package com.rain.app.service.riot.api.endpoints.constants.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.Utility;
import com.rain.app.service.riot.api.endpoints.constants.StaticDataApiMethod;
import com.rain.app.service.riot.api.endpoints.constants.constant.Locale;
import com.rain.app.service.riot.api.endpoints.constants.constant.SpellData;
import com.rain.app.service.riot.api.endpoints.constants.dto.SummonerSpell;
import com.rain.app.service.riot.constant.Platform;

public class GetDataSummonerSpell extends StaticDataApiMethod {

	public GetDataSummonerSpell(ApiConfig config, Platform platform, int id, Locale locale, String version, SpellData... spellData) {
		super(config);
		setPlatform(platform);
		setReturnType(SummonerSpell.class);
		setUrlBase(platform.getHost() + "/lol/static-data/v3/summoner-spells/" + id);
		if (locale != null) {
			add(new UrlParameter("locale", locale));
		}
		if (version != null) {
			add(new UrlParameter("version", version));
		}
		if (spellData[0] != null) {
			add(new UrlParameter("spellData", Utility.joinString(",", (Object[]) spellData)));
		}
		addApiKeyParameter();
	}
}
