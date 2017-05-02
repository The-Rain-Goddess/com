package com.rain.app.server.redux;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.RiotApi;
import com.rain.app.service.riot.api.RiotApiException;
import com.rain.app.service.riot.api.endpoints.summoner.dto.Summoner;
import com.rain.app.service.riot.constant.Platform;

public class FutureTest {

	public static void main(String[] args) throws RiotApiException, NoSuchMethodException, SecurityException, InterruptedException, ExecutionException{
		long time = System.currentTimeMillis();
		RiotApi api = new RiotApi(new ApiConfig().setKey("fb22315c-06cd-4f26-91ed-f0912a72a78d"));
		System.out.println("Normal " + (System.currentTimeMillis() - time) + " " + api.getSummonerByName(Platform.NA, "theraingoddess"));
		
		time = System.currentTimeMillis();
		FutureTask<Summoner> ft = new FutureTask<Summoner>(api, api.getClass().getMethod("getSummonerByName", Platform.class, String.class), Platform.NA, "theraingoddess");
		ExecutorService pool = Executors.newFixedThreadPool(1);
		Future<Summoner> future = pool.submit(ft);
		Summoner s = future.get();
		System.out.println("Future " + (System.currentTimeMillis() - time) + " " + s.toString());
		
		pool.shutdownNow();
	}
}
