package com.rain.app.server;

import java.util.ArrayList;
import java.util.List;

import net.rithms.riot.api.RateLimitException;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Match.MatchDetail;
import net.rithms.riot.dto.Match.Participant;
import net.rithms.riot.dto.Match.ParticipantIdentity;
import net.rithms.riot.dto.Match.ParticipantStats;
import net.rithms.riot.dto.MatchList.MatchList;
import net.rithms.riot.dto.MatchList.MatchReference;
import net.rithms.riot.dto.Stats.AggregatedStats;
import net.rithms.riot.dto.Stats.ChampionStats;
import net.rithms.riot.dto.Stats.RankedStats;
import net.rithms.riot.dto.Summoner.Summoner;

public class Rapi {
	protected String summName;
	protected RiotApi api;
	protected RiotApi api_backup;
	protected long id;
	protected final static String phaseKey  = "fb22315c-06cd-4f26-91ed-f0912a72a78d"; //api-key
	protected final static String phaseKey2 = "RGAPI-1E616A6D-EC4A-46F1-A9BE-E1C620EABB05"; //api-key2
	protected MatchList ml;
	private String passString = "";
	private String tempString = "";
	private List<MatchDetail> match_details;
	private List<MatchDetail> ranked_match_details;
	private List<MatchReference> match_references;
	private List<String> champion_names;
	protected long lastMatchId = 0;

//public contructor	
	public Rapi(String summName) throws RiotApiException{
		this.summName = summName;
		api_backup = new RiotApi(phaseKey2);
		api_backup.setRegion(Region.NA);
		api = new RiotApi(phaseKey);
		api.setRegion(Region.NA);
		Summoner summoner = api.getSummonerByName(summName); //+1call
		try{
			Thread.sleep(1000L);
		} catch(Exception e){
			e.printStackTrace();
		}
		//setters
		this.id = summoner.getId();
		this.match_details = new ArrayList<MatchDetail>(40);
		this.ranked_match_details = new ArrayList<MatchDetail>(30);
		this.match_references = new ArrayList<MatchReference>(40);
		champion_names = new ArrayList<String>(40);
	}

//getters	
	int getId(){ return (int)id; }
	
	long getMostRecentMatchId(){
		try {
			Thread.sleep(1000L);
			if(lastMatchId == 0)
				lastMatchId = api_backup.getMatchList(id).getMatches().get(0).getMatchId();
			else
				return lastMatchId;
		} catch (RiotApiException e) {
			System.err.println("Rapi: Riot Api Error");
			getMostRecentMatchId();
			e.printStackTrace();
		} catch(InterruptedException e){
			e.printStackTrace();
		} return lastMatchId;
	}
	
	long getMostRecentRankedMatchId(){
		try {
			return api.getMatchList(id).getMatches().get(0).getMatchId();
		} catch (RiotApiException e) {
			System.err.println("Rapi: Riot Api Error");
			e.printStackTrace();
		}
		return 0;
	}
	
	//for update segment
	List<MatchDetail> getMatchDetails(long lastId){
		List<MatchDetail> details = new ArrayList<>(10);
		System.err.println("Rapi: Getting New Matches for Update: ");
		try{
			Thread.sleep(1000L);
			ml = api_backup.getMatchList(id);
			List<MatchReference> matchRef = ml.getMatches();
			match_references = matchRef; int count = 0; int champId; String champName;
			
			long matchId = match_references.get(count).getMatchId();
			while(matchId != lastId){
				MatchDetail temp_match;
				champId = (int) match_references.get(count).getChampion();
				Thread.sleep(1000L);
				if(count%2==0){
					temp_match = api_backup.getMatch(matchId);
					champName = api.getDataChampion(champId).getName();
				} else{
					temp_match = api.getMatch(matchId);
					champName = api_backup.getDataChampion(champId).getName();
				}
				champion_names.add(count, champName.replace("'", "").toLowerCase());
				match_details.add(count, temp_match);
				details.add(temp_match);
				count++;
				matchId = match_references.get(count).getMatchId();
			} 
			if(details.size()!=0)	
				System.err.println("Rapi: Retrieved: "  + (details.size()) + " New Matches, With: " + champion_names.get(0));
			else
				System.err.println("Rapi: There were no new matches to add.");
		} catch(RateLimitException e){
			e.printStackTrace();
		} catch(RiotApiException e){
			e.printStackTrace();
		} catch(InterruptedException e){
			e.printStackTrace();
		} catch(IndexOutOfBoundsException e){
			e.printStackTrace();
			System.out.println("Rapi: Details: " + details + "know why");
		}
		return details;
	}
	
	List<MatchDetail> getOlderMatchDetails(int start, int stop){
		System.err.println("Rapi: Starting Older Data Lookup:");
		getHistory(start, stop);
		List<MatchDetail> details = new ArrayList<>(stop + 5);
		for(int i = start, j = 0; i < stop; i++, j++){
			details.add(match_details.get(j));
		} return details;
	}
	
	List<MatchDetail> getMatchDetails(){ return match_details; }
	
