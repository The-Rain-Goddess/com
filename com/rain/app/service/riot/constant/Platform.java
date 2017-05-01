package com.rain.app.service.riot.constant;

import java.util.NoSuchElementException;

import com.rain.app.service.riot.api.RiotApi;

public enum Platform {
	BR("BR1", "br"),
	EUNE("EUN1", "eune"),
	EUW("EUW1", "euw"),
	JP("JP1", "jp"),
	KR("KR", "kr"),
	LAN("LA1", "lan"),
	LAS("LA2", "las"),
	NA("NA1", "na"),
	OCE("OC1", "oce"),
	PBE("PBE1", "pbe"),
	RU("RU", "ru"),
	TR("TR1", "tr"),
	GLOBAL("GLOBAL", "global");

	private String id;
	private String name;

	public static Platform getPlatformById(String id) {
		for (Platform platform : Platform.values()) {
			if (platform.getId().toLowerCase().equals(id.toLowerCase())) {
				return platform;
			}
		}
		RiotApi.log.warning("Unknown platform ID: " + id);
		throw new NoSuchElementException("Unknown platform ID: " + id);
	}

	public static Platform getPlatformByName(String name) {
		for (Platform platform : Platform.values()) {
			if (platform.getName().toLowerCase().equals(name.toLowerCase())) {
				return platform;
			}
		}
		RiotApi.log.warning("Unknown platform name: " + name);
		throw new NoSuchElementException("Unknown platform name: " + name);
	}

	Platform(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getHost() {
		return "https://" + getId().toLowerCase() + ".api.riotgames.com";
	}

	@Override
	public String toString() {
		return getName();
	}
}
