package com.rain.app.server.redux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rain.app.service.riot.api.endpoints.champion_mastery.dto.ChampionMastery;
import com.rain.app.service.riot.api.endpoints.champion_mastery.dto.ChampionMasteryList;
import com.rain.app.service.riot.api.endpoints.league.dto.LeagueEntry;
import com.rain.app.service.riot.api.endpoints.league.dto.LeagueList;
import com.rain.app.service.riot.api.endpoints.match.dto.Match;
import com.rain.app.service.riot.api.endpoints.match.dto.MatchReference;
import com.rain.app.service.riot.api.endpoints.match.dto.MatchReferenceList;
import com.rain.app.service.riot.api.endpoints.match.dto.Participant;
import com.rain.app.service.riot.api.endpoints.match.dto.ParticipantStats;
import com.rain.app.service.riot.api.endpoints.match.dto.Player;
import com.rain.app.service.riot.api.endpoints.summoner.dto.Summoner;



public class SummonerData {
	private List<Match> matchList;
	private MatchReferenceList matchReferenceList;
	private List<String> masteryProfileData;
	private ChampionMasteryList championMasteryList;
	private Map<String, List<LeagueList>> leagueMap;
	private Map<Integer, List<AggregatedChampionData>> rankedChampionDataMap;
	private long summonerId;
	private String summonerName;
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private final int WIN_DATA_INDEX = 1, LOSS_DATA_INDEX = 2, ALL_DATA_INDEX = 0;

//constructors	
	public SummonerData(String name, Summoner summoner){
		log("SummonerData: " + "Constructor called.");
		this.summonerId = summoner.getId();
		this.summonerName = name;
		this.matchList = new ArrayList<>();
		log("SummonerData: " + summonerName + " was created.");
	}

//private a/m
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
			Player pIdentity = match.getParticipantIdentities().get(i).getPlayer();
			String stats = 	getMatchDetails(player.getStats(), matchReferenceList.getMatches().get(matchIndex)) +
							"champion:" + Server.getChampionNameFromId(player.getChampionId()) + "/" + 
							getSummonerSpellIds(player) + 
							getMatchPeripherals(match) + 
							"summonerId: " + pIdentity.getSummonerId() + "/";;
			if(pIdentity.getSummonerId()==summonerId)
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
			out = (i==list.size()-1) ? (out + list.get(i)) : (out + list.get(i) + "PLAYERS");
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
	
	
	private String getProfileSummary(){
		String profileSummary = "";
		log("SummonerData: Aggregating profile summary.");
		for(Map.Entry<String, List<LeagueList>> entry : leagueMap.entrySet()){
			for(LeagueList league : entry.getValue()){
				profileSummary = profileSummary + "" + league.getQueue() + ":" + league.getTier();
				LeagueEntry player = league.getEntryBySummonerId(summonerId);
				profileSummary = profileSummary + ":" + player.getRank() + ":" + player.getLeaguePoints() + "LP";
				profileSummary = profileSummary + "/";
			} 
		} log("SummonerData: Profile summary aggregated successfully."); 
		log("SummonerData:" + profileSummary.substring(0, profileSummary.length()-1));
		return profileSummary.substring(0, profileSummary.length()-1);
	}
	
	private List<String> getChampionMasterySummary(int numberOfMasteriesToRetrieve){
		log("SummonerData: Aggregating mastery summary.");
		List<String> championMasterySummary = new ArrayList<>();
		String tmp = "";
		System.out.println(championMasteryList.getChampionMasteries().size());
		for(int i = 0; i < numberOfMasteriesToRetrieve && i < championMasteryList.getChampionMasteries().size(); i++){
			ChampionMastery cm = championMasteryList.getChampionMasteries().get(i);
			tmp = 	Server.getChampionNameFromId(cm.getChampionId()) + ":" +
					Long.toString(cm.getChampionLevel()) + ":" +
					Long.toString(cm.getChampionPoints()); 
			championMasterySummary.add(tmp);
		} log("SummonerData: Mastery summary aggregated successfully.");  
		return championMasterySummary;
	}
	