	List<MatchReference> getMatchReferences(long lastId){
		List<MatchReference> details = new ArrayList<>(25);
		for(int i = 0; i < match_references.size(); i++){
			details.add(match_references.get(i));
			if(match_references.get(i).getMatchId() == lastId){
				details.remove(match_references.get(i));
				break;
			}	
		}	return details;
	}
	
	List<MatchReference> getMatchReferences(){ return match_references; }
	
	List<String> getChampionNames(){ return champion_names; }
	
	List<MatchDetail> getRankedMatchListDetails(){ return ranked_match_details; }
	
	//for the analysis
	String getRankedStats(){
		String returnString = "";
		int bog = 0;
		try{
			Thread.sleep(1200L);
			RankedStats rankedStats = api.getRankedStats(id); //+1call
			List<ChampionStats> temp_champ_stats = rankedStats.getChampions();
			AggregatedStats temp_agg;
			for(int i = 0; i < temp_champ_stats.size(); i++){
				temp_agg = temp_champ_stats.get(i).getStats();
				bog = temp_champ_stats.get(i).getId();
				if(bog==0)
					bog = 1;
				returnString = returnString + "/cmp:" 
											+ "/champ:" + api_backup.getDataChampion(bog).getName() + "/" +
											aggregateFromStats(temp_agg);
			} //System.out.println("CHAMP STATS:" + returnString);
			return returnString;
		} catch(RateLimitException e){
			e.printStackTrace();
		} catch(RiotApiException e){
			System.err.println("Rapi: Chanmpid: " + bog);
			e.printStackTrace();
		} catch(InterruptedException e){
			e.printStackTrace();
		} return returnString;	
	}
	
	String aggregateFromStats(AggregatedStats a){
		String tmpString = 	"avgAssists:" + a.getAverageAssists() + "/" +
							"avgKills:" + a.getAverageChampionsKilled() + "/" +
							"avgPlayerScore:" + a.getAverageCombatPlayerScore() + "/" +
							"avgDeaths:" + a.getAverageNumDeaths() + "/" +
							"avgTeamObj:" + a.getAverageTeamObjective() + "/" +
							"botGamesPlayed:" + a.getBotGamesPlayed() + "/" +
							"killingSpree:" + a.getKillingSpree()  + "/" +
							"maxAssists:" + a.getMaxAssists() + "/" +
							"maxKills:" + a.getMaxChampionsKilled() + "/" +
							"largestCrit:" + a.getMaxLargestCriticalStrike() + "/" +
							"largestKillingSpree:" + a.getMaxLargestKillingSpree() + "/" +
							"maxDeaths:" + a.getMaxNumDeaths() + "/" +
							"maxTeamObj:" + a.getMaxTeamObjective() + "/" +
							"maxTime:" + a.getMaxTimePlayed() + "/" +
							"maxTimeLive:" + a.getMaxTimeSpentLiving() + "/" +
							"mostChampKillSession:" + a.getMostChampionKillsPerSession() + "/" +
							"mostSpellsCast:"+  a.getMostSpellsCast() + "/" +
							"normalGames:" + a.getNormalGamesPlayed() + "/" +
							"rankedPremade:" + a.getRankedPremadeGamesPlayed() + "/" +
							"rankedSolo:" + a.getRankedSoloGamesPlayed() + "/" +
							"totalAssists:" +a.getTotalAssists() + "/" +
							"totalKills:" +a.getTotalChampionKills() + "/" +
							"totalDmgDealt:" + a.getTotalDamageDealt() + "/" +
							"totalDmgTaken:" + a.getTotalDamageTaken() + "/" +
							"doubleKills:"  +a.getTotalDoubleKills() + "/" +
							"totalFirstBlood:" +a.getTotalFirstBlood() + "/" +
							"totalGold:" + a.getTotalGoldEarned() + "/" +
							"totalHeal:" + a.getTotalHeal() + "/" +
							"totalMagicDmgDealt:" + a.getTotalMagicDamageDealt() + "/" +
							"totalMinionKills:" +a.getTotalMinionKills() + "/" +
							"totalNeutralKills:"+a.getTotalNeutralMinionsKilled() + "/" +
							"totalPentaKills:"  +a.getTotalPentaKills() + "/" +
							"totalPhysicalDmgDealt:" +a.getTotalPhysicalDamageDealt() + "/" +
							"totalQuadraKills:" +a.getTotalQuadraKills() + "/" +
							"totalSessionsPlayed:" +a.getTotalSessionsPlayed() + "/" +
							"totalSessionsLost:" +a.getTotalSessionsLost() + "/" +
							"totalSessionsWon:" +a.getTotalSessionsWon() + "/" +
							"totalTripleKills:" +a.getTotalTripleKills() + "/" +
							"totalTurretKills:" +a.getTotalTurretsKilled();	
		return tmpString;		
	}
	
//used for when you have open files and network connections that garbage cant kil for u
	protected void finallizer() throws Throwable{
		api = null;
		//match_details = null;
	}
	
