package com.rain.app.server.redux;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.RiotApi;
import com.rain.app.service.riot.api.RiotApiException;
import com.rain.app.service.riot.api.endpoints.match.dto.Match;
import com.rain.app.service.riot.api.endpoints.summoner.dto.Summoner;
import com.rain.app.service.riot.constant.Platform;

public class FutureTest {

	public static void main(String[] args) throws RiotApiException, NoSuchMethodException, SecurityException, InterruptedException, ExecutionException{
		long time = System.currentTimeMillis();
		RiotApi api = new RiotApi(new ApiConfig().setKey("fb22315c-06cd-4f26-91ed-f0912a72a78d").setRespectRateLimit(true));
		//System.out.println("Normal " + (System.currentTimeMillis() - time) + " " + api.getSummonerByName(Platform.NA, "theraingoddess"));
		
		time = System.currentTimeMillis();
		FutureTask<Object> ft = new FutureTask<Object>(api, api.getClass().getMethod("getSummonerByName", Platform.class, String.class), Platform.NA, "theraingoddess");
		//FutureTask<Summoner> ft2 = new FutureTask<Summoner>(api, api.getClass().getMethod("getSummonerByName", Platform.class, String.class), Platform.NA, "theicegoddess");
		FutureTask<Object> ft3 = new FutureTask<Object>(api, api.getClass().getMethod("getMatch", Platform.class, long.class), Platform.NA, 2488585989L);
		//ExecutorService pool = Executors.newFixedThreadPool(1);
		//Future<Summoner> future = pool.submit(ft);
		int n = 3; // number of threads
		RateLimitingQueue queue = new RateLimitingQueue(5000, 9, 10000L, n);
		ExecutorService executor = new ThreadPoolExecutor(n, 100, 10000L, TimeUnit.MILLISECONDS, queue);
		List<Future<Object>> futureList = new ArrayList<>();
		for(int i = 0; i<30; i++){
			Future<Object> f;
			if(i%2==0){
				f = executor.submit(ft);
			} else 
				f = executor.submit(ft3);
			
			futureList.add(f);
		}
		//List<Future<Summoner>> futureList = executor.invokeAll(Arrays.asList(ft, ft2, ft, ft2, ft, ft2, ft, ft2, ft, ft2, ft));
		int recieved = 1;
		for(int i = 0; i<30; i++){
			if(i%2==0){
				Summoner s = (Summoner) futureList.get(i).get();
				System.out.println("Future " + recieved + " " + (System.currentTimeMillis() - time) + " " + s.toString());
				
			} else{
				Match m = (Match) futureList.get(i).get();
				System.out.println("Future " + recieved + " " + (System.currentTimeMillis() - time) + " " + m.getGameDuration());
			}
			recieved++;
		}
		System.out.println("Future done");
		
		executor.shutdown();
		Thread.sleep(5000L);
		executor.shutdownNow();
	}
}
