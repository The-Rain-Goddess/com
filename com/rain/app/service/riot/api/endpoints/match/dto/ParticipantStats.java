package com.rain.app.service.riot.api.endpoints.match.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import com.rain.app.service.riot.api.Dto;

public class ParticipantStats extends Dto implements Serializable {

	private static final long serialVersionUID = 7907342025148370788L;

	private int altarsCaptured;
	private int altarsNeutralized;
	private int assists;
	private boolean causedEarlySurrender;
	private int champLevel;
	private int combatPlayerScore;
	private long damageDealtToObjectives;
	private int deaths;
	private int doubleKills;
	private boolean earlySurrenderAccomplice;
	private boolean firstBloodAssist;
	private boolean firstBloodKill;
	private boolean firstInhibitorAssist;
	private boolean firstInhibitorKill;
	private boolean firstTowerAssist;
	private boolean firstTowerKill;
	private boolean gameEndedInEarlySurrender;
	private boolean gameEndedInSurrender;
	private int goldEarned;
	private int goldSpent;
	private int inhibitorKills;
	private int item0;
	private int item1;
	private int item2;
	private int item3;
	private int item4;
	private int item5;
	private int item6;
	private int killingSprees;
	private int kills;
	private int largestCriticalStrike;
	private int largestKillingSpree;
	private int largestMultiKill;
	private boolean leaver;
	private int longestTimeSpentLiving;
	private long magicDamageDealt;
	private long magicDamageDealtToChampions;
	private long magicalDamageTaken;
	private int neutralMinionsKilled;
	private int neutralMinionsKilledEnemyJungle;
	private int neutralMinionsKilledTeamJungle;
	private int nodeCapture;
	private int nodeCaptureAssist;
	private int nodeNeutralize;
	private int nodeNeutralizeAssist;
	private int objectivePlayerScore;
	private int participantId;
	private int pentaKills;
	private long physicalDamageDealt;
	private long physicalDamageDealtToChampions;
	private long physicalDamageTaken;
	private int quadraKills;
	private int sightWardsBoughtInGame;
	private boolean teamEarlySurrendered;
	private int teamObjective;
	private int timeCCingOthers;
	private long totalDamageDealt;
	private long totalDamageDealtToChampions;
	private long totalDamageTaken;
	private long totalHeal;
	private int totalMinionsKilled;
	private int totalPlayerScore;
	private int totalScoreRank;
	private int totalUnitsHealed;
	private int tripleKills;
	private long trueDamageDealt;
	private long trueDamageDealtToChampions;
	private long trueDamageTaken;
	private int turretKills;
	private int unrealKills;
	private int visionScore;
	private int visionWardsBoughtInGame;
	private int wardsKilled;
	private int wardsPlaced;
	private boolean wasAfk;
	private boolean win;
	private int wins = 0;
	private int losses = 0;
	private int totalSessionsPlayed = 1;
	private int afkGames;
	private int earlySurrenderGames;
			//	item#	  numberOfTimesBought
	private Map<Integer, Integer> item0Map = new TreeMap<>();
	private Map<Integer, Integer> item1Map = new TreeMap<>();
	private Map<Integer, Integer> item2Map = new TreeMap<>();
	private Map<Integer, Integer> item3Map = new TreeMap<>();
	private Map<Integer, Integer> item4Map = new TreeMap<>();
	private Map<Integer, Integer> item5Map = new TreeMap<>();
	private Map<Integer, Integer> item6Map = new TreeMap<>();

