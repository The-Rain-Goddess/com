package com.rain.app.service.riot.api.endpoints.stats.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.endpoints.constants.StaticDataApiMethod;
import com.rain.app.service.riot.api.endpoints.stats.constant.Season;
import com.rain.app.service.riot.api.endpoints.stats.dto.PlayerStatsSummaryList;
import com.rain.app.service.riot.constant.Region;

public class GetPlayerStatsSummary extends StaticDataApiMethod {

	@Deprecated
	public GetPlayerStatsSummary(ApiConfig config, Region region,Season season, long summonerId) {
		super(config);
		setRegion(region);
		setReturnType(PlayerStatsSummaryList.class);
		setUrlBase(region.getEndpoint() + "/v1.3/stats/by-summoner/" + summonerId + "/summary");
		if (season != null) {
			add(new UrlParameter("season", season));
		}
		addApiKeyParameter();
	}
}
