package com.rain.app.server.redux;

import com.rain.app.service.riot.api.endpoints.match.dto.ParticipantStats;

public class AggregatedChampionData extends Object{
	
	private ParticipantStats unaggregatedStats;
	private long championId;

	public AggregatedChampionData(ParticipantStats ps, long championId) {
		this.unaggregatedStats = ps;
		this.championId = championId;
		if(this.unaggregatedStats.isWin())
			this.unaggregatedStats.setWins(1);
		else
			this.unaggregatedStats.setLosses(1);
	}
	
	public AggregatedChampionData(long championId, boolean isWin){
		this.unaggregatedStats = new ParticipantStats();
		this.championId = championId;
		if(isWin){
			this.unaggregatedStats.setWins(1);
			this.unaggregatedStats.setLosses(0);
		} else{
			this.unaggregatedStats.setWins(0);
			this.unaggregatedStats.setLosses(1);
		}
	}
	
	public void addNewMatchStats(ParticipantStats ps){
		this.unaggregatedStats.addStats(ps);
	}
	
	@Override
	public String toString(){
		return aggregatePlayerStats(unaggregatedStats);
	}
	
	private String aggregatePlayerStats(ParticipantStats ps){
		String returnString = 
						
						"totalSessionsPlayed:" + ps.getTotalSessionsPlayed() + "/" +
						"totalSessionsLost:" + ps.getLosses() + "/" +
						"totalSessionsWon:" + ps.getWins() + "/" +
						"assists:" + ps.getAssists() + "/" + 
						"champ:" + Server.getChampionNameFromId(championId) + "/" + 
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
						"winner:" + ps.isWin() + "/" +
						
						"avgAssists:" + ps.getAverageAssists() + "/" +
						"avgKills:" + ps.getAverageKills() + "/" +
						"avgPlayerScore:" + ps.getAverageCombatPlayerScore() + "/" +
						"avgDeaths:" + ps.getAverageDeaths() + "/" +
						"avgTeamObj:" + ps.getAverageTeamObjective() + "/" +
						//"botGamesPlayed:" + ps.getBotGamesPlayed() + "/" +
						"killingSprees:" + ps.getKillingSprees()  + "/" +
						"maxAssists:" + ps.getMaxAssists() + "/" +
						"maxKills:" + ps.getMaxKills() + "/" +
						"largestCrit:" + ps.getLargestCriticalStrike() + "/" +
						"largestKillingSpree:" + ps.getLargestKillingSpree() + "/" +
						"maxDeaths:" + ps.getMaxDeaths() + "/" +
						"maxTeamObj:" + ps.getMaxTeamObj() + "/" +
						//"maxTime:" + ps.getMaxTimePlayed() + "/" +
						"maxTimeLive:" + ps.getMaxTimeAlive() + "/" +
						"mostChampKillSession:" + ps.getMaxKills() + "/" +
						//"mostSpellsCast:"+  ps.getMostSpellsCast() + "/" +
						//"normalGames:" + ps.getNormalGamesPlayed() + "/" +
						//"rankedPremade:" + ps.getRankedPremadeGamesPlayed() + "/" +
						//"rankedSolo:" + ps.getRankedSoloGamesPlayed() + "/" +
						"totalAssists:" +ps.getAssists() + "/" +
						"totalKills:" +ps.getKills() + "/" +
						"totalDeaths:" + ps.getDeaths() + "/" + 
						"totalDmgDealt:" + ps.getTotalDamageDealt() + "/" +
						"totalDmgTaken:" + ps.getTotalDamageTaken() + "/" +
						"doubleKills:"  +ps.getDoubleKills() + "/" +
						"totalFirstBlood:" +ps.getFirstBloodKills() + "/" +
						"totalGoldEarned:" + ps.getGoldEarned() + "/" +
						"totalGoldSpent:" + ps.getGoldSpent() + "/" +
						"totalHeal:" + ps.getTotalHeal() + "/" +
						"totalMagicDmgDealt:" + ps.getMagicDamageDealt() + "/" +
						"totalMagicDmgDealtToChamps:" + ps.getMagicDamageDealtToChampions() + "/" +
						"totalMinionKills:" +ps.getTotalMinionsKilled() + "/" +
						"totalNeutralKills:"+ps.getNeutralMinionsKilled() + "/" +
						"totalPentaKills:"  +ps.getPentaKills() + "/" +
						"totalPhysicalDmgDealtToChamps:" +ps.getPhysicalDamageDealtToChampions() + "/" +
						"totalPhysicalDmgDealtToChamps:" +ps.getPhysicalDamageDealtToChampions() + "/" +
						"totalQuadraKills:" +ps.getQuadraKills() + "/" +
						"totalTripleKills:" +ps.getTripleKills() + "/" +
						"totalTurretKills:" +ps.getTurretKills();
		return returnString;
	}
}
