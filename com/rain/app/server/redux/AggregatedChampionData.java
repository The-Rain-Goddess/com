package com.rain.app.server.redux;

import com.rain.app.service.riot.api.endpoints.match.dto.ParticipantStats;

public class AggregatedChampionData extends Object{
	
	private ParticipantStats unaggregatedStats;

	public AggregatedChampionData(ParticipantStats ps) {
		this.unaggregatedStats = ps;
	}
	
	public AggregatedChampionData(){
		this.unaggregatedStats = new ParticipantStats();
	}
	
	public void addNewMatchStats(ParticipantStats ps){
		ps.addStats(ps);
	}
	
	@Override
	public String toString(){
		return aggregatePlayerStats(unaggregatedStats);
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
}
