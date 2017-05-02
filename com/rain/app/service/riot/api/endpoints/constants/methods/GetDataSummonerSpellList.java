package com.rain.app.service.riot.api.endpoints.constants.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.Utility;
import com.rain.app.service.riot.api.endpoints.constants.StaticDataApiMethod;
import com.rain.app.service.riot.api.endpoints.constants.constant.Locale;
import com.rain.app.service.riot.api.endpoints.constants.constant.SpellListData;
import com.rain.app.service.riot.api.endpoints.constants.dto.SummonerSpellList;
import com.rain.app.service.riot.constant.Platform;

public class GetDataSummonerSpellList extends StaticDataApiMethod {

	public GetDataSummonerSpellList(ApiConfig config, Platform platform, Locale locale, String version, boolean dataById, SpellListData... spellListData) {
		super(config);
		setPlatform(platform);
		setReturnType(SummonerSpellList.class);
		setUrlBase(platform.getHost() + "/lol/static-data/v3/summoner-spells");
		if (locale != null) {
			add(new UrlParameter("locale", locale));
		}
		if (version != null) {
			add(new UrlParameter("version", version));
		}
		add(new UrlParameter("dataById", dataById));
		if (spellListData[0] != null) {
			add(new UrlParameter("spellListData", Utility.joinString(",", (Object[]) spellListData)));
		}
		addApiKeyParameter();
	}
}
