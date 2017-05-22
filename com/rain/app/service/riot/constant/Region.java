package com.rain.app.service.riot.constant;

import java.util.NoSuchElementException;

import com.rain.app.service.riot.api.RiotApi;

public enum Region {
	BR("br.api.pvp.net", "br"),
	EUNE("eune.api.pvp.net", "eune"),
	EUW("euw.api.pvp.net", "euw"),
	JP("jp.api.pvp.net", "jp"),
	KR("kr.api.pvp.net", "kr"),
	LAS("las.api.pvp.net", "las"),
	LAN("lan.api.pvp.net", "lan"), 
	NA("na1.api.riotgames.com", "na"),
	OCE("oce.api.pvp.net", "oce"),
	PBE("pbe.api.pvp.net", "pbe"),
	RU("ru.api.pvp.net", "ru"),
	TR("tr.api.pvp.net", "tr"),
	GLOBAL("global.api.pvp.net", "global");

	private String endpoint;
	private String region;

	public static Region getRegionByName(String name) {
		for (Region region : Region.values()) {
			if (region.getName().equals(name.toLowerCase())) {
				return region;
			}
		}
		RiotApi.log.warning("Unknown region name: " + name);
		throw new NoSuchElementException("Unknown region name: " + name);
	}

	public static Region getRegionByPlatformId(Platform platformId) {
		return getRegionByName(platformId.getName());
	}

	Region(String endpoint, String region) {
		this.endpoint = endpoint;
		this.region = region;
	}

	public String getEndpoint(boolean withRegion) {
		String url = "https://" + endpoint + "/api/lol/";
		if (withRegion) {
			url += getName();
		}
		return url;
	}

	public String getEndpoint() {
		return getEndpoint(true);
	}

	public String getName() {
		return region;
	}

	@Override
	public String toString() {
		return getName();
	}
}
