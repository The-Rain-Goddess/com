package com.rain.app.service.riot.api.endpoints.constants.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.endpoints.constants.StaticDataApiMethod;
import com.rain.app.service.riot.api.endpoints.constants.constant.Locale;
import com.rain.app.service.riot.api.endpoints.constants.dto.LanguageStrings;
import com.rain.app.service.riot.constant.Platform;

public class GetDataLanguageStrings extends StaticDataApiMethod {

	public GetDataLanguageStrings(ApiConfig config, Platform platform, Locale locale, String version) {
		super(config);
		setPlatform(platform);
		setReturnType(LanguageStrings.class);
		setUrlBase(platform.getHost() + "/lol/static-data/v3/language-strings");
		if (locale != null) {
			add(new UrlParameter("locale", locale));
		}
		if (version != null) {
			add(new UrlParameter("version", version));
		}
		addApiKeyParameter();
	}
}
