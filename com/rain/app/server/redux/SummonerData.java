package com.rain.app.server.redux;

import java.util.List;

import com.rain.app.server.Rapi;
import com.rain.app.service.riot.api.endpoints.match.dto.Match;
import com.rain.app.service.riot.api.endpoints.match.dto.MatchReference;
import com.rain.app.service.riot.api.endpoints.match.dto.Participant;
import com.rain.app.service.riot.api.endpoints.match.dto.ParticipantIdentity;
import com.rain.app.service.riot.api.endpoints.match.dto.ParticipantStats;


public class SummonerData {
	private List<MatchReference> ranked_match_reference;
	private List<Match> ranked_match_details;
	
	private List<MatchReference> match_reference;
	private List<Match> match_details;
	private List<String> Champion;
	
	private List<String> mastery_profile_data;
	private String ranked_profile_data;

	private String rankedAnalysis;
	
	private int summoner_id;
	private String summoner_name;
	public SummonerData(){}

//constructors	
	public SummonerData(String name, int id
			, List<MatchReference> reference
			, List<MatchReference>rankedReference
			, List<Match> md
			, List<Match> rmd){
		summoner_name = name;
		summoner_id = id;
		match_reference = reference;
		ranked_match_reference = rankedReference;
		match_details = md;
		ranked_match_details = rmd;
	}
	
	public SummonerData(String name, int id			//normals + ranked constructor
			, List<MatchReference> reference
			, List<Match> md
			, List<String> champ){
		summoner_name = name;
		summoner_id = id;
		match_reference = reference;
		match_details = md;
		Champion = champ;
	}
	
//Overrides
	@Override
	public String toString(){
		String returnString = "";
		returnString = 	":" + summoner_id + 
						":" + summoner_name;
		for(int i = 0; i < match_reference.size(); i++){
			//returnString = returnString + ":" + match_reference.get(i).toString();
		}
		for(int j = 0; j < match_details.size(); j++){
			returnString = returnString + ":" + match_details.get(j).toString();
		}
		for(int k = 0; k < Champion.size(); k++){
			returnString = returnString + ":" + Champion.get(k);
		}
		return returnString;
	}
	
//non-private accessors/mutators	
	public String getMatchesFromMemory(int start, int stop){
		int i=0;
		String passString = "";
		String tempString;
		String otherPlayers = null;
		try{
			tempString = ""; // string for match detail aggregation
			//passString = ""; // string to return
			
			for(i = start; i < stop; i++){ 		//goes through each match
				List<Participant> pPlayers = match_details.get(i).getParticipants();
				List<ParticipantIdentity> pIdentity = match_details.get(i).getParticipantIdentities();
				int teamDmg = 0, enemyTeamDmg = 0;
				
				for(int j = 0; j<10; j++){			//this loop finds the requested player's data in the match
					if(pIdentity.get(j).getPlayer().getSummonerId() == summoner_id){
						ParticipantStats ps = pPlayers.get(j).getStats();
						tempString = aggregateDataFromMatch(ps) + 
								"champion:" + Champion.get(i) + "/" + 								//57
								"sspell1:" + pPlayers.get(j).getSpell1Id() +  "/" +					//58
								"sspell2:" + pPlayers.get(j).getSpell2Id() + "/" +					//59
								"matchLength:" + match_details.get(i).getGameDuration() + "/" +	//60
								"matchId:" + match_details.get(i).getGameId() + "/" +				//61
								"matchMode:" + match_details.get(i).getGameMode() + "/" +			//62
								"matchType:" + match_details.get(i).getGameType() + "/" + 			//63
								"matchStartTime:" + match_details.get(i).getGameCreation() + "/" +	//64
								"queueType:" + match_details.get(i).getQueueId() + "/" +			//65
								"teamId:" +  pPlayers.get(j).getTeamId() + "/" ; 					//66
								
						for(int k = 0; k<10; k++){ //this loop is to get total team dmg and total enemy team dmg
							if(pPlayers.get(k).getTeamId()==pPlayers.get(j).getTeamId())
								teamDmg+= (pPlayers.get(k).getStats().getTotalDamageDealtToChampions());
							else
								enemyTeamDmg += (pPlayers.get(k).getStats().getTotalDamageDealtToChampions());
						} tempString = tempString +  
							"totalTeamDmg:" + teamDmg + "/" + 
							"totalEnemyDmg:" + enemyTeamDmg + "/";
						passString = passString + tempString;
					
					} else{ //Other Players
						String otherPlayersString = null;
						ParticipantStats ps = pPlayers.get(j).getStats();
						otherPlayersString = aggregateDataFromMatch(ps) + 
								"champion:" + Rapi.getChampName(pPlayers.get(j).getChampionId()) + "/" + 	//57
								"sspell1:" + pPlayers.get(j).getSpell1Id() +  "/" +							//58
								"sspell2:" + pPlayers.get(j).getSpell2Id() + "/" +							//59
								"matchLength:" + match_details.get(i).getGameDuration() + "/" +			//60
								"matchId:" + match_details.get(i).getGameId() + "/" +						//61
								"matchMode:" + match_details.get(i).getGameMode() + "/" +					//62
								"matchType:" + match_details.get(i).getGameType() + "/" + 					//63
								"matchStartTime:" + match_details.get(i).getGameCreation() + "/" +			//64
								"queueType:" + match_details.get(i).getQueueId() + "/" +					//65
								"teamId:" +  pPlayers.get(j).getTeamId() + "/" + 							//66
								
								"playerName: " + pIdentity.get(j).getPlayer().getSummonerName() + "/"; 
								
						otherPlayers = otherPlayers + "PLAYERS" + otherPlayersString;
						
					}
				}
			} 
		} catch(IndexOutOfBoundsException e){	
			e.printStackTrace();
			System.out.println("Summoner: error at: " + i + ", stop at: " + stop);
			//Rapi mRapi = new Rapi(summoner_name);
			//TODO: addOlderMatchesToMemory(i, mRapi.getOlderMatchs(i, stop), mRapi.getChampionNames());
			
			//restarts the function
			getMatchesFromMemory(i, stop);
			
		} return passString + otherPlayers;
	}
	
