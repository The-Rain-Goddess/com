package com.rain.web.dragcave;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebListener implements Runnable {
	private String site;
	private int location;
	
	static {
	    TrustManager[] trustAllCertificates = new TrustManager[] {
	        new X509TrustManager() {
	            @Override
	            public X509Certificate[] getAcceptedIssuers() {
	                return null; // Not relevant.
	            }
	            @Override
	            public void checkClientTrusted(X509Certificate[] certs, String authType) {
	                // Do nothing. Just allow them all.
	            }
	            @Override
	            public void checkServerTrusted(X509Certificate[] certs, String authType) {
	                // Do nothing. Just allow them all.
	            }
	        }
	    };

	    HostnameVerifier trustAllHostnames = new HostnameVerifier() {
	        @Override
	        public boolean verify(String hostname, SSLSession session) {
	            return true; // Just allow them all.
	        }
	    };

	    try {
	        System.setProperty("jsse.enableSNIExtension", "false");
	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCertificates, new SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	        HttpsURLConnection.setDefaultHostnameVerifier(trustAllHostnames);
	    }
	    catch (GeneralSecurityException e) {
	        throw new ExceptionInInitializerError(e);
	    }
	}
	
	public WebListener(String s) {
		site = s;
	}
	
	public WebListener(String s, int loc){
		site = s;
		location = loc;
		
		login();
	}
	
	@Override
	public void run(){
		try{
			while(Main.getContinue()){
				Thread.sleep(1000L);
				Document d = Jsoup.connect(site).get();
				Elements eggs = d.getElementsByClass("eggs");
				Elements links = d.select("a[href]");
				
				List<String> ids = new ArrayList<String>();
				for(Element link : links){
					//System.out.println(link.attr("href"));
					
					if(link.attr("href").contains("/get/"))
						ids.add(link.attr("href").replace("/get/", ""));
				} //System.out.println("IDS: " + ids);
				
				String eggsText = eggs.text();
				eggsText.replace("It's bright\\. And pink", "It's bright And pink");
				eggsText.replace("Oh my\\. There is a leetle tree among the eggs", "Oh my There is a leetle tree among the eggs");
				eggsText.replace("This egg looks like it doesn't belong; it is brightly colored with white spots\\.", "This egg looks like it doesn't belong; it is brightly colored with white spots");
				String[] data = eggsText.split("\\.");
				//String id = "";
				
				for(int i = 0; i<ids.size(); i++){
					//System.out.println(data[i]);
					if(!contains(ids.get(i))){
						Egg egg = new Egg(data[i].trim(), new Date(), location, ids.get(i));
						Main.getEggList().add(egg);
						take(egg);
					}
				}
			}	
		} catch(SocketTimeoutException e){
			//e.printStackTrace();
			//System.out.println("Socket timed out, retrying...");
			this.run();
		} catch(InterruptedException e){
			e.printStackTrace();
		} catch(Exception e){
			//unhandled exceptions
			e.printStackTrace();
		}	
	}
	
	@SuppressWarnings("unused")
	public void take(Egg e){
		//if(e.getType().equals("Gold") || e.getType().equals("Ice") || e.getType().equals("Magma") || e.getType().equals("Thunder") || e.getType().equals("Silver") || e.getType().contains("Dino") || e.getType().equals("Blusang Lindwurm")){
		if(Main.getParams().contains(e.getType())){	
			String url = "http://dragcave.net/get/" + e.getId().trim();
			try {
				Document d = Jsoup.connect(url).get();
			} catch(SocketTimeoutException e1){
				System.out.println("Socket timeout, retrying 'get' ...");
				take(e);
			}
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally{
				System.out.println("Successfully took: " + e);
			}
		}
	}
	
	public static void main(String[]args){
		Thread t = new Thread(new WebListener("http://dragcave.net/locations/1", 1));
		t.start();
		//new WebListener("http://dragcave.net/locations/1", 1);
	}
	
	private boolean contains(String id){
		List<Egg> eggs = Main.getEggList();
		for(int i = 0; i<eggs.size();i++){
			if(eggs.get(i).getId().equals(id))
				return true;
		} return false;
	}
	
	private void login(){
		try{
			String url = "https://dragcave.net/login";
			//String gmail = "http://dragcave.net/dragons/";

			HttpUrlLogin http = new HttpUrlLogin();

			// make sure cookies is turn on
			CookieHandler.setDefault(new CookieManager());

			// 1. Send a "GET" request, so that you can extract the form's data.
			Document page = Jsoup.connect(url).get(); //System.out.println("Html" + page.html());
			String postParams = http.getFormParams(page.html(), "Ghostz4a1234", "11600ga");

			// 2. Construct above post's content and then send a POST request for
			// authentication
			http.sendPost(url, postParams);

			// 3. success then go to gmail.
			//String result = http.GetPageContent(gmail);
			//System.out.println(result);
			
		} catch(UnsupportedEncodingException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} 
	}
}

	
