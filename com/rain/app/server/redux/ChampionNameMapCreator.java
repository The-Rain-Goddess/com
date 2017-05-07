package com.rain.app.server.redux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.RiotApi;
import com.rain.app.service.riot.api.RiotApiException;
import com.rain.app.service.riot.api.endpoints.constants.dto.Champion;
import com.rain.app.service.riot.api.endpoints.constants.dto.ChampionList;
import com.rain.app.service.riot.constant.Platform;

public class ChampionNameMapCreator {

	static RiotApi api = new RiotApi(new ApiConfig().setKey("RGAPI-1E616A6D-EC4A-46F1-A9BE-E1C620EABB05"));
	
	public static void main(String[] args) throws RiotApiException{
		ChampionList cList = api.getDataChampionList(Platform.NA);
		List<String> l = new ArrayList<>();
		//for(Map.Entry<String, Champion> entry : new ArrayList<>(cList.getData().entrySet()))
			//System.out.println(entry.getKey() + "\n" + entry.getValue());
		
		
		for(Map.Entry<String, Champion> entry : new ArrayList<>(cList.getData().entrySet()))
			if(entry.getValue().getId() == 432)
				System.out.println(entry.getValue().getName());
	}

}