	private int earlySurrenderAccomplices = 0;
	private int firstBloodAssists = 0;
	private int firstBloodKills = 0;
	private int firstInhibitorAssists = 0;
	private int firstInhibitorKills = 0;
	private int firstTowerAssists = 0;
	private int firstTowerKills = 0;
	private int gamesEndedInEarlySurrender;
	private int gamesEndedInSurrender = 0;
	private int causedEarlySurrenders = 0;
	private int leavers = 0;
	private int maxKills = 0;
	private int maxAssists = 0;
	private int maxDeaths = 0;
	private int maxTeamObj = 0;
	private int maxTimeAlive = 0;
	
//non-private a/m	
	public void addStats(ParticipantStats ps){
		this.maxTeamObj = (this.maxTeamObj > ps.getTeamObjective()) ? this.maxTeamObj : ps.getTeamObjective();
		this.maxTimeAlive = (this.maxTimeAlive > ps.getLongestTimeSpentLiving()) ? this.maxTimeAlive : ps.getLongestTimeSpentLiving();
		this.maxKills = (this.maxKills > ps.getKills()) ? this.maxKills : ps.getKills();
		this.maxDeaths = (this.maxDeaths > ps.getDeaths()) ? this.maxDeaths : ps.getDeaths();
		this.maxAssists = (this.maxAssists > ps.getAssists()) ? this.maxAssists : ps.getAssists();
		this.altarsCaptured += ps.altarsCaptured;
		this.altarsNeutralized += ps.altarsNeutralized;
		this.assists += ps.assists;
		this.setCausedEarlySurrenders((this.causedEarlySurrender) ? 1 : 0);
		this.champLevel += ps.champLevel;
		this.combatPlayerScore += ps.combatPlayerScore;
		this.damageDealtToObjectives = ps.damageDealtToObjectives;
		this.deaths = ps.deaths;
		this.doubleKills = ps.doubleKills;
		this.setEarlySurrenderAccomplices(this.getEarlySurrenderAccomplices() + ((ps.earlySurrenderAccomplice) ? 1 : 0));
		this.setFirstBloodAssists(this.getFirstBloodAssists() + ((ps.firstBloodAssist) ? 1 : 0));
		this.setFirstBloodKills(this.getFirstBloodKills() + ((ps.firstBloodKill) ? 1 : 0));
		this.setFirstInhibitorAssists(this.getFirstInhibitorAssists() + ((ps.firstInhibitorAssist) ? 1 : 0));
		this.setFirstInhibitorKills(this.getFirstInhibitorKills() + ((ps.firstInhibitorKill) ? 1 : 0));
		this.setFirstTowerAssists(this.getFirstTowerAssists() + ((ps.firstTowerAssist) ? 1 : 0));
		this.setFirstTowerKills(this.getFirstTowerKills() + ((ps.firstTowerKill) ? 1 : 0));
		this.setGamesEndedInEarlySurrender(this.getGamesEndedInEarlySurrender() + ((ps.gameEndedInEarlySurrender) ? 1 : 0));
		this.setGamesEndedInSurrender(this.getGamesEndedInSurrender() + ((ps.gameEndedInSurrender) ? 1 : 0));
		
		this.goldEarned += ps.goldEarned;
		this.goldSpent += ps.goldSpent;
		this.inhibitorKills += ps.inhibitorKills;
		
		if(this.item0Map.containsKey(ps.item0))
			this.item0Map.put(ps.item0, this.item0Map.get(ps.item0)+1);
		else
			this.item0Map.put(ps.item0, 1);
		
		if(this.item1Map.containsKey(ps.item1))
			this.item1Map.put(ps.item1, this.item1Map.get(ps.item1)+1);
		else
			this.item1Map.put(ps.item1, 1);
		
		if(this.item2Map.containsKey(ps.item2))
			this.item2Map.put(ps.item2, this.item2Map.get(ps.item2)+1);
		else
			this.item2Map.put(ps.item2, 1);
		
		if(this.item3Map.containsKey(ps.item3))
			this.item3Map.put(ps.item3, this.item3Map.get(ps.item3)+1);
		else
			this.item3Map.put(ps.item3, 1);

		if(this.item4Map.containsKey(ps.item4))
			this.item4Map.put(ps.item4, this.item4Map.get(ps.item4)+1);
		else
			this.item4Map.put(ps.item4, 1);

		if(this.item5Map.containsKey(ps.item5))
			this.item5Map.put(ps.item5, this.item5Map.get(ps.item5)+1);
		else
			this.item5Map.put(ps.item5, 1);
		
		if(this.item6Map.containsKey(ps.item6))
			this.item6Map.put(ps.item6, this.item6Map.get(ps.item6)+1);
		else
			this.item6Map.put(ps.item6, 1);
		
		this.killingSprees += ps.killingSprees;
		this.kills += ps.kills;
		this.setLeavers(this.getLeavers() + ((ps.leaver) ? 1 : 0));
		this.largestCriticalStrike = (this.largestCriticalStrike > ps.largestCriticalStrike) ? this.largestCriticalStrike : ps.largestCriticalStrike;
		this.largestKillingSpree = (this.largestKillingSpree > ps.largestKillingSpree) ? this.largestKillingSpree : ps.largestKillingSpree;
		this.largestMultiKill = (this.largestMultiKill > ps.largestMultiKill) ? this.largestMultiKill : ps.largestMultiKill;
		this.longestTimeSpentLiving = (this.longestTimeSpentLiving > ps.longestTimeSpentLiving) ? this.longestTimeSpentLiving : ps.longestTimeSpentLiving;
		this.magicalDamageTaken += ps.magicalDamageTaken;
		this.magicDamageDealt += ps.magicDamageDealt;
		this.magicDamageDealtToChampions += ps.magicDamageDealtToChampions;
		this.neutralMinionsKilled += ps.neutralMinionsKilled;
		this.neutralMinionsKilledEnemyJungle += ps.neutralMinionsKilledEnemyJungle;
		this.neutralMinionsKilledTeamJungle += ps.neutralMinionsKilledTeamJungle;
		this.nodeCapture += ps.nodeCapture;
		this.nodeCaptureAssist += ps.nodeCaptureAssist;
		this.nodeNeutralize += ps.nodeNeutralize;
		this.nodeNeutralizeAssist += ps.nodeNeutralizeAssist;
		this.objectivePlayerScore += ps.objectivePlayerScore;
		//this.participantId;
		this.pentaKills += ps.pentaKills;
		this.physicalDamageDealt += ps.physicalDamageDealt;
		this.physicalDamageDealtToChampions += ps.physicalDamageDealtToChampions;
		this.physicalDamageTaken += ps.physicalDamageTaken;
		this.quadraKills += ps.quadraKills;
		this.sightWardsBoughtInGame += ps.sightWardsBoughtInGame;
		this.setEarlySurrenderGames(this.getEarlySurrenderGames() + ((ps.teamEarlySurrendered) ? 1 : 0));
		this.teamObjective += ps.teamObjective;
		this.timeCCingOthers += ps.timeCCingOthers;
		this.totalDamageDealt += ps.totalDamageDealt;
		this.totalDamageDealtToChampions += ps.totalDamageDealtToChampions;
		this.totalDamageTaken += ps.totalDamageTaken;
		this.totalHeal += ps.totalHeal;
		this.totalMinionsKilled += ps.totalMinionsKilled;
		this.totalPlayerScore += ps.totalPlayerScore;
		this.totalScoreRank += ps.totalScoreRank;
		this.totalUnitsHealed += ps.totalUnitsHealed;
		this.tripleKills += ps.tripleKills;
		this.trueDamageDealt += ps.trueDamageDealt;
		this.trueDamageDealtToChampions += ps.trueDamageDealtToChampions;
		this.trueDamageTaken += ps.trueDamageTaken;
		this.turretKills += ps.turretKills;
		this.unrealKills += ps.unrealKills;
		this.visionScore += ps.visionScore;
		this.visionWardsBoughtInGame += ps.visionWardsBoughtInGame;
		this.wardsKilled += ps.wardsKilled;
		this.wardsPlaced += ps.wardsPlaced;
		this.setAfkGames(this.getAfkGames() + ((ps.wasAfk) ? 1 : 0));
		this.wins = (ps.isWin()) ? (this.wins+1) : (this.wins+0);
		this.losses = (ps.isWin()) ? (this.losses+0) : (this.losses+1);
		this.totalSessionsPlayed = this.totalSessionsPlayed + 1;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getMaxAssists(){ return this.maxAssists; }
	
	public int getMaxKills() { return this.maxKills; }
	
	public int getMaxDeaths() { return this.maxDeaths; }
	
	public int getTotalSessionsPlayed(){
		return totalSessionsPlayed;
	}
	
	public int getMaxTeamObj() {
		return maxTeamObj;
	}

	public int getMaxTimeAlive() {
		return maxTimeAlive;
	}
	
	public double getAverageAssists(){
		return (double) this.assists / (double) (getTotalSessionsPlayed());
	}
	
	public double getAverageKills(){
		return (double) this.kills / (double) (getTotalSessionsPlayed());
	}
	
	public double getAverageDeaths(){
		return (double) this.deaths / (double) (getTotalSessionsPlayed());
	}
	
	public double getAverageCombatPlayerScore(){
		return (double) this.combatPlayerScore / (double) getTotalSessionsPlayed();
	}
	
	public double getAverageTeamObjective(){
		return (double) this.teamObjective / (double) getTotalSessionsPlayed();
	}
	
	
	
	public int getWins(){ return this.wins; }
	
	public int getLosses(){ return this.losses; } 

	public int getAltarsCaptured() {
		return altarsCaptured;
	}

	public int getAltarsNeutralized() {
		return altarsNeutralized;
	}

	public int getAssists() {
		return assists;
	}

	public int getChampLevel() {
		return champLevel;
	}

	public int getCombatPlayerScore() {
		return combatPlayerScore;
	}

	public long getDamageDealtToObjectives() {
		return damageDealtToObjectives;
	}

	public int getDeaths() {
		return deaths;
	}

	public int getDoubleKills() {
		return doubleKills;
	}

	public int getGoldEarned() {
		return goldEarned;
	}

	public int getGoldSpent() {
		return goldSpent;
	}

	public int getInhibitorKills() {
		return inhibitorKills;
	}

	public int getItem0() {
		return item0;
	}

	public int getItem1() {
		return item1;
	}

	public int getItem2() {
		return item2;
	}

	public int getItem3() {
		return item3;
	}

	public int getItem4() {
		return item4;
	}

	public int getItem5() {
		return item5;
	}

	public int getItem6() {
		return item6;
	}

	public int getKillingSprees() {
		return killingSprees;
	}

	public int getKills() {
		return kills;
	}

	public int getLargestCriticalStrike() {
		return largestCriticalStrike;
	}

	public int getLargestKillingSpree() {
		return largestKillingSpree;
	}

	public int getLargestMultiKill() {
		return largestMultiKill;
	}

	public int getLongestTimeSpentLiving() {
		return longestTimeSpentLiving;
	}

	public long getMagicDamageDealt() {
		return magicDamageDealt;
	}

	public long getMagicDamageDealtToChampions() {
		return magicDamageDealtToChampions;
	}

	public long getMagicalDamageTaken() {
		return magicalDamageTaken;
	}

	public int getNeutralMinionsKilled() {
		return neutralMinionsKilled;
	}

	public int getNeutralMinionsKilledEnemyJungle() {
		return neutralMinionsKilledEnemyJungle;
	}

	public int getNeutralMinionsKilledTeamJungle() {
		return neutralMinionsKilledTeamJungle;
	}

	public int getNodeCapture() {
		return nodeCapture;
	}

	public int getNodeCaptureAssist() {
		return nodeCaptureAssist;
	}

	public int getNodeNeutralize() {
		return nodeNeutralize;
	}

	public int getNodeNeutralizeAssist() {
		return nodeNeutralizeAssist;
	}

	public int getObjectivePlayerScore() {
		return objectivePlayerScore;
	}

	public int getParticipantId() {
		return participantId;
	}

	public int getPentaKills() {
		return pentaKills;
	}

	public long getPhysicalDamageDealt() {
		return physicalDamageDealt;
	}

	public long getPhysicalDamageDealtToChampions() {
		return physicalDamageDealtToChampions;
	}

	public long getPhysicalDamageTaken() {
		return physicalDamageTaken;
	}

	public int getQuadraKills() {
		return quadraKills;
	}

	public int getSightWardsBoughtInGame() {
		return sightWardsBoughtInGame;
	}

	public int getTeamObjective() {
		return teamObjective;
	}

	public int getTimeCCingOthers() {
		return timeCCingOthers;
	}

	public long getTotalDamageDealt() {
		return totalDamageDealt;
	}

	public long getTotalDamageDealtToChampions() {
		return totalDamageDealtToChampions;
	}

	public long getTotalDamageTaken() {
		return totalDamageTaken;
	}

	public long getTotalHeal() {
		return totalHeal;
	}

	public int getTotalMinionsKilled() {
		return totalMinionsKilled;
	}

	public int getTotalPlayerScore() {
		return totalPlayerScore;
	}

	public int getTotalScoreRank() {
		return totalScoreRank;
	}

	public int getTotalUnitsHealed() {
		return totalUnitsHealed;
	}

	public int getTripleKills() {
		return tripleKills;
	}

	public long getTrueDamageDealt() {
		return trueDamageDealt;
	}

	public long getTrueDamageDealtToChampions() {
		return trueDamageDealtToChampions;
	}

	public long getTrueDamageTaken() {
		return trueDamageTaken;
	}

	public int getTurretKills() {
		return turretKills;
	}

	public int getUnrealKills() {
		return unrealKills;
	}

	public int getVisionScore() {
		return visionScore;
	}

	public int getVisionWardsBoughtInGame() {
		return visionWardsBoughtInGame;
	}

	public int getWardsKilled() {
		return wardsKilled;
	}

	public int getWardsPlaced() {
		return wardsPlaced;
	}

	public boolean isCausedEarlySurrender() {
		return causedEarlySurrender;
	}

	public boolean isEarlySurrenderAccomplice() {
		return earlySurrenderAccomplice;
	}

	public boolean isGameEndedInEarlySurrender() {
		return gameEndedInEarlySurrender;
	}

	public boolean isGameEndedInSurrender() {
		return gameEndedInSurrender;
	}

	public boolean isFirstBloodAssist() {
		return firstBloodAssist;
	}

	public boolean isFirstBloodKill() {
		return firstBloodKill;
	}

	public boolean isFirstInhibitorAssist() {
		return firstInhibitorAssist;
	}

	public boolean isFirstInhibitorKill() {
		return firstInhibitorKill;
	}

	public boolean isFirstTowerAssist() {
		return firstTowerAssist;
	}

	public boolean isFirstTowerKill() {
		return firstTowerKill;
	}

	public boolean isLeaver() {
		return leaver;
	}

	public boolean isTeamEarlySurrendered() {
		return teamEarlySurrendered;
	}

	public boolean isWasAfk() {
		return wasAfk;
	}

	public boolean isWin() {
		return win;
	}

	/**
	 * @return the afkGames
	 */
	public int getAfkGames() {
		return afkGames;
	}

	/**
	 * @param afkGames the afkGames to set
	 */
	public void setAfkGames(int afkGames) {
		this.afkGames = afkGames;
	}

	/**
	 * @return the earlySurrenderGames
	 */
	public int getEarlySurrenderGames() {
		return earlySurrenderGames;
	}

	/**
	 * @param earlySurrenderGames the earlySurrenderGames to set
	 */
	public void setEarlySurrenderGames(int earlySurrenderGames) {
		this.earlySurrenderGames = earlySurrenderGames;
	}

	/**
	 * @return the earlySurrenderAccomplices
	 */
	public int getEarlySurrenderAccomplices() {
		return earlySurrenderAccomplices;
	}

	/**
	 * @param earlySurrenderAccomplices the earlySurrenderAccomplices to set
	 */
	public void setEarlySurrenderAccomplices(int earlySurrenderAccomplices) {
		this.earlySurrenderAccomplices = earlySurrenderAccomplices;
	}

	/**
	 * @return the firstBloodAssists
	 */
	public int getFirstBloodAssists() {
		return firstBloodAssists;
	}

	/**
	 * @param firstBloodAssists the firstBloodAssists to set
	 */
	public void setFirstBloodAssists(int firstBloodAssists) {
		this.firstBloodAssists = firstBloodAssists;
	}

	/**
	 * @return the firstBloodKills
	 */
	public int getFirstBloodKills() {
		return firstBloodKills;
	}

	/**
	 * @param firstBloodKills the firstBloodKills to set
	 */
	public void setFirstBloodKills(int firstBloodKills) {
		this.firstBloodKills = firstBloodKills;
	}

	/**
	 * @return the firstInhibitorAssists
	 */
	public int getFirstInhibitorAssists() {
		return firstInhibitorAssists;
	}

	/**
	 * @param firstInhibitorAssists the firstInhibitorAssists to set
	 */
	public void setFirstInhibitorAssists(int firstInhibitorAssists) {
		this.firstInhibitorAssists = firstInhibitorAssists;
	}

	/**
	 * @return the firstInhibitorKills
	 */
	public int getFirstInhibitorKills() {
		return firstInhibitorKills;
	}

	/**
	 * @param firstInhibitorKills the firstInhibitorKills to set
	 */
	public void setFirstInhibitorKills(int firstInhibitorKills) {
		this.firstInhibitorKills = firstInhibitorKills;
	}

	/**
	 * @return the firstTowerAssists
	 */
	public int getFirstTowerAssists() {
		return firstTowerAssists;
	}

	/**
	 * @param firstTowerAssists the firstTowerAssists to set
	 */
	public void setFirstTowerAssists(int firstTowerAssists) {
		this.firstTowerAssists = firstTowerAssists;
	}

	/**
	 * @return the firstTowerKills
	 */
	public int getFirstTowerKills() {
		return firstTowerKills;
	}

	/**
	 * @param firstTowerKills the firstTowerKills to set
	 */
	public void setFirstTowerKills(int firstTowerKills) {
		this.firstTowerKills = firstTowerKills;
	}

	/**
	 * @return the gamesEndedInEarlySurrender
	 */
	public int getGamesEndedInEarlySurrender() {
		return gamesEndedInEarlySurrender;
	}

	/**
	 * @param gamesEndedInEarlySurrender the gamesEndedInEarlySurrender to set
	 */
	public void setGamesEndedInEarlySurrender(int gamesEndedInEarlySurrender) {
		this.gamesEndedInEarlySurrender = gamesEndedInEarlySurrender;
	}

	/**
	 * @return the gamesEndedInSurrender
	 */
	public int getGamesEndedInSurrender() {
		return gamesEndedInSurrender;
	}

	/**
	 * @param gamesEndedInSurrender the gamesEndedInSurrender to set
	 */
	public void setGamesEndedInSurrender(int gamesEndedInSurrender) {
		this.gamesEndedInSurrender = gamesEndedInSurrender;
	}

	/**
	 * @return the causedEarlySurrenders
	 */
	public int getCausedEarlySurrenders() {
		return causedEarlySurrenders;
	}

	/**
	 * @param causedEarlySurrenders the causedEarlySurrenders to set
	 */
	public void setCausedEarlySurrenders(int causedEarlySurrenders) {
		this.causedEarlySurrenders = causedEarlySurrenders;
	}

	/**
	 * @return the leavers
	 */
	public int getLeavers() {
		return leavers;
	}

	/**
	 * @param leavers the leavers to set
	 */
	public void setLeavers(int leavers) {
		this.leavers = leavers;
	}

	public Map<Integer, Integer> getItem0Map() {
		return item0Map;
	}

	public Map<Integer, Integer> getItem1Map() {
		return item1Map;
	}

	public Map<Integer, Integer> getItem2Map() {
		return item2Map;
	}

	public Map<Integer, Integer> getItem3Map() {
		return item3Map;
	}

	public Map<Integer, Integer> getItem4Map() {
		return item4Map;
	}

	public Map<Integer, Integer> getItem5Map() {
		return item5Map;
	}

	public Map<Integer, Integer> getItem6Map() {
		return item6Map;
	}
}
