package com.rain.app.server;

import java.util.Map;

public class RequestListHandler implements Runnable{

//public contructer
	public RequestListHandler(){
		
	}
	
	@SuppressWarnings("unused")
	private  void println(String p){
		System.out.println("RequestListHandler: " + Thread.currentThread().getName() + " " + p);
	}
	
//public main loop
	@Override
	public void run(){
		try{
			System.out.println("RequestListHandler: " + Thread.currentThread().getName() + " is started as RequestListHandler.");
			int control_num = 0, request_num = 0;
			synchronized(Server.handlers){
				control_num = Server.handlers.size();
			}
			synchronized(Server.requestList){
				request_num = Server.requestList.size();
			}
	//thread main loop
			while(true){
				//println("Handlers: " + control_num);
				//println("Requests: " + request_num);
				if(control_num == 0 && request_num == 0){
					synchronized(Server.summoner_storage){
						if(Server.summoner_storage.size()!=0){
							for(Map.Entry<String, com.rain.app.server.Summoner> entry : Server.summoner_storage.entrySet()){
								synchronized(Server.requestList){
									Server.requestList.add(entry.getKey());
								}
							}
						}
					}
				} else if( control_num != 0){
					synchronized(Server.requestList){
						Server.requestList.clear();
					}
				} synchronized(Server.handlers){
					control_num = Server.handlers.size();
				} synchronized(Server.requestList){
					request_num = Server.requestList.size();
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
