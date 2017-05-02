package com.rain.app.service.riot.api.endpoints.constants.methods;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.constants.StaticDataApiMethod;
import com.rain.app.service.riot.constant.Platform;

public class GetDataLanguages extends StaticDataApiMethod {
	public GetDataLanguages(ApiConfig config, Platform platform) {
		super(config);
		setPlatform(platform);
		setReturnType(new TypeToken<List<String>>() {
		}.getType());
		setUrlBase(platform.getHost() + "/lol/static-data/v3/languages");
		addApiKeyParameter();
	}
}
