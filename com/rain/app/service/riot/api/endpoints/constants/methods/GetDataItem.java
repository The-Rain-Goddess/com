package com.rain.app.service.riot.api.endpoints.constants.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.Utility;
import com.rain.app.service.riot.api.endpoints.constants.StaticDataApiMethod;
import com.rain.app.service.riot.api.endpoints.constants.constant.ItemData;
import com.rain.app.service.riot.api.endpoints.constants.constant.Locale;
import com.rain.app.service.riot.api.endpoints.constants.dto.Item;
import com.rain.app.service.riot.constant.Platform;

public class GetDataItem extends StaticDataApiMethod {

	public GetDataItem(ApiConfig config, Platform platform, int id, Locale locale, String version, ItemData... itemData) {
		super(config);
		setPlatform(platform);
		setReturnType(Item.class);
		setUrlBase(platform.getHost() + "/lol/static-data/v3/items/" + id);
		if (locale != null) {
			add(new UrlParameter("locale", locale));
		}
		if (version != null) {
			add(new UrlParameter("version", version));
		}
		if (itemData[0] != null) {
			add(new UrlParameter("itemData", Utility.joinString(",", (Object[]) itemData)));
		}
		addApiKeyParameter();
	}
}
