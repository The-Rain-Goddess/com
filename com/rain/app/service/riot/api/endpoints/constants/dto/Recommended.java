package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class Recommended extends Dto implements Serializable {
	private static final long serialVersionUID = 8722523053273835114L;

	private List<Block> blocks;
	private String champion;
	private String map;
	private String mode;
	private boolean priority;
	private String title;
	private String type;

	public List<Block> getBlocks() {
		return blocks;
	}

	public String getChampion() {
		return champion;
	}

	public String getMap() {
		return map;
	}

	public String getMode() {
		return mode;
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public boolean isPriority() {
		return priority;
	}

	@Override
	public String toString() {
		return getTitle();
	}
}
