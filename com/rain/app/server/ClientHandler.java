package com.rain.app.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.rithms.riot.api.RateLimitException;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.dto.Match.MatchDetail;

public class ClientHandler extends Thread {
	protected Socket s;
	protected DataInputStream in;
	protected DataOutputStream out;
	
	
	protected static Map<String, com.rain.app.server.Summoner> summoner_ranked_storage = Collections.synchronizedSortedMap(new TreeMap<String, com.rain.app.server.Summoner>());
	
	protected Rapi rapi;
	protected com.rain.app.server.Summoner mSummoner;
	protected String[] msg_split;
	
//public constructor	
	public ClientHandler(Socket s) throws IOException{
		System.out.println("ClientHandler: Client accepted");
		this.s = s;
		this.in = new DataInputStream( new BufferedInputStream(
				s.getInputStream()));
		this.out = new DataOutputStream( new BufferedOutputStream(
				s.getOutputStream()));
	}
	
	private boolean checkIfSummonerCurrent(String name){
		if(com.rain.app.server.Server.summoner_storage.containsKey(name))
			if(com.rain.app.server.Server.summoner_storage.get(name).getMostRecentMatchId() == rapi.getMostRecentMatchId())
				return true;
			else
				return false;
		else return false;
	}
	
	@SuppressWarnings("unused")
	private boolean checkIfSummonerRankedCurrent(String name){
		if(summoner_ranked_storage.containsKey(name))
			if(summoner_ranked_storage.get(name).getMostRecentMatchId() == rapi.getMostRecentRankedMatchId())
				return true;
			else
				return false;
		else return false;
	}
	
	@Override
	public void run(){
		int match_data_stop = 0;
		int match_data_start = 0;
		int match_retrieve_num = 0;
		try{
			System.out.println("ClientHandler: " + Thread.currentThread().getName() + " is started as ClientHandler.");
	//variables to be used in run()
			synchronized(Server.handlers){
				Server.handlers.addElement(this);
			}
													//		0			1			2		3		4
			String msg = in.readUTF(); //msg syntax is: "<summName>::<command>::<number>::<start>::<stop>"
			msg_split = msg.split("::"); //splits msg into input and command
			broadcast(msg);
			match_data_start = Integer.parseInt(msg_split[3]);
			match_data_stop = Integer.parseInt(msg_split[4]);
			match_retrieve_num = Integer.parseInt(msg_split[2]);
			
			rapi = new Rapi(msg_split[0]); //does most of the main data req work
			
	//main loop
				broadcast(msg);
				if(msg_split[1].equals("get_match_history")){
					if(checkIfSummonerCurrent(msg_split[0])){ //TODO: fix problem with getting data that is older, not newer than whats in memory
						System.err.println("ClientHandler: Memory-read segment");
						//outputs to client
						out.writeUTF(match_retrieve_num + "");
						out.flush();
						for(int i = match_data_start; i < match_data_stop; i++){
							out.writeUTF(com.rain.app.server.Server.summoner_storage.get(msg_split[0]).getMatchesFromMemory(i, i+1));
							out.flush();
						}
					} else if(com.rain.app.server.Server.summoner_storage.containsKey(msg_split[0])){
						System.err.println("ClientHandler: Update Segment");
						//updates summoner
						List<MatchDetail> list = rapi.getMatchDetails(com.rain.app.server.Server.summoner_storage.get(msg_split[0]).getMostRecentMatchId());
						synchronized(Server.summoner_storage){	// synchronized to preven concurrent access of 'summoner_storage' across all ClientHandlers
							com.rain.app.server.Server.summoner_storage.get(msg_split[0])
							.addMatchesToMemory(0, list, rapi.getMatchReferences(), rapi.getChampionNames()); //summoner_storage.get(msg_split[0]).getMostRecentMatchId()
						}
						//outputs to client
						out.writeUTF(match_retrieve_num + "");
						out.flush();
						for(int i = match_data_start; i < match_data_stop; i++){
							out.writeUTF(com.rain.app.server.Server.summoner_storage.get(msg_split[0]).getMatchesFromMemory(i, i+1));
							out.flush();
						}	
					} else{
						System.err.println("ClientHandler: New-Summoner Segment");
						out.writeUTF(match_retrieve_num + "");
						out.flush();
						for(int i = match_data_start; i< match_data_stop; i++){
							String match_data_to_output = rapi.getHistory(i, i+1);
							out.writeUTF(match_data_to_output); 	//this part returns the match history from a to b
							out.flush();					 		//to the client
						}
						
						mSummoner = new com.rain.app.server.Summoner(msg_split[0], rapi.getId(), rapi.getMatchReferences(), rapi.getMatchDetails(), rapi.getChampionNames() );
						synchronized(Server.summoner_storage){ // synchronized to preven concurrent access of 'summoner_storage' across all ClientHandlers
							com.rain.app.server.Server.summoner_storage.put(msg_split[0], mSummoner);
						}
					}
				} else if(msg_split[1].equals("get_analysis")){
					if(checkIfSummonerCurrent(msg_split[0])){ //checks to see if the client data is already in memory
						out.writeUTF(rapi.getRankedStats());
						out.writeUTF("EOF");
						out.flush();
					} else{
						
						out.flush();					 //to the client
					}
				} else if(msg_split[1].equals("")){
					
				}
		} catch(RateLimitException ex){					//client exceeds riot limit rate
			try{
				synchronized(Server.summoner_storage){
					com.rain.app.server.Server.summoner_storage.remove(msg_split[0]);
				}
				out.writeUTF("RiotLimitException");
				out.flush();
			} catch(IOException e){						//there was an IO exception
				e.printStackTrace();
			} ex.printStackTrace();
		} catch(IOException e){
			System.err.println("ClientHandler: IOException");
			e.printStackTrace();
		} catch (RiotApiException e) {					//riot api did not accept the command
			try{
				out.writeUTF("RiotApiException");
				out.flush();
			} catch(IOException ex){
				ex.printStackTrace();
			} e.printStackTrace();
		} catch(Exception e){ 
			e.printStackTrace();
		} finally{
			try{
				//out.writeUTF("EOF");  // this signals to client that 
				//out.flush();		// connection is about to close
				
				in.close();			//closes server-side connection
				out.close();		// ^
				s.close();			// ^
				
				Thread.sleep(0);
		/*		//////////////////////////// gets 24 older matches
				if(com.rain.app.server.Server.summoner_storage.get(msg_split[0]).getMatchDetails().size() <= match_data_stop){
					Thread.sleep(10000L);
					List<MatchDetail> list = rapi.getOlderMatchDetails(com.rain.app.server.Server.summoner_storage.get(msg_split[0]).getMatchDetails().size(), 24);
					synchronized(this){	// synchronized to preven concurrent access of 'summoner_storage' across all ClientHandlers
						com.rain.app.server.Server.summoner_storage.get(msg_split[0])
						.addMatchesToMemory(list, rapi.getMatchReferences(), rapi.getChampionNames());
					} System.err.println("End Data Lookup:");	
				} 
				//////////////////////////// */
				synchronized(Server.handlers){
					Server.handlers.removeElement(this);  //prevents old connections from leaking data
				}
				rapi = null;
			} catch(IOException e){
				e.printStackTrace();
			} catch(InterruptedException e){
				e.printStackTrace();
			} System.err.println("ClientHandler: " +Thread.currentThread().getName() + ", Data Exchange Finished and thread closing." ); 
			System.out.println();
		}
	}
	
	protected static void broadcast(String msg){
		System.err.println("ClientHandler: " + Thread.currentThread().getName() + ", MSG: " + msg);
	}
}