	//add data to Matchs and matchreference
	//updating data
	public boolean addMatchesToMemory(int start, List<Match> list, List<MatchReference> listReference, List<String> champList){
		for(int i = list.size()-1; i > -1; i--){
			match_details.add(0,list.get(i));  	//puts the last item in input list into first item in history
			match_reference.add(0, listReference.get(i));
			Champion.add(0, champList.get(i));
		}	return true;
	}
	//getting more data
	public boolean addMatchesToMemory(List<Match> list, List<MatchReference> listReference, List<String> champList){
		for(int i = 0; i < list.size(); i++){
			match_details.add(list.get(i));  	//puts the first item in input list into last item in history
			match_reference.add(listReference.get(i));
		}	return true;
	}
	
	public boolean addOlderMatchesToMemory(int offset, List<Match> list, List<String> champList){
		for(int i = 0; i < list.size(); i++){
			match_details.add(list.get(i));  	//puts the first item in input list into last item in history
			//match_reference.add(listReference.get(i));
			Champion.add(champList.get(i));
		}	return true;
	}
	
	public String getRankedMatchesFromMemory(int start, int stop){
		String tempString = ""; // string for match detail aggregation
		String passString = ""; // string to return
		for(int i = start; i < stop; i++){ 		//goes through each match
			List<Participant> pPlayers = ranked_match_details.get(i).getParticipants();
			List<ParticipantIdentity> pIdentity = match_details.get(i).getParticipantIdentities();
			for(int j = 0; j<10; j++){			//this loop finds the requested player's data in the match
				if(pIdentity.get(j).getPlayer().getSummonerId() == summoner_id){
					ParticipantStats ps = pPlayers.get(j).getStats();
					tempString = aggregateDataFromMatch(ps) + 
							"champ:" + pPlayers.get(j).getChampionId();
					passString = passString + "|MATCH:" + i + "|" + tempString;
				}
			}
		} return passString;	
	}
	
	public void setRankedProfileData(String updatedData){
		ranked_profile_data = updatedData;
	}
	
	public void setMasteryProfileData(List<String> list){
		mastery_profile_data = list;
	}
	
	public String getRankedAnalysis(){ return rankedAnalysis; }
	
	public void updateRankedAnalysis(String updatedData){ 
		rankedAnalysis = updatedData;
	}
	
	public String aggregateDataFromMatch(ParticipantStats ps){
		String stringToReturn = 
			"assists:" + ps.getAssists() + "/" + 
			//"champion:" + api.getDataChampion(champId).getName() + "/" +
			"champLevel:" + ps.getChampLevel() + "/" + 
			"cs:" + (ps.getTotalMinionsKilled() + ps.getNeutralMinionsKilledEnemyJungle() + ps.getNeutralMinionsKilledTeamJungle()) + "/" +
			"deaths:" + ps.getDeaths() + "/" + 		  
			"dmgToChamp:" + ps.getTotalDamageDealtToChampions() + "/" + 
			"dmgTaken:" + ps.getTotalDamageTaken() + "/" + 
			"doubleKills:" + ps.getDoubleKills() + "/" + 
			"firstBloodAssist:" + ps.isFirstBloodAssist() + "/" + 
			"firtBloodKill:" + ps.isFirstBloodKill() + "/" + 
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
		return stringToReturn;	
	}

//get methods below	
	public List<String> getMatchHistory(){ return null; }
	
	public List<String> getAnalysis(){ return null; }
	
	public List<String> getProfile(){ return null; } 
	
	public List<String> getMasteryProfileData(){ return mastery_profile_data; }
	
	public String getRankedProfileData(){ return ranked_profile_data; }
	
	public String getSummonerName(){ return summoner_name; }
	
	public int getSummonerId(){ return summoner_id; }
	
	public List<Match> getMatchDetails(){ return match_details; }
	
	public long getMostRecentMatchId(){ return match_reference.get(0).getGameId(); }
	
	public long getMostRecentRankedMatchId(){ return ranked_match_reference.get(0).getGameId(); }
	
	public List<MatchReference> getMatchReferenceList(){ return match_reference; }
	
	public List<MatchReference> getRankedMatchReference(){ return ranked_match_reference; }
}
