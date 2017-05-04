package com.rain.app.server.redux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rain.app.service.riot.api.endpoints.match.dto.Match;
import com.rain.app.service.riot.api.endpoints.match.dto.MatchReference;
import com.rain.app.service.riot.api.endpoints.match.dto.MatchReferenceList;
import com.rain.app.service.riot.api.endpoints.match.dto.Participant;
import com.rain.app.service.riot.api.endpoints.match.dto.ParticipantStats;
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
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

//constructors	
	public SummonerData(String name, Summoner summoner){
		log("SummonerData: " + "Constructor called.");
		this.summonerId = summoner.getAccountId();
		this.summonerName = name;
		this.matchList = new ArrayList<>();
		log("SummonerData: " + summonerName + " was created.");
	}

//private a/m
	@SuppressWarnings("unused")
	private static void log(Level level, String msg){
		LOGGER.log(level, msg);
	}
	
	private static void log(Level level, String msg, Exception e){
		LOGGER.log(level, msg, e);
	}
	
	private static void log(String msg){
		LOGGER.log(Level.INFO, msg);
	}
	
	private String aggregateMatchData(Match match, int matchIndex){
		String aggregatedData = null;
		List<String> unorderedPlayerList = new ArrayList<>(10);
		for(int i = 0; i < 10; i++){ //aggregates player data to list
			Participant player = match.getParticipants().get(i);
			String stats = 	getMatchDetails(player.getStats(), matchReferenceList.getMatches().get(matchIndex)) +
							"champion:" + player.getChampionId() + "/" + 
							getSummonerSpellIds(player) + 
							getMatchPeripherals(match);
			if(player.getParticipantId()==summonerId)
				stats = stats + getTeamStats(match.getParticipants(), player.getTeamId());
			unorderedPlayerList.add(stats);
		} 
		aggregatedData = toOutputFormat(sortPlayerList(unorderedPlayerList)); //sorts list and formats it		
		return aggregatedData;
	}
	
	private String getTeamStats(List<Participant> players, int teamId){
		double teamDmg = 0, enemyTeamDmg = 0;
		for(int k = 0; k<10; k++){ //this loop is to get total team dmg and total enemy team dmg
			if(players.get(k).getTeamId() == teamId )
				teamDmg+= (players.get(k).getStats().getTotalDamageDealtToChampions());
			else
				enemyTeamDmg += (players.get(k).getStats().getTotalDamageDealtToChampions());
		} return "totalTeamDmg:" + teamDmg + "/" + 
				 "totalEnemyDmg:" + enemyTeamDmg + "/";
	}
	
	private List<String> sortPlayerList(List<String> unorderedPlayerList){
		List<String> orderedPlayerList = new ArrayList<>(10);
		for(String playerData : unorderedPlayerList){
			if(playerData.contains("summonerId:" + summonerId))
				orderedPlayerList.add(0, playerData);
			else
				orderedPlayerList.add(playerData);
		} return orderedPlayerList;
	}
	
	private String toOutputFormat(List<String> list){
		String out = "";
		for(int i = 0; i<list.size(); i++)
			out = (i==list.size()-1) ? (out + list.get(i)) : (out + list.get(i) + "PLAYER");
		return out;
	}
	
	private String getMatchPeripherals(Match match){
				return 	"matchLength:" + match.getGameDuration() + "/" +	//60
						"matchId:" + match.getGameId() + "/" +				//61
						"matchMode:" + match.getGameMode() + "/" +			//62
						"matchType:" + match.getGameType() + "/" + 			//63
						"matchStartTime:" + match.getGameCreation() + "/" + //64
						"queueType:" + match.getQueueId() + "/";			//65
	}
	
	private String getSummonerSpellIds(Participant player){
				 return "sspell1:" + player.getSpell1Id() +  "/" +		//58
						"sspell2:" + player.getSpell2Id() + "/" +		//59
						"summonerId: " + player.getParticipantId() + "/" + 
						"teamId:" +  player.getTeamId() + "/"; 
	}
	
	private String getMatchDetails(ParticipantStats ps, MatchReference matchReference){
		return 	aggregatePlayerStats(ps) ;		
	}
	
	private String aggregatePlayerStats(ParticipantStats ps){
		String returnString = 
						"assists:" + ps.getAssists() + "/" + 
						
						"champLevel:" + ps.getChampLevel() + "/" + 
						"cs:" + (ps.getTotalMinionsKilled() + ps.getNeutralMinionsKilledEnemyJungle() + ps.getNeutralMinionsKilledTeamJungle()) + "/" +
						"deaths:" + ps.getDeaths() + "/" + 		  
						"dmgToChamp:" + ps.getTotalDamageDealtToChampions() + "/" + 
						"dmgTaken:" + ps.getTotalDamageTaken() + "/" + 
						"doubleKills:" + ps.getDoubleKills() + "/" + 
						"firstBloodAssist:" + ps.isFirstBloodAssist() + "/" + 
						"firstBloodKill:" + ps.isFirstBloodKill() + "/" + 
						"goldEarned:" + ps.getGoldEarned() + "/" +
						"item0:" + ps.getItem0() + "/" + 
						"item1:" + ps.getItem1() + "/" + 
						"item2:" + ps.getItem2() + "/" +
						"item3:" + ps.getItem3() + "/" + 
						"item4:" + ps.getItem4() + "/" + 
						"item5:" + ps.getItem5() + "/" +
						"item6:" + ps.getItem6() + "/" + 
						"kills:" + ps.getKills() + "/" + 
						"largestKillSpree:" + ps.getLargestKillingSpree() + "/" + 
						"inhibKills:" + ps.getInhibitorKills() + "/" + 
						"largestCritStrike:" + ps.getLargestCriticalStrike() +"/" + 
						"magicDmgDealt:" + ps.getMagicDamageDealt() + "/" + 
						"magicDmgDealtChamps:" + ps.getMagicDamageDealtToChampions() +"/" + 
						"magicDmgTaken:" + ps.getMagicalDamageTaken() +"/" + 
						"minionsKilled:" + ps.getTotalMinionsKilled() + "/" + 
						"neutralMinionsKilled:" + ps.getNeutralMinionsKilled() + "/" + 
						"neutralMinionsKilledEnemyJngl:" + ps.getNeutralMinionsKilledEnemyJungle() +"/" + 
						"neutralMinionsKilledTeamJngl:" + ps.getNeutralMinionsKilledTeamJungle() + "/" + 
						"nodeCaptures:" + ps.getNodeCapture() + "/" + 
						"nodeCaptureAssist:" + ps.getNodeCaptureAssist() + "/" + 
						"nodeNeutralized:" + ps.getNodeNeutralize() + "/" + 
						"nodeNeutralizedAssist:" + ps.getNodeNeutralizeAssist() + "/" + 
						"objectivePlayerScore:" + ps.getObjectivePlayerScore() + "/" + 
						"pentaKills:" + ps.getPentaKills() + "/" + 
						"physicalDmgDealt:" + ps.getPhysicalDamageDealt() + "/" + 
						"PhysicalDmgDealtToChampions:" + ps.getPhysicalDamageDealtToChampions() +"/" + 
						"physicalDmgTaken:" + ps.getPhysicalDamageTaken() + "/" + 
						"quadraKills:" + ps.getQuadraKills() + "/" + 
						"sightWardsBoughtInGame:" + ps.getSightWardsBoughtInGame() + "/" + 
						"teamObjective:" + ps.getTeamObjective() + "/" + 
						"totalDmgDealt:" + ps.getTotalDamageDealt() + "/" + 
						"totalDmgDealtToChampions:" + ps.getTotalDamageDealtToChampions() + "/" + 
						"totalDmgTaken:" + ps.getTotalDamageTaken() + "/" + 
						"totalHeal:" + ps.getTotalHeal() + "/" + 
						"totalPlayerScore:" + ps.getTotalPlayerScore() + "/" + 
						"totalScoreRank:" + ps.getTotalScoreRank() + "/" + 
						"totalTimeCrowdControlDealt:" + ps.getTimeCCingOthers() + "/" + 
						"totalUnitHealed:" + ps.getTotalUnitsHealed() + "/" + 
						"towerKills:"  +ps.getTurretKills() + "/" + 
						"tripleKills:" + ps.getTripleKills() + "/" + 
						"trueDmgTaken:" + ps.getTrueDamageTaken() + "/" + 
						"trueDmgDealt:" + ps.getTrueDamageDealt() + "/" + 
						"trueDmgDealtChamps:" + ps.getTrueDamageDealtToChampions() + "/" + 
						"unrealKills:" + ps.getUnrealKills() + "/" + 
						"visionWardsBoughtInGame:" + ps.getVisionWardsBoughtInGame() + "/" + 
						"wardsKilled:" + ps.getWardsKilled() + "/" + 
						"wardsPlaced:" + ps.getWardsPlaced() +"/" + 
						"winner:" + ps.isWin() + "/";
		
				
		return returnString;
	}
	
	
