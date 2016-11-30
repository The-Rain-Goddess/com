package com.rain.app.server;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Random;

public class RequestListListener implements Runnable{
	
//public constructor	
	public RequestListListener(){
		
	}
	
	@SuppressWarnings("unused")
	private void println(String p){
		System.out.println("RequestListener: " + Thread.currentThread().getName() + " " + p);
	}
	
//thread method
	@Override
	public void run(){
		int request_num = 0;
		try{
			System.out.println("RequestListListener: " +Thread.currentThread().getName() + " is started as RequestListListener.");
			
			synchronized(Server.requestList){
				request_num = Server.requestList.size();
			}
			
			while(true){
				//println(request_num+"");
				if(request_num != 0){
					if(request_num == 1){
						int i = 0;
						String temp_string = "";
						int temp_num;
						synchronized(Server.requestList){
							temp_string = Server.requestList.get(i);
						} synchronized(Server.summoner_storage){
							temp_num = Server.summoner_storage.get(temp_string).getMatchDetails().size();
						}
						Thread.sleep(20000L);
						new ClientPopulator(temp_string + "::get_match_history::" + 0 + "::" + (temp_num+1));
						Thread.sleep(15000L);
					}
					else{
						int i = new Random().nextInt(request_num - 1);
						String temp_string = "";
						int temp_num;
						synchronized(Server.requestList){
							temp_string = Server.requestList.get(i);
						} synchronized(Server.summoner_storage){
							temp_num = Server.summoner_storage.get(temp_string).getMatchDetails().size();
						}
						new ClientPopulator(temp_string + "::get_match_history::" + temp_num + "::" + (temp_num+1));
						Thread.sleep(15000L);
					} 
				} synchronized(Server.requestList){
					request_num = Server.requestList.size();
				}
			}
			
		} catch(ConcurrentModificationException e){
			System.err.println("RequestListListener: Problem with synchro");
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
