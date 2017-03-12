package com.rain.web.dragcave;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpUrlLogin {
	private List<String> cookies = new ArrayList<>();
	private HttpsURLConnection httpsConnection;
	private final String USER_AGENT = "Mozilla/5.0";
	
	public HttpUrlLogin() {
		
	}
	
	public void sendPost(String url, String postParams) throws IOException {

		URL targetURL = new URL(url);
		httpsConnection = (HttpsURLConnection) targetURL.openConnection();

		// Acts like a browser
		httpsConnection.setUseCaches(false);
		httpsConnection.setRequestMethod("POST");
		httpsConnection.setRequestProperty("Host", "accounts.google.com");
		httpsConnection.setRequestProperty("User-Agent", USER_AGENT);
		httpsConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpsConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		for (String cookie : this.cookies) {
			httpsConnection.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
		}
		
		httpsConnection.setRequestProperty("Connection", "keep-alive");
		httpsConnection.setRequestProperty("Referer", "https://dragcave.net/");
		httpsConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		httpsConnection.setRequestProperty("Content-Length", Integer.toString(postParams.length()));

		httpsConnection.setDoOutput(true);
		httpsConnection.setDoInput(true);

		// Send post request
		DataOutputStream writeToHttpsConnection = new DataOutputStream(httpsConnection.getOutputStream());
		writeToHttpsConnection.writeBytes(postParams);
		writeToHttpsConnection.flush();
		writeToHttpsConnection.close();

		int responseCode = httpsConnection.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + postParams);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(httpsConnection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// System.out.println(response.toString());
	}
		
	public String GetPageContent(String url) throws IOException {
		URL targetURL = new URL(url);
		URLConnection httpConnection =  targetURL.openConnection(); //(HttpsURLConnection)

		// default is GET
		((HttpURLConnection) httpConnection).setRequestMethod("GET");

		httpConnection.setUseCaches(false);

		// act like a browser
		httpConnection.setRequestProperty("User-Agent", USER_AGENT);
		httpConnection.setRequestProperty("Accept",
			"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		if (cookies != null) {
			for (String cookie : this.cookies) {
				httpConnection.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
			}
		}
		//int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(targetURL.openStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// Get the response cookies
		setCookies(httpsConnection.getHeaderFields().get("Set-Cookie"));

		return response.toString();
	}
	
	public String getFormParams(String html, String username, String password)throws UnsupportedEncodingException {
		System.out.println("Extracting form's data...");

		Document htmlDocument = Jsoup.parse(html);

		// Google form id
		Element loginform = htmlDocument.getElementById("middle");
		//System.out.println(loginform.text());
		Elements inputElements = loginform.getElementsByTag("input");
		List<String> paramList = new ArrayList<String>();
		for (Element inputElement : inputElements) {
			String key = inputElement.attr("name");
			String value = inputElement.attr("value");

			if (key.equals("username"))
				value = username;
			else if (key.equals("password"))
				value = password;
			paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
		}

		// build parameters list
		StringBuilder result = new StringBuilder();
		for (String param : paramList) {
			if (result.length() == 0) {
				result.append(param);
			} else {
				result.append("&" + param);
			}
		}
		return result.toString();
	}
	
	public List<String> getCookies() {
		return cookies;
	}

	public void setCookies(List<String> cookies) {
		this.cookies = cookies;
	}

}
