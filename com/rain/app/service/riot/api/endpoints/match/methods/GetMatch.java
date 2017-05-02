package com.rain.app.service.riot.api.endpoints.match.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.match.MatchApiMethod;
import com.rain.app.service.riot.api.endpoints.match.dto.Match;
import com.rain.app.service.riot.constant.Platform;

public class GetMatch extends MatchApiMethod {

	public GetMatch(ApiConfig config, Platform platform, long matchId) {
		super(config);
		setPlatform(platform);
		setReturnType(Match.class);
		setUrlBase(platform.getHost() + "/lol/match/v3/matches/" + matchId);
		addApiKeyParameter();
	}
}
