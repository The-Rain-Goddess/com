package com.rain.app.service.riot.api.endpoints.constants.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.Utility;
import com.rain.app.service.riot.api.endpoints.constants.StaticDataApiMethod;
import com.rain.app.service.riot.api.endpoints.constants.constant.Locale;
import com.rain.app.service.riot.api.endpoints.constants.constant.RuneListData;
import com.rain.app.service.riot.api.endpoints.constants.dto.RuneList;
import com.rain.app.service.riot.constant.Platform;

public class GetDataRuneList extends StaticDataApiMethod {

	public GetDataRuneList(ApiConfig config, Platform platform, Locale locale, String version, RuneListData... runeListData) {
		super(config);
		setPlatform(platform);
		setReturnType(RuneList.class);
		setUrlBase(platform.getHost() + "/lol/static-data/v3/runes");
		if (locale != null) {
			add(new UrlParameter("locale", locale));
		}
		if (version != null) {
			add(new UrlParameter("version", version));
		}
		if (runeListData[0] != null) {
			add(new UrlParameter("runeListData", Utility.joinString(",", (Object[]) runeListData)));
		}
		addApiKeyParameter();
	}
}
