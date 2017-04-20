package com.rain.web.browser.ffreader;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Browser {
	User userCredentials;
	
	public enum type{
		FFREADER
	}

	public Browser() {

	}
	
	public Browser(Browser.type type){
		
	}

	public static Browser build(Browser.type typeOfBrowser) {
		return new Browser(typeOfBrowser);
	}
	
	public void setUserCredentials(String username, String password){
		userCredentials = User.build();
		userCredentials.setPassword(password);
		userCredentials.setUsername(username);
	}

	public boolean login(){
		if(userCredentials!=null)
			return true;
		else
			return false;
	}
	
	public Document getHtmlFromURL(String url) throws IOException{
		return Jsoup.connect(url).get();
	}
	
}
