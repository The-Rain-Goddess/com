package com.rain.app.server.redux;

import java.util.List;

public class ServerUtilities {

	public static String printList(List<?> list){
		String re = "";
		for(Object s : list){
			re = re + s.toString() + "\n";
		} return re;
	}

}
