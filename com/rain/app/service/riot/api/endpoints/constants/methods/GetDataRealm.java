package com.rain.app.service.riot.api.endpoints.constants.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.constants.StaticDataApiMethod;
import com.rain.app.service.riot.api.endpoints.constants.dto.Realm;
import com.rain.app.service.riot.constant.Platform;

public class GetDataRealm extends StaticDataApiMethod {

	public GetDataRealm(ApiConfig config, Platform platform) {
		super(config);
		setPlatform(platform);
		setReturnType(Realm.class);
		setUrlBase(platform.getHost() + "/lol/static-data/v3/realms");
		addApiKeyParameter();
	}
}
