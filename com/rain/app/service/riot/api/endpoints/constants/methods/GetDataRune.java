package com.rain.app.service.riot.api.endpoints.constants.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.Utility;
import com.rain.app.service.riot.api.endpoints.constants.StaticDataApiMethod;
import com.rain.app.service.riot.api.endpoints.constants.constant.Locale;
import com.rain.app.service.riot.api.endpoints.constants.constant.RuneData;
import com.rain.app.service.riot.api.endpoints.constants.dto.Rune;
import com.rain.app.service.riot.constant.Platform;

public class GetDataRune extends StaticDataApiMethod {

	public GetDataRune(ApiConfig config, Platform platform, int id, Locale locale, String version, RuneData... runeData) {
		super(config);
		setPlatform(platform);
		setReturnType(Rune.class);
		setUrlBase(platform.getHost() + "/lol/static-data/v3/runes/" + id);
		if (locale != null) {
			add(new UrlParameter("locale", locale));
		}
		if (version != null) {
			add(new UrlParameter("version", version));
		}
		if (runeData[0] != null) {
			add(new UrlParameter("runeData", Utility.joinString(",", (Object[]) runeData)));
		}
		addApiKeyParameter();
	}
}
