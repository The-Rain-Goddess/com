package com.rain.app.service.riot.api.endpoints.constants.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.endpoints.constants.StaticDataApiMethod;
import com.rain.app.service.riot.api.endpoints.constants.constant.Locale;
import com.rain.app.service.riot.api.endpoints.constants.dto.ProfileIconData;
import com.rain.app.service.riot.constant.Platform;

public class GetDataProfileIcons extends StaticDataApiMethod {

	public GetDataProfileIcons(ApiConfig config, Platform platform, Locale locale, String version) {
		super(config);
		setPlatform(platform);
		setReturnType(ProfileIconData.class);
		setUrlBase(platform.getHost() + "/lol/static-data/v3/profile-icons");
		if (locale != null) {
			add(new UrlParameter("locale", locale));
		}
		if (version != null) {
			add(new UrlParameter("version", version));
		}
		addApiKeyParameter();
	}
}
