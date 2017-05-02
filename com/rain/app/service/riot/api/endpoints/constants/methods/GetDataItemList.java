package com.rain.app.service.riot.api.endpoints.constants.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.Utility;
import com.rain.app.service.riot.api.endpoints.constants.StaticDataApiMethod;
import com.rain.app.service.riot.api.endpoints.constants.constant.ItemListData;
import com.rain.app.service.riot.api.endpoints.constants.constant.Locale;
import com.rain.app.service.riot.api.endpoints.constants.dto.ItemList;
import com.rain.app.service.riot.constant.Platform;

public class GetDataItemList extends StaticDataApiMethod {

	public GetDataItemList(ApiConfig config, Platform platform, Locale locale, String version, ItemListData... itemListData) {
		super(config);
		setPlatform(platform);
		setReturnType(ItemList.class);
		setUrlBase(platform.getHost() + "/lol/static-data/v3/items");
		if (locale != null) {
			add(new UrlParameter("locale", locale));
		}
		if (version != null) {
			add(new UrlParameter("version", version));
		}
		if (itemListData[0] != null) {
			add(new UrlParameter("itemListData", Utility.joinString(",", (Object[]) itemListData)));
		}
		addApiKeyParameter();
	}
}
