package com.rain.app.server.redux;

import java.util.ArrayList;
import java.util.List;

import com.rain.app.service.riot.api.endpoints.match.dto.Match;
import com.rain.app.service.riot.api.endpoints.match.dto.MatchReferenceList;
import com.rain.app.service.riot.api.endpoints.summoner.dto.Summoner;


public class SummonerData {
	private List<Match> matchList;
	private MatchReferenceList matchReferenceList;
	private List<String> champion;
	private List<String> masteryProfileData;
	private String rankedProfileData;
	private String rankedAnalysis;
	private long summonerId;
	private String summonerName;

//constructors	
	public SummonerData(String name, Summoner summoner){
		this.summonerId = summoner.getAccountId();
		this.summonerName = name;
		this.matchList = new ArrayList<>();
	}

//non-private accessors/mutators
	public SummonerData setMatchList(MatchReferenceList matches){
		matchReferenceList = matches;
		return this;
	}
	
	public SummonerData addMatch(Match match, boolean addNewMatch){
		if(addNewMatch)
			matchList.add(0, match);
		else
			matchList.add(match);
		return this;
	}
	
//accessors
	public List<String> getMatchHistory(){ return null; }
	
	public List<String> getAnalysis(){ return null; }
	
	public List<String> getProfile(){ return null; } 
	
	public List<String> getMasteryProfileData(){ return masteryProfileData; }
	
	public String getRankedProfileData(){ return rankedProfileData; }
	
	public String getSummonerName(){ return summonerName; }
	
	public long getSummonerId(){ return summonerId; }
	
	public MatchReferenceList getMatchReferenceList(){ return matchReferenceList; }
	
	public long getMostRecentMatchId(){ return matchList.get(0).getGameId(); }
	
	
}
