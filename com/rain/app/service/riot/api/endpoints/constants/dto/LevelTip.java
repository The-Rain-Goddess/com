package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class LevelTip extends Dto implements Serializable {

	private static final long serialVersionUID = -3061786573868823081L;

	private List<String> effect;
	private List<String> label;

	public List<String> getEffect() {
		return effect;
	}

	public List<String> getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return getLabel().toString();
	}
}
