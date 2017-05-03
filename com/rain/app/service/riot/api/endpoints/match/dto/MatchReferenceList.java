package com.rain.app.service.riot.api.endpoints.match.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class MatchReferenceList extends Dto implements Serializable {

	private static final long serialVersionUID = 939383850419752274L;

	private int endIndex;
	private List<MatchReference> matches;
	private int startIndex;
	private int totalGames;

	public int getEndIndex() {
		return endIndex;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getTotalGames() {
		return totalGames;
	}

	public List<MatchReference> getMatches() {
		return matches;
	}

	@Override
	public String toString() {
		return "[" + getStartIndex() + "," + getEndIndex() + "]";
	}
}
