package com.rain.app.service.riot.api.endpoints.summoner.methods;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.RiotApi;
import com.rain.app.service.riot.api.endpoints.summoner.SummonerApiMethod;
import com.rain.app.service.riot.api.endpoints.summoner.dto.Summoner;
import com.rain.app.service.riot.constant.Platform;

import net.rithms.util.Convert;

public class GetSummonerByName extends SummonerApiMethod {

	public GetSummonerByName(ApiConfig config, Platform platform, String summonerName) {
		super(config);
		setPlatform(platform);
		summonerName = Convert.normalizeSummonerName(summonerName);
		setReturnType(Summoner.class);
		try {
			setUrlBase(platform.getHost() + "/lol/summoner/v3/summoners/by-name/" + URLEncoder.encode(summonerName, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// This should never happen
			RiotApi.log.log(Level.SEVERE, "URL Encoding Failed", e);
		}
		addApiKeyParameter();
	}
}
