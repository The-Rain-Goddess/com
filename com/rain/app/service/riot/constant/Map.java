package com.rain.app.service.riot.constant;

import java.util.NoSuchElementException;

import com.rain.app.service.riot.api.RiotApi;




public enum Map {
	ARAM(12, "Howling Abyss"),
	BUTCHERS_BRIDGE(14, "Butcher's Bridge"),
	COSMIC_RUINS(16, "Cosmic Ruins"),
	CRYSTAL_SCAR(8, "The Crystal Scar"),
	HOWLING_ABYSS(12, "Howling Abyss"),
	PROVING_GROUNDS(3, "Proving Grounds"),
	SUMMONERS_RIFT(11, "Summoner's Rift"),
	SUMMONERS_RIFT_AUTUMN(2, "Summoner's Rift"),
	SUMMONERS_RIFT_SUMMER(1, "Summoner's Rift"),
	TWISTED_TREELINE_CURRENT(10, "Twisted Treeline"),
	TWISTED_TREELINE_ORIGINAL(4, "Twisted Treeline");

	private int id;
	private String name;

	public static Map getMapById(int mapId) {
		for (Map map : Map.values()) {
			if (mapId == map.getId()) {
				return map;
			}
		}
		RiotApi.log.warning("Unknown map ID: " + mapId);
		throw new NoSuchElementException("Unknown map ID: " + mapId);
	}

	Map(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}
}
