package com.rain.app.server;
import java.util.List;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Match.MatchDetail;
import net.rithms.riot.dto.Match.Participant;
import net.rithms.riot.dto.Match.ParticipantIdentity;
import net.rithms.riot.dto.Match.ParticipantStats;
import net.rithms.riot.dto.MatchList.MatchList;
import net.rithms.riot.dto.MatchList.MatchReference;
import net.rithms.riot.dto.Summoner.Summoner;


public class RiotTest {

	public static void main(String[] args) throws RiotApiException {
		RiotApi api = new RiotApi("fb22315c-06cd-4f26-91ed-f0912a72a78d");
		api.setRegion(Region.NA);

		Summoner summoner = api.getSummonerByName("theraingoddess"); //+1call
		long id = summoner.getId();
		MatchList ml = api.getMatchList(id); //+1call
		List<MatchReference> mr = ml.getMatches();
		for(int i = 0; i< 8; i++){
			int champId = (int) mr.get(i).getChampion();
			//Champion temp = api.getDataChampion(champId); //+1call
			//System.out.println(temp.getName()+ " , " + mr.get(i).getMatchId());
			MatchDetail temp3 = api.getMatch(mr.get(i).getMatchId(), true); //+5calls
			List<Participant> part = temp3.getParticipants();
			List<ParticipantIdentity> part2 = temp3.getParticipantIdentities();
			for(int j = 0; j<10; j++){
				//System.out.println(j);
				if(part2.get(j).getPlayer().getSummonerId() == (int)id){
					ParticipantStats ps = part.get(j).getStats();
					String temp = 	
									"assists:" + ps.getAssists() + "/" + 
									"champion:" + api.getDataChampion(champId).getName() + "/" +
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
					System.out.println(temp);
				}
			}	
		}
	}
}
