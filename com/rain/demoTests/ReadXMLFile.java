package com.rain.demoTests;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.*;;
 
public class ReadXMLFile {
 
  public static void main(String argv[]) {
 
    try {
 
	File fXmlFile = new File("C:\\Users\\rmay\\Project_RMay\\MB0600KG22181B18Gxxx2.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
 
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();
 
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
 
	NodeList nList = doc.getElementsByTagName("Analog_Channels");
 
	for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);
 
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
 
			//System.out.println("Staff id : " + eElement.getAttribute("id"));
			System.out.println("Name : " + eElement.getElementsByTagName("DisplayText").item(0).getTextContent());
			System.out.println("ChannelID : " + eElement.getElementsByTagName("ChannelID").item(0).getTextContent());
			System.out.println("Units : " + eElement.getElementsByTagName("Units").item(0).getTextContent());
			//System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
			//System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
 
		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
  }
 
}