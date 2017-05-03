package com.rain.app.server.redux;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rain.app.server.Rapi;
import com.rain.app.service.riot.constant.Platform;

public class ClientHandler extends Thread {
	protected Socket s;
	protected DataInputStream in;
	protected DataOutputStream out;
	protected Rapi rapi;
	protected SummonerData mSummoner;
	private List<String> requestFromClient;
	private RiotApiHandler riotApiHandler;
	
	private String request;
	
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
//public constructor	
	public ClientHandler(Socket s) throws IOException{
		log("ClientHandler: Client accepted");
		this.s = 	s;
		this.in = 	new DataInputStream( 	new BufferedInputStream(	s.getInputStream()));
		this.out = new DataOutputStream(	new BufferedOutputStream(	s.getOutputStream()));
	}
	
	@Override
	public void run(){
		try{
			log(getDate() + " ClientHandler: " + Thread.currentThread().getName() + " is started as ClientHandler.");
			String msg = in.readUTF(); 
			log(msg);
			requestFromClient = Arrays.asList(msg.split("::"));
			riotApiHandler = new RiotApiHandler(requestFromClient.get(0), Platform.NA);
			List<String> responseForClient = processRequest();
			respondToClient(responseForClient);
			in.close(); out.close(); s.close();
		} catch(IOException e){
			e.printStackTrace();
		} finally{
			log(getDate() + " ClientHandler: " + Thread.currentThread().getName() + ", Data Exchange Finished and thread closing." ); 
		}
	}
	
	private List<String> processRequest() throws IOException{
		List<String> responseToClient = new ArrayList<>();
		if(request.equals("get_match_history")){
			responseToClient = getMatchHistory();
		} else if(request.equals("get_analysis")){
			responseToClient = getAnalysis();
		} else if(request.equals("get_profile")){
			responseToClient = getProfile();
		} else if(request.equals("send_feedback")){
			responseToClient = sendFeedback();
		} return responseToClient;	
	}
	
	
	private List<String> getMatchHistory(){
		return riotApiHandler.getSummonerData().getMatchHistory();
	}
	
	private List<String> getAnalysis(){
		return riotApiHandler.getSummonerData().getAnalysis();
	}

	private List<String> getProfile(){
		return riotApiHandler.getSummonerData().getProfile();
	}
	
	private List<String> sendFeedback() throws IOException{
		String limit = in.readUTF();
		
		int numberOfLines = Integer.parseInt(limit);
		List<String> feedback = new ArrayList<>(20);
		for(int i = 0; i<numberOfLines; i++){
			String line = in.readUTF();
			feedback.add(line);
		}
		Path feedbackFilePath = Paths.get("feedback\\" + getDate().replace(":", "-").replace(" ", "-")+".txt");
		Files.write(feedbackFilePath, feedback, Charset.forName("UTF-8"));
		System.out.println(feedbackFilePath.toAbsolutePath().toString());
		System.out.println("Feedback has bee successfully written to file.");
		return null;
	}

	private void respondToClient(List<String> response) throws IOException{
		
	}
	
	private String getDate(){
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();					
	    String strDate = sdfDate.format(now);	
		strDate = "[" + strDate + "]";
		return strDate;
	}
	
	private static void log(String msg){
		LOGGER.log(Level.INFO, msg);
	}
	
	protected static void broadcast(String msg){
		System.out.println("ClientHandler: " + Thread.currentThread().getName() + ", MSG: " + msg);
	}
}
