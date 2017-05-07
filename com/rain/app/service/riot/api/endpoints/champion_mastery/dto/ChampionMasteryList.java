package com.rain.app.service.riot.api.endpoints.champion_mastery.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class ChampionMasteryList extends Dto implements Serializable {

	private static final long serialVersionUID = -6236629756009801533L;
	
	private List<ChampionMastery> championMasteryList;
	
	public ChampionMasteryList(List<ChampionMastery> list){
		championMasteryList = list;
	}
	
	public List<ChampionMastery> getChampionMasteries(){
		return championMasteryList;
	}
	
	@Override
	public String toString(){
		return championMasteryList.toString();
	}
}
