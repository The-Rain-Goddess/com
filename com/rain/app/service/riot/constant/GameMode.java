package com.rain.app.service.riot.constant;

public enum GameMode {
	
	ARAM("ARAM"),
	ARSR("ARAM SR"),
	ASCENSION("Ascension"),
	ASSASSINATE("Blood Hunt Assassin"),
	CLASSIC("Classic"),
	DARKSTAR("Darkstar"),
	FIRSTBLOOD("Snowdown Showdown"),
	KINGPORO("King Poro"),
	ODIN("Dominion"),
	ONEFORALL("One for All"),
	SIEGE("Nexus Siege"),
	TUTORIAL("Tutorial");

	private String name;

	GameMode(String name) {
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
