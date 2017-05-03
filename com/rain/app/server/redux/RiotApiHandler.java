package com.rain.app.server.redux;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.RiotApi;
import com.rain.app.service.riot.api.endpoints.match.dto.Match;
import com.rain.app.service.riot.api.endpoints.match.dto.MatchReference;
import com.rain.app.service.riot.api.endpoints.match.dto.MatchReferenceList;
import com.rain.app.service.riot.api.endpoints.summoner.dto.Summoner;
import com.rain.app.service.riot.constant.Platform;

public class RiotApiHandler {
	private String summonerName;
	private SummonerData summonerData;
	private Summoner summoner;
	private Platform platform;
	private RiotApi api;
	private long summonerId;
	private final String apiKey  = "fb22315c-06cd-4f26-91ed-f0912a72a78d"; //api-key
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	//private RiotApi api_backup;
	//private final String apiKey2 = "RGAPI-1E616A6D-EC4A-46F1-A9BE-E1C620EABB05"; //api-key2
	
//constructor	
	public RiotApiHandler(String summonerName, Platform platform){
		try{
			this.summonerName = summonerName;
			this.platform = platform;
			this.api = new RiotApi(new ApiConfig().setKey(apiKey));
			this.summoner = (Summoner) evaluateFromFuture(api.getClass().getMethod("getSummonerByName", Platform.class, String.class), platform, summonerName);
			this.summonerId = summoner.getAccountId();
			this.summonerData = (isSummonerExistent()) ? (isSummonerCurrent()) ? getSummonerDataFromStorage(summonerName) : updateSummoner() : createSummoner();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
//private mutators	
	private Object evaluateFromFuture(Method method, Object... args){
		try {
			Future<Object> future = Server.getDataRetrievalPool().submit(new FutureTask<Object>(api, method, args));
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private boolean isSummonerExistent(){
		return (Server.getSummonerDataStorage().containsKey(summonerName));
	}
	
	private boolean isSummonerCurrent() throws InterruptedException, ExecutionException, NoSuchMethodException, SecurityException{
		return (mostRecentMatchInMemoryId() == getMostRecentMatchReferenceList().getMatches().get(0).getGameId());
	}
	
	private long mostRecentMatchInMemoryId(){
		return Server.getSummonerDataStorage().get(summonerName).getMostRecentMatchId();
	}
	
	private MatchReferenceList getMostRecentMatchReferenceList() throws InterruptedException, ExecutionException, NoSuchMethodException, SecurityException{
		return (MatchReferenceList) evaluateFromFuture(api.getClass().getMethod("getRecentMatchListByAccountId", Platform.class, long.class), platform, summonerId);
	}
	
	private MatchReferenceList getMatchReferenceList() throws InterruptedException, ExecutionException, NoSuchMethodException, SecurityException{
		return (MatchReferenceList) evaluateFromFuture(api.getClass().getMethod("getMatchListByAccountId", Platform.class, long.class), platform, summonerId);
	}

	private SummonerData getSummonerDataFromStorage(String keyName){
		log("retrieving old summoner data...");
		return Server.getSummonerDataStorage().get(keyName);
	}
	
	private int getNumberOfNewMatches(List<MatchReference> matchReferences){
		int count = 0;
		long mostRecentMatchInMemory = mostRecentMatchInMemoryId();
		long matchToAddId = matchReferences.get(count).getGameId();
		while(matchToAddId != mostRecentMatchInMemory){
			matchToAddId = matchReferences.get(count).getGameId();
			count++;
		} return count;
	}
	
	private Match getMatch(long matchId) throws NoSuchMethodException, SecurityException{
		return (Match) evaluateFromFuture(api.getClass().getMethod("getMatch", Platform.class, long.class), platform, matchId);
	}
	
	private void updateMatchReferences(MatchReferenceList mrl){
		Server.getSummonerDataStorage().get(summonerName).setMatchReferenceList(mrl);
	}
	
	private void updateMatches(MatchReferenceList mrl) throws NoSuchMethodException, SecurityException{
		List<MatchReference> matchReferences = mrl.getMatches();
		int numberOfNewMatchesToAdd = getNumberOfNewMatches(matchReferences);
		for(int i = numberOfNewMatchesToAdd; i > 0 ; i--){
			log("Retrieving match: " + matchReferences.get(i).getGameId());
			Server.getSummonerDataStorage().get(summonerName).addMatch(getMatch(matchReferences.get(i).getGameId()), true);
		}
	}
	
	private SummonerData updateSummoner(){ 
		log("updating old summoner data..." + " for " + summonerName);
		try {
			MatchReferenceList updatedMatchReferenceList = getMatchReferenceList();
			updateMatchReferences(updatedMatchReferenceList);
			updateMatches(updatedMatchReferenceList);
		} catch (NoSuchMethodException | SecurityException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} return Server.getSummonerDataStorage().get(summonerName);
	}
	
	private SummonerData createSummoner(){
		log("creating new summoner data..." + " for " + summonerName);
		SummonerData newSummoner = null;
		try {
			newSummoner = new SummonerData(summonerName, summoner);
			MatchReferenceList newMatchReferences = getMatchReferenceList();
			newSummoner.setMatchReferenceList(newMatchReferences);
		} catch (NoSuchMethodException | SecurityException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} return newSummoner;
	}
	
	private void log(String msg){
		LOGGER.log(Level.INFO, msg);
	}

//non-public accessors/mutators	
	public SummonerData getSummonerData(){
		return Server.getSummonerDataStorage().get(summonerName);
	}
	
}