	private String aggregateDataFromHistory(ParticipantStats ps){
		String returnString = 
				"assists:" + ps.getAssists() + "/" + 
						//"champion:" + api.getDataChampion(champId).getName() + "/" +
						"champLevel:" + ps.getChampLevel() + "/" + 
						"cs:" + (ps.getMinionsKilled() + ps.getNeutralMinionsKilledEnemyJungle() + ps.getNeutralMinionsKilledTeamJungle()) + "/" +
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
						"magicDmgTaken:" + ps.getMagicDamageTaken() +"/" + 
						"minionsKilled:" + ps.getMinionsKilled() + "/" + 
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
						"totalTimeCrowdControlDealt:" + ps.getTotalTimeCrowdControlDealt() + "/" + 
						"totalUnitHealed:" + ps.getTotalUnitsHealed() + "/" + 
						"towerKills:"  +ps.getTowerKills() + "/" + 
						"tripleKills:" + ps.getTripleKills() + "/" + 
						"trueDmgTaken:" + ps.getTrueDamageTaken() + "/" + 
						"trueDmgDealt:" + ps.getTrueDamageDealt() + "/" + 
						"trueDmgDealtChamps:" + ps.getTrueDamageDealtToChampions() + "/" + 
						"unrealKills:" + ps.getUnrealKills() + "/" + 
						"visionWardsBoughtInGame:" + ps.getVisionWardsBoughtInGame() + "/" + 
						"wardsKilled:" + ps.getWardsKilled() + "/" + 
						"wardsPlaced:" + ps.getWardsPlaced() +"/" + 
						"winner:" + ps.isWinner() + "/";
				
		return returnString;
	}
	
//methods below to be called first to populate data struct for methods above
	public String getHistory(int start, int stop){
		//TODO: find the reason why finding older matches cause rapi to start count from 0 to end
		int i = start;
		try{
			Thread.sleep(1200L);
			passString = ""; 
			tempString = "";
			ml = api.getMatchList(id); 					//+1call
			List<MatchReference> mr = ml.getMatches(); 
			match_references = mr;
			
			System.err.println("Rapi: Starting main loop");
			for(i = start; i < stop; i++){
				long matchId = match_references.get(i).getMatchId();
				Thread.sleep(1200L); //slows down the request rate
				MatchDetail temp3;
				if(i%2==1){
					temp3 = api_backup.getMatch(matchId);    //TODO: change this to alternate
				} else{
					temp3 = api.getMatch(matchId);    //TODO: change this to alternate
				}
				
				
				int champId = (int) mr.get(i).getChampion();
				match_details.add(temp3); //removes the need to call api again later for this data
				
				List<Participant> part = temp3.getParticipants();
				List<ParticipantIdentity> part2 = temp3.getParticipantIdentities();
				//System.err.println("Secondary Loop:");
				int teamDmg = 0;
				int enemyTeamDmg = 0;
				for(int j = 0; j<10; j++){ //finds the input player in each match
					if(part.get(j).getTeamId()==1){
						
					}
					if(part2.get(j).getPlayer().getSummonerId() == (int)id){
						ParticipantStats ps = part.get(j).getStats();
						//Thread.sleep(600L);
						String temp_name = api.getDataChampion(champId).getName();
						temp_name.replace("'", "").toLowerCase();
						champion_names.add(temp_name);
						tempString = 	
										aggregateDataFromHistory(ps) + 
										"champion:" + temp_name + "/" +						//57
										"sspell1:" + part.get(j).getSpell1Id() +  "/" +		//58
										"sspell2:" + part.get(j).getSpell2Id() + "/" +		//59
										"matchLength:" + temp3.getMatchDuration() + "/";	//60
										;
						for(int k = 0; k<10; k++){ //this loop is to get total team dmg and total enemy team dmg
							if(part.get(k).getTeamId()==part.get(j).getTeamId())
								teamDmg+= (part.get(k).getStats().getTotalDamageDealtToChampions());
							else
								enemyTeamDmg += (part.get(k).getStats().getTotalDamageDealtToChampions());
						} tempString = tempString +  
										"totalTeamDmg:" + teamDmg + "/" + 
										"totalEnemyDmg:" + enemyTeamDmg + "/";
						System.out.println("Rapi:" + tempString);	
						passString = passString + tempString;
					}
				}
			}
		} catch(RateLimitException e){ //will attempt to retry the method after the retry after time expires
			e.printStackTrace();
			System.err.println("Rapi: Exception: " + e.getMessage() + ", Error Type: " + e.getRateLimitType() + ", Rapi: Retrying in: " + e.getRetryAfter());
			try {
				if(e.getRetryAfter()==0){
					Thread.sleep(500L);
					getHistory(i, stop);
				} else{
					Thread.sleep((e.getRetryAfter() * 1000L) + 11000L);
					getHistory(i, stop);
				}
				
				
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch(RiotApiException e){
			e.printStackTrace();
			System.err.println("Rapi: Big Error:");
		} catch(InterruptedException e){
			e.printStackTrace();
		} finally{
			System.err.println("Rapi: Finished Loop: ");
			//System.out.println("passString: " + passString);
		} return passString;
	}
}
