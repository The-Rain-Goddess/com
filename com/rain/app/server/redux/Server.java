package com.rain.app.server.redux;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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

public class Server {
//Package Varriables	
	private static final Map<String, SummonerData> summonerDataStorage = Collections.synchronizedSortedMap(new TreeMap<String, SummonerData>());
	private static final ExecutorService dataRetrievalPool = new ThreadPoolExecutor(3, 100, 10000L, TimeUnit.MILLISECONDS, new RateLimitingQueue(5000, 9, 10000L, 3));
	private boolean running = true;
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
//MAIN	
	public static void main(String[] args) throws IOException{
		MyLogger.setup();
		final int PORT_NUMBER = 48869;
		new Server(PORT_NUMBER);
		
	}

//constructor	
	public Server(final int PORT_NUMBER) throws IOException{
		log("Server Started");
		ServerSocket ss = null;
		ExecutorService executor = null;
		try{
			log("Server: " + Thread.currentThread().getName() + " is started.");
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
			log("Server shutting down");
			saveDataToText();
			
			executor.shutdownNow();
			ss.close();
		}
	}
	
	public static Map<String, SummonerData> getSummonerDataStorage(){ return summonerDataStorage; }
	
	public static ExecutorService getDataRetrievalPool(){ return dataRetrievalPool; }
	
	private static void log(String msg){
		LOGGER.log(Level.INFO, msg);
	}
	
	private static void log(Level level, String msg){
		LOGGER.log(level, msg);
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
}