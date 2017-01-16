package com.rain.app.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class Server {
//Package Varriables	
	protected static Map<String, com.rain.app.server.Summoner> summoner_storage = Collections.synchronizedSortedMap(new TreeMap<String, com.rain.app.server.Summoner>());
	protected static Vector<ClientHandler> handlers = new Vector<ClientHandler>();
	protected static List<String> requestList = Collections.synchronizedList(new ArrayList<String>());
	protected static String keyboard = "";
	
	
//MAIN	
	public static void main(String[] args) throws IOException{
		System.out.println("Server: Server Started");
		new Server(48869);
		
	}
	
	public Server(int port) throws IOException{
		ServerSocket ss = null;
		try{ 
			System.out.println("Server: " + Thread.currentThread().getName() + " is started.");
			ss = new ServerSocket(port);
			new Thread(new KeyHandler()).start();			
			//new Thread(new RequestListHandler()).start();	
			//new Thread(new RequestListListener()).start(); 	
			
			while(!keyboard.equals("x")){
				Socket serviceSocket = ss.accept();
				System.err.println("Server: Accepted from " + serviceSocket.getInetAddress());
				
				ClientHandler c = new ClientHandler(serviceSocket);
				c.start();
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			saveDataToText();
			ss.close();
		}
	}
	
	//@SuppressWarnings("resource")
	private boolean saveDataToText(){
		try{
			PrintWriter pw = new PrintWriter("backup.txt");
			synchronized(summoner_storage){
				System.err.println("Server: Exported Data");
				//String name; Summoner temp_summoner = new Summoner();
				for(Map.Entry<String, com.rain.app.server.Summoner> entry : summoner_storage.entrySet()){
					//name = entry.getKey();
					//temp_summoner = entry.getValue();
					String tmp = entry.getValue().toString();
					System.out.println("Server: " + tmp);
					pw.println(entry.getKey() + ": ");
					pw.println("Two: " + tmp);
					pw.flush();
				}
			} pw.close();
		} catch(IOException e){
			e.printStackTrace();
		} 
		finally{
			
		}
		
		return true;
	}

}
