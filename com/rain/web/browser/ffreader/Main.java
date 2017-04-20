package com.rain.web.browser.ffreader;

import java.io.IOException;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Main {

	public static void main(String[] args) throws IOException{
		Browser testBrowser = Browser.build(Browser.type.FFREADER);
		//System.out.println(testBrowser.getHtmlFromURL("http://google.com/").html());
		
		JFXPanel jfxPanel = new JFXPanel(); 
		Platform.runLater(()-> {
			final AnchorPane anchorPane = new AnchorPane();
	        WebView webBrowser = new WebView();

	        //Set Layout Constraint
	        AnchorPane.setTopAnchor(webBrowser, 0.0);
	        AnchorPane.setBottomAnchor(webBrowser, 0.0);
	        AnchorPane.setLeftAnchor(webBrowser, 0.0);
	        AnchorPane.setRightAnchor(webBrowser, 0.0);

	        //Add WebView to AnchorPane
	        anchorPane.getChildren().add(webBrowser);

	        //Create Scene
	        final Scene scene = new Scene(anchorPane);

	        // Obtain the webEngine to navigate
	        final WebEngine webEngine = webBrowser.getEngine();
	        try {
				webEngine.loadContent(testBrowser.getHtmlFromURL("http://google.com/").html());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        jfxPanel.setScene(scene);

		});
	}

}
