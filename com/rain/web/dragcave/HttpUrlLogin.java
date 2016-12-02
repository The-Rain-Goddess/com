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
	private HttpsURLConnection conn;
	private final String USER_AGENT = "Mozilla/5.0";
	
	public HttpUrlLogin() {
		
	}
	
	public void sendPost(String url, String postParams) throws IOException {

		URL obj = new URL(url);
		conn = (HttpsURLConnection) obj.openConnection();

		// Acts like a browser
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Host", "accounts.google.com");
		conn.setRequestProperty("User-Agent", USER_AGENT);
		conn.setRequestProperty("Accept",
			"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		for (String cookie : this.cookies) {
			conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
		}
		conn.setRequestProperty("Connection", "keep-alive");
		conn.setRequestProperty("Referer", "https://dragcave.net/");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));

		conn.setDoOutput(true);
		conn.setDoInput(true);

		// Send post request
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(postParams);
		wr.flush();
		wr.close();

		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + postParams);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// System.out.println(response.toString());
	}
		
	public String GetPageContent(String url) throws IOException {
		URL obj = new URL(url);
		URLConnection con =  obj.openConnection(); //(HttpsURLConnection)

		// default is GET
		((HttpURLConnection) con).setRequestMethod("GET");

		con.setUseCaches(false);

		// act like a browser
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept",
			"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		if (cookies != null) {
			for (String cookie : this.cookies) {
				con.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
			}
		}
		//int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(obj.openStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// Get the response cookies
		setCookies(conn.getHeaderFields().get("Set-Cookie"));

		return response.toString();
	}
	
	public String getFormParams(String html, String username, String password)throws UnsupportedEncodingException {
		System.out.println("Extracting form's data...");

		Document doc = Jsoup.parse(html);

		// Google form id
		Element loginform = doc.getElementById("middle");
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
