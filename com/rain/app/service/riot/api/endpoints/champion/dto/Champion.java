package com.rain.app.service.riot.api.endpoints.champion.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class Champion extends Dto implements Serializable {

	private static final long serialVersionUID = 4342849519353550572L;

	private boolean active;
	private boolean botEnabled;
	private boolean botMmEnabled;
	private boolean freeToPlay;
	private int id;
	private boolean rankedPlayEnabled;

	public int getId() {
		return id;
	}

	public boolean isActive() {
		return active;
	}

	public boolean isBotEnabled() {
		return botEnabled;
	}

	public boolean isBotMmEnabled() {
		return botMmEnabled;
	}

	public boolean isFreeToPlay() {
		return freeToPlay;
	}

	public boolean isRankedPlayEnabled() {
		return rankedPlayEnabled;
	}
	
	@Override
	public String toString(){
		return String.valueOf(getId());
	}
}
