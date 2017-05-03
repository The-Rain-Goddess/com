package com.rain.app.server.redux;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.RiotApi;
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
		Future<MatchReferenceList> mostRecentMatchId = Server.getDataRetrievalPool().submit(new FutureTask<MatchReferenceList>(api, api.getClass().getMethod("getRecentMatchListByAccountId", Platform.class, long.class), platform, summonerId)); 
		return (Server.getSummonerDataStorage().get(summonerName).getMostRecentMatchId() == mostRecentMatchId.get().getMatches().get(0).getMatchId());
	}

	private SummonerData getSummonerDataFromStorage(String keyName){
		log("retrieving old summoner data...");
		return Server.getSummonerDataStorage().get(keyName);
	}
	
	private SummonerData updateSummoner(){
		log("updating old summoner data...");
		return null;
	}
	
	private SummonerData createSummoner(){
		log("creating new summoner data...");
		return null;
	}
	
	private void log(String msg){
		LOGGER.log(Level.INFO, msg);
	}

//non-public accessors/mutators	
	public SummonerData getSummonerData(){
		return this.summonerData;
	}
	
}