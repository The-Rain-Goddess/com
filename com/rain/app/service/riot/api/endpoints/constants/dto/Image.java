package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class Image extends Dto implements Serializable {

	private static final long serialVersionUID = 5802273643884377746L;

	private String full;
	private String group;
	private int h;
	private String sprite;
	private int w;
	private int x;
	private int y;

	public String getFull() {
		return full;
	}

	public String getGroup() {
		return group;
	}

	public int getH() {
		return h;
	}

	public String getSprite() {
		return sprite;
	}

	public int getW() {
		return w;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return getSprite();
	}
}
