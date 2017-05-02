package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class Skin extends Dto implements Serializable {

	private static final long serialVersionUID = -8984891033284072910L;

	private int id;
	private String name;
	private int num;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getNum() {
		return num;
	}

	@Override
	public String toString() {
		return getId() + ": " + getName();
	}
}
