package com.rain.app.service.riot.api.endpoints.constants.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.Utility;
import com.rain.app.service.riot.api.endpoints.constants.StaticDataApiMethod;
import com.rain.app.service.riot.api.endpoints.constants.constant.ChampData;
import com.rain.app.service.riot.api.endpoints.constants.constant.Locale;
import com.rain.app.service.riot.api.endpoints.constants.dto.Champion;
import com.rain.app.service.riot.constant.Platform;

public class GetDataChampion extends StaticDataApiMethod {
	public GetDataChampion(ApiConfig config, Platform platform, int id, Locale locale, String version, ChampData... champData) {
		super(config);
		setPlatform(platform);
		setReturnType(Champion.class);
		setUrlBase(platform.getHost() + "/lol/static-data/v3/champions/" + id);
		if (locale != null) {
			add(new UrlParameter("locale", locale));
		}
		if (version != null) {
			add(new UrlParameter("version", version));
		}
		if (champData[0] != null) {
			add(new UrlParameter("champData", Utility.joinString(",", (Object[]) champData)));
		}
		addApiKeyParameter();
	}
}
