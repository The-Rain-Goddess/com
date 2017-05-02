package com.rain.app.service.riot.api.endpoints.constants.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.Utility;
import com.rain.app.service.riot.api.endpoints.constants.StaticDataApiMethod;
import com.rain.app.service.riot.api.endpoints.constants.constant.Locale;
import com.rain.app.service.riot.api.endpoints.constants.constant.MasteryListData;
import com.rain.app.service.riot.api.endpoints.constants.dto.MasteryList;
import com.rain.app.service.riot.constant.Platform;

public class GetDataMasteryList extends StaticDataApiMethod {

	public GetDataMasteryList(ApiConfig config, Platform platform, Locale locale, String version, MasteryListData... masteryListData) {
		super(config);
		setPlatform(platform);
		setReturnType(MasteryList.class);
		setUrlBase(platform.getHost() + "/lol/static-data/v3/masteries");
		if (locale != null) {
			add(new UrlParameter("locale", locale));
		}
		if (version != null) {
			add(new UrlParameter("version", version));
		}
		if (masteryListData[0] != null) {
			add(new UrlParameter("masteryListData", Utility.joinString(",", (Object[]) masteryListData)));
		}
		addApiKeyParameter();
	}

}
