package com.rain.app.server.redux;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rain.app.server.redux.logger.MyLogger;
import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.RiotApi;
import com.rain.app.service.riot.api.RiotApiException;
import com.rain.app.service.riot.api.endpoints.constants.dto.Champion;
import com.rain.app.service.riot.api.endpoints.constants.dto.ChampionList;
import com.rain.app.service.riot.constant.Platform;

public class Server {
//Package Varriables	
	private static final Map<String, SummonerData> summonerDataStorage = Collections.synchronizedSortedMap(new TreeMap<String, SummonerData>());
	private static final ExecutorService dataRetrievalPool = new ThreadPoolExecutor(1, 100, 10000L, TimeUnit.MILLISECONDS, new RateLimitingQueue(5000, 8, 10000L, 1));
	private static ChampionList championTranslationList;
	private boolean running = true;
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
//MAIN	
	public static void main(String[] args) throws IOException{
		MyLogger.setup();
		final int PORT_NUMBER = 48868;
		new Server(PORT_NUMBER);
	}

//constructor	
	public Server(final int PORT_NUMBER) throws IOException{
		championTranslationList = getChampionDataList();
		log("Server: Champion translation list succesfully retrieved.");
		log("Server: Server Started");
		ServerSocket ss = null;
		ExecutorService executor = null;
		try{
			log("Server: [" + Thread.currentThread().getName() + "] is started.");
			executor = Executors.newCachedThreadPool();
			
			ss = new ServerSocket(PORT_NUMBER);
			while(running){
				Socket serviceSocket = ss.accept();
				log("Server: Accepted from " + serviceSocket.getInetAddress());
				ClientHandler c = new ClientHandler(serviceSocket);
				executor.submit(c);
			} executor.shutdown();
			Thread.sleep(5000L);
		} catch(IOException e){
			e.printStackTrace();
		} catch(InterruptedException e){
			e.printStackTrace();
		} finally{
			log("Server: Server shutting down");
			saveDataToText();
			
			executor.shutdownNow();
			ss.close();
		}
	}

//private a/m	
	private static ChampionList getChampionDataList(){
		try {
			log("Server: Retrieving champion translation list...");
			return (new RiotApi(new ApiConfig().setKey("RGAPI-1E616A6D-EC4A-46F1-A9BE-E1C620EABB05"))).getDataChampionList(Platform.NA);
		} catch (RiotApiException e1) {
			e1.printStackTrace();
			log(Level.SEVERE, "Server: Riot Service unavailable, attempting to get Champion Data List.", e1);
			return getChampionDataList();
		}
	}
	
	private static void log(String msg){
		LOGGER.log(Level.INFO, msg);
	}
	
	@SuppressWarnings("unused")
	private static void log(Level level, String msg){
		LOGGER.log(level, msg);
	}
	
	private static void log(Level level, String msg, Exception e){
		LOGGER.log(level, msg, e);
	}
	
	private boolean saveDataToText(){
		try{
			PrintWriter pw = new PrintWriter("backup.txt");
			synchronized(summonerDataStorage){
				System.err.println("Server: Exported Data");
				for(Map.Entry<String, SummonerData> entry : summonerDataStorage.entrySet()){
					String tmp = entry.getValue().toString();
					System.out.println("Server: " + tmp);
					pw.println(entry.getKey() + ": ");
					pw.println("Two: " + tmp);
					pw.flush();
				}
			} pw.close();
		} catch(IOException e){
			e.printStackTrace();
		} finally{
			
		} return true;
	}
	
	
//non-private a/m
	public static String getChampionNameFromId(long champId){
		for(Map.Entry<String, Champion> entry : new ArrayList<>(championTranslationList.getData().entrySet()))
			if(entry.getValue().getId() == champId)
				return entry.getValue().getName();
		return null;
	}
	
	public static ChampionList getChampionTranslationList(){ return championTranslationList; }
	
	public static Map<String, SummonerData> getSummonerDataStorage(){ return summonerDataStorage; }
	
	public static ExecutorService getDataRetrievalPool(){ return dataRetrievalPool; }
}