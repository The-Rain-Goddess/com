package com.rain.app.service.riot.api.endpoints.match.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.match.MatchApiMethod;
import com.rain.app.service.riot.api.endpoints.match.dto.MatchTimeline;
import com.rain.app.service.riot.constant.Platform;

public class GetTimelineByMatchId extends MatchApiMethod {

	public GetTimelineByMatchId(ApiConfig config, Platform platform, long matchId) {
		super(config);
		setPlatform(platform);
		setReturnType(MatchTimeline.class);
		setUrlBase(platform.getHost() + "/lol/match/v3/timelines/by-match/" + matchId);
		addApiKeyParameter();
	}
}
