package com.rain.app.service.riot.api.endpoints.constants.constant;

public enum RuneListData {
	ALL("all"),
	BASIC("basic"),
	COLLOQ("colloq"),
	CONSUME_ON_FULL("consumeOnFull"),
	CONSUMED("consumed"),
	DEPTH("depth"),
	FROM("from"),
	GOLD("gold"),
	HIDE_FROM_ALL("hideFromAll"),
	IMAGE("image"),
	IN_STORE("inStore"),
	INTO("into"),
	MAPS("maps"),
	REQUIRED_CHAMPION("requiredChampion"),
	SANITIZED_DESCRIPTION("sanitizedDescription"),
	SPECIAL_RECIPE("specialRecipe"),
	STACKS("stacks"),
	STATS("stats"),
	TAGS("tags");

	private String name;

	RuneListData(String name) {
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
