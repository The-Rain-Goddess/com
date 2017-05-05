package com.rain.app.service.riot.api.endpoints.match.methods;

import java.util.Set;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.endpoints.match.MatchApiMethod;
import com.rain.app.service.riot.api.endpoints.match.dto.MatchReferenceList;
import com.rain.app.service.riot.constant.Platform;

public class GetMatchReferenceListByAccountId extends MatchApiMethod {

	public GetMatchReferenceListByAccountId(ApiConfig config, Platform platform, long accountId, Set<Integer> champion, Set<Integer> queue, Set<Integer> season,
			long beginTime, long endTime, int beginIndex, int endIndex) {
		super(config);
		setPlatform(platform);
		setReturnType(MatchReferenceList.class);
		setUrlBase(platform.getHost() + "/lol/match/v3/matchlists/by-account/" + accountId);
		if (champion != null) {
			for (int param : champion) {
				add(new UrlParameter("champion", param));
			}
		}
		if (queue != null) {
			for (int param : queue) {
				add(new UrlParameter("queue", param));
			}
		}
		if (season != null) {
			for (int param : season) {
				add(new UrlParameter("season", param));
			}
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
