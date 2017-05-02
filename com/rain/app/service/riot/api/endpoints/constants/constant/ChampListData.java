package com.rain.app.service.riot.api.endpoints.constants.constant;

public enum ChampListData {
	ALL("all"),
	ALLYTIPS("allytips"),
	ALTIMAGES("altimages"),
	BLURB("blurb"),
	ENEMYTIPS("enemytips"),
	IMAGE("image"),
	INFO("info"),
	LORE("lore"),
	PARTYPE("partype"),
	PASSIVE("passive"),
	RECOMMENDED("recommended"),
	SKINS("skins"),
	SPELLS("spells"),
	STATS("stats"),
	TAGS("tags");

	private String name;

	ChampListData(String name) {
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
