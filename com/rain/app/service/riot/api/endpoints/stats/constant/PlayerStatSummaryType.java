package com.rain.app.service.riot.api.endpoints.stats.constant;

public enum PlayerStatSummaryType {
	AramUnranked5x5("ARAM / Howling Abyss"),
	Ascension("Ascension"),
	Bilgewater("Black Market Brawlers"),
	CAP5x5("Team Builder"),
	CoopVsAI("Summoner's Rift/Crystal Scar Bots"),
	CoopVsAI3x3("Twisted Treeline Bots"),
	CounterPick("Nemesis"),
	FirstBlood1x1("Snowdown Showdown 1x1"),
	FirstBlood2x2("Snowdown Showdown 2x2"),
	Hexakill("Twisted Treeline 6x6 Hexakill"),
	KingPoro("King Poro"),
	NightmareBot("Summoner's Rift Nightmare Bots"),
	OdinUnranked("Dominion/Crystal Scar"),
	OneForAll5x5("One for All"),
	RankedFlexSR("Ranked Flex Summoner's Rift"),
	RankedFlexTT("Ranked Flex Twisted Treeline"),
	RankedSolo5x5("Summoner's Rift Ranked Solo"),
	RankedTeam3x3("Twisted Treeline Ranked Team"),
	RankedTeam5x5("Summoner's Rift Ranked Team"),
	Siege("Nexus Siege"),
	SummonersRift6x6("Hexakill"),
	Unranked("Summoner's Rift Unranked"),
	Unranked3x3("Twisted Treeline Unranked"),
	URF("Ultra Rapid Fire"),
	URFBots("Ultra Rapid Fire Bots");

	private String name;

	PlayerStatSummaryType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}
}
