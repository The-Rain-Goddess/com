package com.rain.app.service.riot.api.endpoints.constants.constant;

public enum MasteryListData {
	ALL("all"),
	IMAGE("image"),
	MASTERY_TREE("masteryTree"),
	PREREQ("prereq"),
	RANKS("ranks"),
	SANITIZED_DESCRIPTION("sanitizedDescription"),
	TREE("tree");

	private String name;

	MasteryListData(String name) {
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
