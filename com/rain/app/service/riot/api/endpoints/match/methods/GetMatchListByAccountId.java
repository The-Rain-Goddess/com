package com.rain.app.service.riot.api.endpoints.match.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.endpoints.match.MatchApiMethod;
import com.rain.app.service.riot.api.endpoints.match.dto.MatchReferenceList;
import com.rain.app.service.riot.constant.Platform;

public class GetMatchListByAccountId extends MatchApiMethod {

	public GetMatchListByAccountId(ApiConfig config, Platform platform, long accountId, String champion, String queue, String season, long beginTime, long endTime,int beginIndex, int endIndex) {
		super(config);
		setPlatform(platform);
		setReturnType(MatchReferenceList.class);
		setUrlBase(platform.getHost() + "/lol/match/v3/matchlists/by-account/" + accountId);
		if (champion != null) {
			add(new UrlParameter("champion", champion));
		}
		if (queue != null) {
			add(new UrlParameter("queue", queue));
		}
		if (season != null) {
			add(new UrlParameter("season", season));
		}
		if (beginTime != -1) {
			add(new UrlParameter("beginTime", beginTime));
		}
		if (endTime != -1) {
			add(new UrlParameter("endTime", endTime));
		}
		if (beginIndex != -1) {
			add(new UrlParameter("beginIndex", beginIndex));
		}
		if (endIndex != -1) {
			add(new UrlParameter("endIndex", endIndex));
		}
		addApiKeyParameter();
	}
}
