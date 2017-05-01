package com.rain.app.service.riot.api.endpoints.champion.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class ChampionList extends Dto implements Serializable {

	private static final long serialVersionUID = 987562252588662376L;

	private List<Champion> champions;

	public List<Champion> getChampions() {
		return champions;
	}
}
