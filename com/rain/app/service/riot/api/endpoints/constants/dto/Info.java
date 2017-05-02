package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class Info extends Dto implements Serializable {

	private static final long serialVersionUID = -671408952920494867L;

	private int attack;
	private int defense;
	private int difficulty;
	private int magic;

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public int getMagic() {
		return magic;
	}

	@Override
	public String toString() {
		return "[" + getAttack() + "," + getMagic() + "," + getDefense() + "," + getDifficulty() + "]";
	}
}