	private Map<Integer, List<AggregatedChampionData>> updateRankedChampionDataMap(){
		Map<Integer, List<AggregatedChampionData>> updatedChampionDataMap = new TreeMap<>();
		for(int i = 0; i < matchList.size(); i++){
			for(int j = 0; j < 10; j++){
				if(matchList.get(i).getParticipantIdentities().get(j).getPlayer().getSummonerId() == summonerId){
					Participant player = matchList.get(i).getParticipants().get(j);
					if(updatedChampionDataMap.containsKey(player.getChampionId())){
						updatedChampionDataMap.get(player.getChampionId()).get(ALL_DATA_INDEX).addNewMatchStats(player.getStats());
						if(player.getStats().isWin()){
							updatedChampionDataMap.get(player.getChampionId()).get(WIN_DATA_INDEX).addNewMatchStats(player.getStats());
							//updatedChampionDataMap.get(player.getChampionId()).get(LOSS_DATA_INDEX).incrementMatchNumber()
						} else{
							updatedChampionDataMap.get(player.getChampionId()).get(LOSS_DATA_INDEX).addNewMatchStats(player.getStats());
							//updatedChampionDataMap.get(player.getChampionId()).get(WIN_DATA_INDEX).incrementMatchNumber()
						}
					} else {
						List<AggregatedChampionData> newData = new ArrayList<>();
						newData.add(new AggregatedChampionData(player.getStats(), player.getChampionId())); //all data
						if(player.getStats().isWin()){ // win/loss data
							newData.add(new AggregatedChampionData(player.getStats(), player.getChampionId()));
							newData.add(new AggregatedChampionData(player.getChampionId(), true)); //could throw an error later if adding to uninstanciated fields
						} else{
							newData.add(new AggregatedChampionData(player.getChampionId(), false));
							newData.add(new AggregatedChampionData(player.getStats(), player.getChampionId()));
						} updatedChampionDataMap.put(player.getChampionId(), newData);
					}
				}
			}
		} rankedChampionDataMap = updatedChampionDataMap;
		return rankedChampionDataMap;
	}
	
//non-private accessors/mutators
	public SummonerData updateAnalysis(){
		log("SummonerData: Updating analysis data for " + summonerName + "...");
		rankedChampionDataMap = updateRankedChampionDataMap();
		log("SummonerData: Succesfully updated analysis data.");
		return this;
	}
	
	public SummonerData setMatchReferenceList(MatchReferenceList matches){
		matchReferenceList = matches;
		return this;
	}
	
	public SummonerData addMatch(Match match, boolean addNewMatch){
		if(addNewMatch) //if false, adding old match
			matchList.add(0, match);
		else
			matchList.add(match);
		return this;
	}
	
	public SummonerData addMatch(Match match){
		matchList.add(match);
		return this;
	}
	
	public SummonerData setLeagueMap(Map<String, List<LeagueList>> league){
		this.leagueMap = league;
		return this;
	}
	
	public ChampionMasteryList setChampionMasteryList(ChampionMasteryList mastery){
		this.championMasteryList = mastery;
		return championMasteryList;
	}
	
//accessors
	//TODO: implement getAnalysis
	public List<String> getMatchHistory(List<String> request){
		log("SummonerData: Retrieving match history for " + summonerName);
		int numberOfMatches = Integer.parseInt(request.get(2)), startRequest = Integer.parseInt(request.get(3)), stopRequest = Integer.parseInt(request.get(4)), i = startRequest;
		log("SummonerData: Retrieving match data [" + startRequest + " , " + stopRequest + ")");
		List<String> response = new ArrayList<>(numberOfMatches * 10);
		try{
			for(i = startRequest; i < stopRequest; i++){
				String aggregatedMatchDetailsString = aggregateMatchData(matchList.get(i), i);
				log("SummonerData: Match requested -> \n" + matchList.get(i).getGameId() + "\n\n" + Arrays.asList(aggregatedMatchDetailsString.replaceAll("PLAYERS", "PLAYERS\n").split("PLAYERS")));
				response.addAll(Arrays.asList(aggregatedMatchDetailsString.split("PLAYERS")));
			} return response;
		} catch(IndexOutOfBoundsException e){
			e.printStackTrace();
			log(Level.WARNING, "SummonerData: Match " + i + " not found.", e);
			return null;
		}
	}
	
	public List<String> getProfile(List<String> request){ 
		log("SummonerData: Retrieving profile data for " + summonerName + "...");
		List<String> profile = new ArrayList<>();
		profile.add(getProfileSummary());
		getChampionMasterySummary(5).forEach(profile::add);
		log("SummonerData: Profile -> \n" + ServerUtilities.listToString(profile));
		return profile;
	} 
	
	public List<String> getAnalysis(List<String> request){
		log("SummonerData: Retrieving analysis data for " + summonerName);
		log("SummonerData: Analysis -> \n" + ServerUtilities.mapOfListsToString(rankedChampionDataMap));
		return ServerUtilities.mapToList(rankedChampionDataMap);
	}
	
	public List<String> getMasteryProfileData(){ return masteryProfileData; }
	
	public String getSummonerName(){ return summonerName; }
	
	public long getSummonerId(){ return summonerId; }
	
	public MatchReferenceList getMatchReferenceList(){ return matchReferenceList; }
	
	public long getMostRecentMatchId(){ return matchList.get(0).getGameId(); }
	
	public Map<String, List<LeagueList>> getLeagueMap(){ return this.leagueMap; }
	
	public Map<Integer, List<AggregatedChampionData>> getRankedAnalysis(){ return this.rankedChampionDataMap; }
	
	
}
