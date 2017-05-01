package com.rain.app.service.riot.constant;

public enum GameType {
	CUSTOM_GAME("Custom"),
	MATCHED_GAME("All"),
	TUTORIAL_GAME("Tutorial");

	private String name;

	GameType(String name) {
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
