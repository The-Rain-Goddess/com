package com.rain.app.service.riot.api.endpoints.match.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.match.MatchApiMethod;
import com.rain.app.service.riot.api.endpoints.match.dto.MatchReferenceList;
import com.rain.app.service.riot.constant.Platform;

public class GetRecentMatchListByAccountId extends MatchApiMethod {

	public GetRecentMatchListByAccountId(ApiConfig config, Platform platform, long accountId) {
		super(config);
		setPlatform(platform);
		setReturnType(MatchReferenceList.class);
		setUrlBase(platform.getHost() + "/lol/match/v3/matchlists/by-account/" + accountId + "/recent");
		addApiKeyParameter();
	}
}
