package com.rain.app.server.redux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ServerUtilities {

	public static String listToString(List<?> list){
		String re = "";
		for(Object s : list){
			re = re + s.toString() + "\n";
		} return re;
	}
	
	public static String mapToString(Map<?, ?> map){
		String re = "";
		for(Map.Entry<?, ?> entry : map.entrySet()){
			re = re + entry.toString() + "\n";
		} return re;
	}
	
	public static List<String> mapToList(Map<?, ?> rankedChampionDataMap) {
		List<String> list = new ArrayList<String>();
		for(Entry<?, ?> entry : rankedChampionDataMap.entrySet()){
			list.add(entry.getKey().toString() + ":" + entry.getValue().toString());
		} return list;
	}
	
	public static String mapOfListsToString(Map<Integer, List<AggregatedChampionData>> rankedChampionDataMap){
		String r = "";
		for(Entry<Integer, List<AggregatedChampionData>> entry : rankedChampionDataMap.entrySet()){
			r = r + entry.getKey() + ":\n";
			for(Object s : entry.getValue()){
				r = r + s.toString() + "\n";
			}
		} return r;
	}
}
