package com.rain.app.service.riot.api.endpoints.stats.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.endpoints.constants.StaticDataApiMethod;
import com.rain.app.service.riot.api.endpoints.stats.constant.Season;
import com.rain.app.service.riot.api.endpoints.stats.dto.RankedStats;
import com.rain.app.service.riot.constant.Region;

public class GetRankedStats extends StaticDataApiMethod {

	@Deprecated
	public GetRankedStats(ApiConfig config, Region region, Season season, long summonerId) {
		super(config);
		setRegion(region);
		setReturnType(RankedStats.class);
		setUrlBase(region.getEndpoint() + "/v1.3/stats/by-summoner/" + summonerId + "/ranked");
		if (season != null) {
			add(new UrlParameter("season", season));
		}
		addApiKeyParameter();
	}
}
