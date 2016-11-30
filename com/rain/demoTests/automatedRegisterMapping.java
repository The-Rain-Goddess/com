package com.rain.demoTests;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/////////////////////////////////////////////IMPORTANT, still has bugs that I could not fix, procede with caution!!!
class automatedRegisterMapping{
    static File outputJson; static String panelVariation;
    static BufferedWriter bw; static BufferedReader br;
    public static void main(String[] args){
    	Scanner kb = new Scanner(System.in);
    	System.out.println("Please type path of html input file");
    	String inputFile = kb.nextLine();
    	if(inputFile.contains(".html")){
			if(inputFile.contains("HPanel"))	//      C:\\Users\\rmay\\Project_RMay\\INTERNAL_HPanel.html
				create(0, inputFile);  
			else if(inputFile.contains("GPanel"))
				create(1, inputFile);
			else System.out.println("Wrong file, File must include register settings");
				writeSecondFile(kb);
    	} else if(inputFile.contains(".xml")){  
    			writeSecondFile(kb);		
    	} else System.out.println("Wrong file extension, must be html or xml");
    }
    
    public static void addFromXml(String readIn, String output){
    	try{																    										
			FileWriter fw = new FileWriter(output);					// 		C:\Users\rmay\Project_RMay\INTERNAL_HPanel.html
			FileReader fr = new FileReader(outputJson);				//	C:\\Users\rmay\\Project_RMay\\xD0100KG176.7D18Hxxx3.xml
			bw = new BufferedWriter(fw);br = new BufferedReader(fr);//		F:\\WorkSpace\\demoTests\\HPanel_Config.json
			String line = "";
			 while(!(line = br.readLine()).contains("AI_Result")){
				 bw.write(line); bw.newLine();
			} if(line.contains("AI_Result")){
				 bw.write(line); bw.newLine();
				parseXML(readIn, new String[]{"Analog_Channels,138"});
			} while(!(line = br.readLine()).equals("\t}")){
				bw.write(line);bw.newLine();
			}
    	} catch(IOException e){ e.printStackTrace();}
    	finally{
    		try {
				bw.flush();bw.close();br.close();
				System.out.println("File append completed \nWarning! \n Some data types and all units of measure must be inputed by hand!");
			} catch (IOException e) { e.printStackTrace();}
    	}
    }

