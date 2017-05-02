package com.rain.app.service.riot.api.endpoints.constants.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.Utility;
import com.rain.app.service.riot.api.endpoints.constants.StaticDataApiMethod;
import com.rain.app.service.riot.api.endpoints.constants.constant.ChampListData;
import com.rain.app.service.riot.api.endpoints.constants.constant.Locale;
import com.rain.app.service.riot.api.endpoints.constants.dto.ChampionList;
import com.rain.app.service.riot.constant.Platform;

public class GetDataChampionList extends StaticDataApiMethod {

	public GetDataChampionList(ApiConfig config, Platform platform, Locale locale, String version, boolean dataById, ChampListData... champListData) {
		super(config);
		setPlatform(platform);
		setReturnType(ChampionList.class);
		setUrlBase(platform.getHost() + "/lol/static-data/v3/champions");
		if (locale != null) {
			add(new UrlParameter("locale", locale));
		}
		if (version != null) {
			add(new UrlParameter("version", version));
		}
		add(new UrlParameter("dataById", dataById));
		if (champListData[0] != null) {
			add(new UrlParameter("champListData", Utility.joinString(",", (Object[]) champListData)));
		}
		addApiKeyParameter();
	}
}
