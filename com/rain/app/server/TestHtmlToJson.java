package com.rain.app.server;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

//IT WORKED!!!!!!!!!!!!!!!!!!!!

public class TestHtmlToJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String content = null;
		URLConnection connection = null; //?api_key=RGAPI-1E616A6D-EC4A-46F1-A9BE-E1C620EABB05
		Scanner scanner = null;
		String matchId = "2269415069";
		String destination = "https://na.api.pvp.net/api/lol/na/v2.2/match";
		destination = destination + "/" + matchId + "?api_key=RGAPI-1E616A6D-EC4A-46F1-A9BE-E1C620EABB05";
		
		try {
		  connection =  new URL(destination).openConnection();
		  scanner = new Scanner(connection.getInputStream());
		  scanner.useDelimiter("\\Z");
		  content = scanner.next();
		}catch ( Exception ex ) {
		    ex.printStackTrace();
		} scanner.close();
		
		String data = content; //all of the data
		
		//the match itself
		JSONObject jObject = new JSONObject(data);
		
		//list of the participants
		JSONArray participants = jObject.getJSONArray("participants");
		for(int i = 0; i<participants.length(); i++){
			System.out.println(participants.get(i).toString());
		}
	}
}
