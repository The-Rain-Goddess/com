package com.rain.app.service.riot.api.endpoints.status.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.status.LolStatusApiMethod;
import com.rain.app.service.riot.api.endpoints.status.dto.ShardStatus;
import com.rain.app.service.riot.constant.Platform;

public class GetShardData extends LolStatusApiMethod {

	public GetShardData(ApiConfig config, Platform platform) {
		super(config);
		setPlatform(platform);
		setReturnType(ShardStatus.class);
		setUrlBase(platform.getHost() + "/lol/status/v3/shard-data");
	}
}