//non-private accessors/mutators
	public SummonerData setMatchReferenceList(MatchReferenceList matches){
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
	//TODO: implement getAnalysis, getProfile
	public List<String> getMatchHistory(List<String> request){
		log("SummonerData: retrieving match hisotry for " + summonerName);
		int numberOfMatches = Integer.parseInt(request.get(2));
		int startRequest = Integer.parseInt(request.get(3));
		int stopRequest = Integer.parseInt(request.get(4));
		List<String> response = new ArrayList<>(numberOfMatches*10);
		log("SummonerData: retrieving match data [" + startRequest + " , " + stopRequest + ")");
		int i = startRequest;
		try{
			for(i = startRequest; i < stopRequest; i++){
				String aggregatedMatchDetailsString = aggregateMatchData(matchList.get(i), i);
				//log("SummonerData: Match requested -> " + matchList.get(i).getGameId() + "\n" + aggregatedMatchDetailsString);
				response.addAll(Arrays.asList(aggregatedMatchDetailsString.split("PLAYERS")));
			}
		} catch(IndexOutOfBoundsException e){
			e.printStackTrace();
			log(Level.WARNING, "Match " + i + " not found.", e);
			return null;
		} return response;
	}
	
	public List<String> getAnalysis(List<String> request){ return null; }
	
	public List<String> getProfile(List<String> request){ return null; } 
	
	public List<String> getMasteryProfileData(){ return masteryProfileData; }
	
	public String getRankedProfileData(){ return rankedProfileData; }
	
	public String getSummonerName(){ return summonerName; }
	
	public long getSummonerId(){ return summonerId; }
	
	public MatchReferenceList getMatchReferenceList(){ return matchReferenceList; }
	
	public long getMostRecentMatchId(){ return matchList.get(0).getGameId(); }
	
	
}
