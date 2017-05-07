package com.rain.app.server.redux;

import com.rain.app.service.riot.api.endpoints.match.dto.ParticipantStats;

public class AggregatedChampionData {
	
	private ParticipantStats aggregatedStats;

	public AggregatedChampionData(ParticipantStats ps) {
		this.aggregatedStats = ps;
	}
	
	public void addNewMatchStats(ParticipantStats ps){
		ps.addStats(ps);
	}
	
	@Override
	public String toString(){
		return aggregatedStats.toString();
	}
}
