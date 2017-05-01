package com.rain.app.service.riot.api.endpoints.league.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.endpoints.league.LeagueApiMethod;
import com.rain.app.service.riot.api.endpoints.league.constant.QueueType;
import com.rain.app.service.riot.api.endpoints.league.dto.League;
import com.rain.app.service.riot.constant.Region;

public class GetChallengerLeague extends LeagueApiMethod {

	public GetChallengerLeague(ApiConfig config, Region region, QueueType queueType) {
		super(config);
		setRegion(region);
		setReturnType(League.class);
		setUrlBase(region.getEndpoint() + "/v2.5/league/challenger");
		add(new UrlParameter("type", queueType.name()));
		addApiKeyParameter();
	}
}
