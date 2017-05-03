package com.rain.app.server.redux;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.RiotApi;
import com.rain.app.service.riot.api.endpoints.match.dto.MatchList;
import com.rain.app.service.riot.api.endpoints.summoner.dto.Summoner;
import com.rain.app.service.riot.constant.Platform;

public class RiotApiHandler {
	private String summonerName;
	private SummonerData summonerData;
	private Platform platform;
	private RiotApi api;
	//private RiotApi api_backup;
	private long summonerId;
	private final String apiKey  = "fb22315c-06cd-4f26-91ed-f0912a72a78d"; //api-key
	//private final String apiKey2 = "RGAPI-1E616A6D-EC4A-46F1-A9BE-E1C620EABB05"; //api-key2
	private Summoner summoner;
	
	public RiotApiHandler(String summonerName, Platform platform){
		try{
			this.summonerName = summonerName;
			this.platform = platform;
			api = new RiotApi(new ApiConfig().setKey(apiKey));
			//api_backup = new RiotApi(new ApiConfig().setKey(apiKey2));
			summoner = (Summoner) evaluateFromFuture(api.getClass().getMethod("getSummonerByName", Platform.class, String.class), platform, summonerName);
			summonerId = summoner.getAccountId();
			summonerData = (isSummonerExistent()) ? (isSummonerCurrent()) ? getSummoner() : updateSummoner() : createSummoner();
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
		Future<MatchList> mostRecentMatchId = Server.getDataRetrievalPool().submit(new FutureTask<MatchList>(api, api.getClass().getMethod("getRecentMatchListByAccountId", Platform.class, long.class), platform, summonerId)); 
		return (Server.getSummonerDataStorage().get(summonerName).getMostRecentMatchId() == mostRecentMatchId.get().getMatches().get(0).getMatchId());
	}
	
	private SummonerData getSummoner(){
		return null;
	}
	
	private SummonerData updateSummoner(){
		return null;
	}
	
	private SummonerData createSummoner(){
		System.out.println("creating summoner...");
		return null;
	}
	
	public SummonerData getSummonerData(){
		return summonerData;
	}
}