    public static void parseXML(String readIn, String[] readValues){
    	String outputLine = "";
    	try {
			for(int itemp = 0; itemp<readValues.length; itemp++){
				String[] values = readValues[itemp].split(",");
				File fXmlFile = new File(readIn);
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
				doc.getDocumentElement().normalize();
				
				NodeList nList = doc.getElementsByTagName(values[0]);   // "Analog_Channels"
			 
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;																				//start
						String a  = eElement.getElementsByTagName("DisplayText").item(0).getTextContent(); //a = a.trim();				//reigster
						String b = " " + ((Integer.parseInt(eElement.getElementsByTagName("ChannelID").item(0).getTextContent()) ) *2  + 
								Integer.parseInt(values[1])) ;
						String bt; if(temp==nList.getLength()-1) bt = ""; else bt = ",";
						outputLine = outputLine + "{ \"name\": \"" + a + "\", \"RegisterAddress\":{\"startReg\":" + b + ", \"numRegisters\": " + 2
	                			+ "}, \"type\": \"" + "float" + "\"}" + bt;
						bw.write(outputLine);
						bw.newLine();
						outputLine = "";
					}
				}
			}
		} catch (Exception e) {e.printStackTrace();}
    }
    
    public static void writeSecondFile(Scanner kb){
    	System.out.println("Second InputFile? (y / n)");
    		if(kb.nextLine().equals("y")){
    			System.out.println("Please type path of input xml file:");
    			String in2putFile = kb.nextLine();
    			if(in2putFile.contains(".xml")){
    				System.out.println("\nPlease input path of real output json file");
    				String output = kb.nextLine();
    				addFromXml(in2putFile, output);
    			} else if(in2putFile.contains(".html")){
    				System.out.println("Please input html files first");
    			} else System.out.println("Wrong file extension, must be .xml or .html");
    		} else System.exit(0);
    }

    public static void create(int type, String readIn){
        switch (type) {
            case 0:
                outputJson = new File("temp_HPanel_Config.json");
                panelVariation = "01"; break;
            case 1:
                outputJson = new File("temp_GPanel_Config.json");
                panelVariation = "00"; break;
        }
        readInDocument(readIn, panelVariation);
    }    
    
    public static String typeSet(String in, String size, String check){
    	String out = "";
    	in  = in.trim().replaceAll(",","");						
    	if(in.equals("INT16"))
    		out = "integer";
    	else if(in.equals("INT8") || in.equals("UINT8"))
    		out = "bit";
    	else if(in.equals("UINT16") || in.equals("INT32") || in.equals("UINT32") || in.equals("UINT64") || in.equals("INT64"))
    		out = "integer";
    	else if(in.equals("STRING8") || in.equals("STRING16") || in.equals("USTRING8")|| in.equals("STRING32"))
    		out = "String";
    	else if(in.equals("CUSTOM") && check.contains("register"))
    		out = "bitarray";
    	else out = "bit";
    	if(size.contains("=") && !in.equals("STRING64"))
    		out = out + "array";
    	return out;
    }
    
    public static String typeSetFile(String in, String size, String check){
    	String out = "";
    	in  = in.trim().replaceAll(",","");
    	if(in.equals("INT16") || in.equals("UINT16") || in.equals("INT32") || in.equals("UINT32") || in.equals("UINT64") || in.equals("INT64"))
    		out = "integer";
    	else if(in.equals("INT8") || in.equals("UINT8"))
    		out = "bytearray";
    	else if(in.equals("STRING8") || in.equals("STRING16") || in.equals("USTRING8")|| in.equals("STRING32"))
    		out = "String";
    	else if(in.equals("STRING64") || in.equals("CUSTOM"))
    		out = "bytearray";
    	else
    		out = "bytearray"; 
    	return out;
    }
    
    public static void readInDocument(String readIn, String panelVariation){
    	try{
    		FileReader reader = new FileReader(readIn);
            FileWriter writer  = new FileWriter(outputJson);
            br = new BufferedReader(reader); bw = new BufferedWriter(writer); 											//assigns the reader/writer
            String[] fields; 																					//allows splitting of each attribute
            bw.write("{ "); bw.newLine(); bw.write("\"IPanel-Version\": \"1.0.0.1\",  "); bw.newLine();
	            if(panelVariation.equals("01")){
	            	bw.write("\"Controller\": \"H-Panel\","); bw.newLine(); }
	            else if(panelVariation.equals("00")){
	            	bw.write("\"Controller\": \"G-Panel\","); bw.newLine(); }
            bw.write(" \"Addresses\": [\n"); bw.newLine(); br.readLine();
            String checker = "<h3><a name=\"by_address\">Sorted by Address (map release \"" + panelVariation + "\")</a></h3>";
            while(!br.readLine().equals(checker)){	
            	br.readLine();   
            } br.readLine(); String temp;
            while(br.ready() && !(temp = br.readLine()).equals("")){ 														//main writing loop
            	fields = temp.split(">"); String check = fields[9]; String output = ""; 									//setup variables
            	if(check.contains("register")){	
            		String a = fields[5].toString(); String b = fields[9]; String c = fields[18]; String d = fields[14];  	//obtain strings
            																												//format strings
            		a.trim(); a = a.replaceAll("</a", ""); b.trim(); b = b.replaceAll("register=", " "); b = b.replaceAll("</td", "");
                	String z = b; z.trim(); String zb[] = z.split(","); if(zb.length==2) z = zb[1]; else z = " master-byte"; c.trim();
                	c = c.replaceAll("Size (bytes): ", ""); c = c.replaceAll("</nobr", " "); b = b.replaceAll("\\(", "") ; d.trim(); 
                	d = d.replaceAll("</tt", ", ");  d = typeSet(d, "", check); b = b.replaceAll(",", "");
                	b = b.replaceAll(" high-byte\\)", ""); b = b.replaceAll(" low-byte\\)", ""); b = b.replaceAll("\\)", ""); 
                	c = c.replaceAll("Size \\(bytes\\):", ""); if(c.contains("=")){ String[] cz = c.split(" "); c = cz[5];} int ca = 0;
                	if(!c.contains("4026531839")) ca = Integer.parseInt(c.trim()); //
                	z = z.replaceAll("\\)", ""); String bt; if(fields[18].contains("4026531839")) bt = ""; else bt = ",";
                	
                	output = "{ \"name\": \"" + a + "\", \"RegisterAddress\":{\"startReg\":" + b + ", \"numRegisters\": " + (int)Math.ceil((ca+1)/2)
                			+ "}, \"type\": \"" + d /*+ "\"byte-Type\":" + z*/ + "\"}" + bt;
            	} else if(check.contains("file")){
            		String a = fields[5].toString(); String b = fields[9]; String c = fields[14]; String d = fields[18];   	//obtain strings
            		a.trim(); a = a.replaceAll("</a", ""); b.trim(); String bz[] = b.split(",");   							//format strings
            		bz[0] = bz[0].replaceAll("\\(file=", " "); bz[1] = bz[1].replaceAll("record=", ""); bz[2] = bz[2].replaceAll("byte=","");
            		bz[2] = bz[2].replaceAll("\\)</td", ""); d.trim(); d = d.replaceAll("Size \\(bytes\\):", ""); d = d.replaceAll("</nobr","");
            		c = c.replaceAll("</tt", ", ");  c = typeSetFile(c, fields[18], check); String[]dz; if(d.contains("=")){ dz = d.split(" ");
            		d = " " + dz[dz.length-1];	} else if(d.contains(",")){dz = d.split(","); d = " " + 
            		(Integer.parseInt(dz[0].replaceAll("bytes", "").trim()) * Integer.parseInt(dz[1].replaceAll("items","").trim())); }
            		
                	output = "{ \"name\": \"" + a + "\", \"FileAddress\":{\"file\":" + bz[0] + ", \"record\":" + bz[1]
                			+ ", \"size\":" + bz[2] + "}, \"type\": \"" + c + "\", " + "\"readSize\":" + d + "},";
            	} bw.write(output); bw.newLine();
            } bw.write("\t\t]"); bw.newLine(); bw.write("/t}");															// end of file
    	} catch(IOException e){
    		e.printStackTrace(); System.out.println("File Not Found, check your file path");
    	} finally{
    		try { br.close(); bw.flush(); bw.close(); }
    		catch (IOException e) { e.printStackTrace(); }
    		finally{ System.out.println("file completed"); }
    	}
    }
}