package com.rain.app.service.riot.api.endpoints.constants.constant;

public enum SpellListData {
	ALL("all"),
	COOLDOWN("cooldown"),
	COOLDOWN_BURN("cooldownBurn"),
	COST("cost"),
	COST_BURN("costBurn"),
	COST_TYPE("costType"),
	EFFECT("effect"),
	EFFECT_BURN("effectBurn"),
	IMAGE("image"),
	KEY("key"),
	LEVELTIP("leveltip"),
	MAXRANK("maxrank"),
	MODES("modes"),
	RANGE("range"),
	RANGE_BURN("rangeBurn"),
	RESOURCE("resource"),
	SANITIZED_DESCRIPTION("sanitizedDescription"),
	SANITIZED_TOOLTIP("sanitizedTooltip"),
	TOOLTIP("tooltip"),
	VARS("vars");

	private String name;

	SpellListData(String name) {
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
