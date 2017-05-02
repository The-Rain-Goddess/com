package com.rain.app.service.riot.api.endpoints.stats.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class RankedStats extends Dto implements Serializable {

	private static final long serialVersionUID = 6544447595249079077L;

	private List<ChampionStats> champions;
	private long modifyDate;
	private long summonerId;

	public List<ChampionStats> getChampions() {
		return champions;
	}

	public long getModifyDate() {
		return modifyDate;
	}

	public long getSummonerId() {
		return summonerId;
	}
}
