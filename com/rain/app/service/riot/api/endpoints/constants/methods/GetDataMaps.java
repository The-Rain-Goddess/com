package com.rain.app.service.riot.api.endpoints.constants.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.endpoints.constants.StaticDataApiMethod;
import com.rain.app.service.riot.api.endpoints.constants.constant.Locale;
import com.rain.app.service.riot.api.endpoints.constants.dto.MapData;
import com.rain.app.service.riot.constant.Platform;

public class GetDataMaps extends StaticDataApiMethod {

	public GetDataMaps(ApiConfig config, Platform platform, Locale locale, String version) {
		super(config);
		setPlatform(platform);
		setReturnType(MapData.class);
		setUrlBase(platform.getHost() + "/lol/static-data/v3/maps");
		if (locale != null) {
			add(new UrlParameter("locale", locale));
		}
		if (version != null) {
			add(new UrlParameter("version", version));
		}
		addApiKeyParameter();
	}
}
