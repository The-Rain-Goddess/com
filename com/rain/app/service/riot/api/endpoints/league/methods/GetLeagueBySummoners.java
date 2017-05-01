package com.rain.app.service.riot.api.endpoints.league.methods;

import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.league.LeagueApiMethod;
import com.rain.app.service.riot.api.endpoints.league.dto.League;
import com.rain.app.service.riot.constant.Region;

public class GetLeagueBySummoners extends LeagueApiMethod {

	public GetLeagueBySummoners(ApiConfig config, Region region, String summonerIds) {
		super(config);
		setRegion(region);
		setReturnType(new TypeToken<Map<String, List<League>>>() {
		}.getType());
		setUrlBase(region.getEndpoint() + "/v2.5/league/by-summoner/" + summonerIds);
		addApiKeyParameter();
	}
}