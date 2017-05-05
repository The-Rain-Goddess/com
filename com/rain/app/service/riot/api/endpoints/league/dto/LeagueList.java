package com.rain.app.service.riot.api.endpoints.league.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class LeagueList extends Dto implements Serializable {

	private static final long serialVersionUID = 1878237445691308532L;

	private List<LeagueEntry> entries;
	private String name;
	private String queue;
	private String tier;

	public List<LeagueEntry> getEntries() {
		return entries;
	}

	/**
	 * Utility method to get the entry by {@code summonerId}.
	 * 
	 * @param summonerId
	 *            Summoner ID
	 * @return Entry from {@link #getEntries()} matching {@code summonerId}, or {@code null} if there is no such entry.
	 */
	public LeagueEntry getEntryBySummonerId(long summonerId) {
		List<LeagueEntry> entries = getEntries();
		if (entries != null) {
			String summonerIdString = String.valueOf(summonerId);
			for (LeagueEntry entry : entries) {
				if (entry.getPlayerOrTeamId().equals(summonerIdString)) {
					return entry;
				}
			}
		}
		return null;
	}

	/**
	 * Utility method to get the entry by {@code summonerName}.
	 * 
	 * @param summonerName
	 *            Summoner name
	 * @return Entry from {@link #getEntries()} matching {@code summonerName}, or {@code null} if there is no such entry.
	 */
	public LeagueEntry getEntryBySummonerName(String summonerName) {
		List<LeagueEntry> entries = getEntries();
		if (entries != null) {
			for (LeagueEntry entry : entries) {
				if (entry.getPlayerOrTeamName().equalsIgnoreCase(summonerName)) {
					return entry;
				}
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public String getQueue() {
		return queue;
	}

	public String getTier() {
		return tier;
	}

	@Override
	public String toString() {
		return getName();
	}
}
