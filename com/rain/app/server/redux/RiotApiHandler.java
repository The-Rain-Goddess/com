package com.rain.app.server.redux;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.RiotApi;
import com.rain.app.service.riot.api.RiotApiException;
import com.rain.app.service.riot.api.endpoints.champion_mastery.dto.ChampionMasteryList;
import com.rain.app.service.riot.api.endpoints.league.dto.LeagueList;
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
	private long summonerAccountId;
	private final String apiKey  = "fb22315c-06cd-4f26-91ed-f0912a72a78d"; //api-key
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	//private RiotApi api_backup;
	//private final String apiKey2 = "RGAPI-1E616A6D-EC4A-46F1-A9BE-E1C620EABB05"; //api-key2
	
//constructor	
	public RiotApiHandler(String summonerName, Platform platform){
		try{
			log(Level.INFO, "RiotApiHandler: Constructor called.");
			this.summonerName = summonerName;
			this.platform = platform;
			this.api = new RiotApi(new ApiConfig().setKey(apiKey));
			this.summoner = (Summoner) evaluateFromFuture(api.getClass().getMethod("getSummonerByName", Platform.class, String.class), platform, summonerName);
			this.summonerId = this.summoner.getId();
			this.summonerAccountId = this.summoner.getAccountId();
			this.summonerData = (isSummonerExistent()) ? (isSummonerCurrent()) ? getSummonerDataFromStorage(summonerName) : updateSummoner() : createSummoner();
			log(Level.INFO, "RiotApiHandler: Constructor call finished for summoner " + summonerName);
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
			if(e.getCause().getClass().equals(RiotApiException.class)){
				try{
					Thread.sleep(1000L);
					return evaluateFromFuture(method, args);
				} catch(InterruptedException e1){
					e1.printStackTrace();
				}
			} return null;
		}
	}
	
	private boolean isSummonerExistent(){
		return (Server.getSummonerDataStorage().containsKey(summonerName));
	}
	
	private boolean isSummonerCurrent() throws InterruptedException, ExecutionException, NoSuchMethodException, SecurityException{
		return ( mostRecentMatchInMemoryId()== getMatchReferenceList().getMatches().get(0).getGameId());
	}
	
	private static void log(Level info, String string) {
		LOGGER.log(info, string);
	}

	private long mostRecentMatchInMemoryId(){
		return Server.getSummonerDataStorage().get(summonerName).getMostRecentMatchId();
	}
	
	@SuppressWarnings("unused")
	private MatchReferenceList getMostRecentMatchReferenceList() throws InterruptedException, ExecutionException, NoSuchMethodException, SecurityException{
		return (MatchReferenceList) evaluateFromFuture(api.getClass().getMethod("getRecentMatchListByAccountId", Platform.class, long.class), platform, summonerId);
	}
	
	private MatchReferenceList getMatchReferenceList() throws InterruptedException, ExecutionException, NoSuchMethodException, SecurityException{
		return (MatchReferenceList) evaluateFromFuture(api.getClass().getMethod("getMatchReferenceListByAccountId", Platform.class, long.class), platform, summonerAccountId);
	}

	private SummonerData getSummonerDataFromStorage(String keyName){
		log("RiotApiHandler: Retrieving old summoner data...");
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
			log("RiotApiHandler: Retrieving match: " + matchReferences.get(i).getGameId() + "...");
			Server.getSummonerDataStorage().get(summonerName).addMatch(getMatch(matchReferences.get(i).getGameId()), true);
			log("RiotApiHandler: Successfully retrieved match: " + matchReferences.get(i).getGameId() + ".");
		}
	}
	
	private void updateProfileData() throws NoSuchMethodException, SecurityException{
		Server.getSummonerDataStorage().get(summonerName).setLeagueMap(getLeagueMap());
		Server.getSummonerDataStorage().get(summonerName).setChampionMasteryList(getChampionMastery());
	}
	
	private void updateRankedData(){
		log("RiotApiHandler: Updating ranked data...");
		Server.getSummonerDataStorage().get(summonerName).updateAnalysis();
		log("RiotApiHandler: Successfully updated ranked data.");
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, List<LeagueList>> getLeagueMap() throws NoSuchMethodException, SecurityException{
		log("RiotApiHandler: Attempting to retrieve league map...");
		Map<String, List<LeagueList>> map = new TreeMap<String, List<LeagueList>>();
		map.put(summonerName, (List<LeagueList>)evaluateFromFuture(api.getClass().getMethod("getLeagueBySummonerId", Platform.class, long.class), platform, summonerId));
		//log("RiotApiHandler: Successfully retrieved league map.");
		return map; 
	}
	
	private ChampionMasteryList getChampionMastery() throws NoSuchMethodException, SecurityException{
		log("RiotApiHandler: Attempting to retrieve Champion Mastery...");
		return (ChampionMasteryList) evaluateFromFuture(api.getClass().getMethod("getChampionMasteriesBySummoner", Platform.class, long.class), platform, summonerId);
	}
	
	private List<Match> getMatchList(MatchReferenceList newMatchReferences) throws NoSuchMethodException, SecurityException{
		log("RiotApiHandler: Attempting to retrieve MatchReferenceList...");
		List<Match> matchList = Arrays.asList(	this.getMatch(newMatchReferences.getMatches().get(0).getGameId()),
				this.getMatch(newMatchReferences.getMatches().get(1).getGameId()),
				this.getMatch(newMatchReferences.getMatches().get(2).getGameId()),
				this.getMatch(newMatchReferences.getMatches().get(3).getGameId()),
				this.getMatch(newMatchReferences.getMatches().get(4).getGameId()),
				this.getMatch(newMatchReferences.getMatches().get(5).getGameId()),
				this.getMatch(newMatchReferences.getMatches().get(6).getGameId()),
				this.getMatch(newMatchReferences.getMatches().get(7).getGameId()));
		log("RiotApiHandler: Successfully retrieved MatchReferenceList.");
		return matchList;
	}
	
	private SummonerData updateSummoner(){ 
		log("RiotApiHandler: Updating old summoner data..." + " for " + summonerName);
		try {
			MatchReferenceList updatedMatchReferenceList = getMatchReferenceList();
			updateMatchReferences(updatedMatchReferenceList);
			updateMatches(updatedMatchReferenceList);
			updateProfileData();
			updateRankedData();
		} catch (NoSuchMethodException | SecurityException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		log("RiotApiHandler: Successfully updated old summoner data.");
		return Server.getSummonerDataStorage().get(summonerName);
	}
	
	private SummonerData createSummoner(){
		log("RiotApiHandler: Creating new SummonerData for " + summonerName + "...");
		SummonerData newSummoner = null;
		try {
			MatchReferenceList newMatchReferences = getMatchReferenceList();
			newSummoner = new SummonerData(summonerName, summoner);
			newSummoner.setMatchReferenceList(newMatchReferences);
			getMatchList(newMatchReferences).forEach(newSummoner::addMatch);
			log("RiotApiHandler: Mastery list: \n" + ServerUtilities.listToString(newSummoner.setChampionMasteryList(getChampionMastery()).getChampionMasteries()));
			log("RiotApiHandler: League map: \n" + ServerUtilities.mapToString(newSummoner.setLeagueMap(getLeagueMap()).getLeagueMap()));
			log("RiotApiHandler: Attempting to retrieve updated analysis data...");
			log("RiotApiHandler: Ranked Analysis: \n" + ServerUtilities.mapOfListsToString(newSummoner.updateAnalysis().getRankedAnalysis()));
			Server.getSummonerDataStorage().put(summonerName, newSummoner);
			log("RiotApiHandler: SummonerData->" + summonerName + " was successfully added to storage.");
		} catch (NoSuchMethodException | SecurityException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} return newSummoner;
	}
	
	private void log(String msg){
		LOGGER.log(Level.INFO, msg);
	}

//non-private accessors/mutators	
	public SummonerData getSummonerData(String key){
		return Server.getSummonerDataStorage().get(key);
	}
	
	public SummonerData getLocalSummonerData(){ return summonerData; }
}