package com.rain.demoTests;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
public class ncss {
	public static ArrayList<File>cold;
	public static ArrayList<String>errors; 											//result arraylist
	
	public static void main(String[] args){
		try { cold = new ArrayList<File>(); errors = new ArrayList<String>();
			getFiles("C:\\Users\\rmay\\Project_RMay\\GenLink");
			stealWords();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean getFiles(String input) throws IOException{
		File folder = new File(input);
		File[] listOfFiles = folder.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(".cs")) {
	        cold.add(listOfFiles[i]);
	      } else if (listOfFiles[i].isDirectory()){
	    	  //TIDO CODE GET HERE
	      }
	    } return true;
	}
	
	public static boolean stealWords(){
		try{
			File a = new File("Documentation.txt");
			Files.deleteIfExists(a.toPath());        								//resets document if exists
			for(int i = 0; i<cold.size(); i++){
				FileReader fr = new FileReader(cold.get(i));
				FileWriter fw = new FileWriter("Documentation.txt",true);
				java.io.BufferedReader br = new BufferedReader(fr);
				BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw);
				out.println(cold.get(i).getName());  								//System.out.println(cold.get(i).getName()); 
				readThrough(br, out);	
			} System.out.println("file complete");
		} catch(IOException e){
			e.printStackTrace();
		} return true;
	}
	
	public static boolean readThrough(BufferedReader br, PrintWriter out){
		try {
			String line = br.readLine();//input line
			String adder = "";			//output line
			while(line!=null){
				if(line.contains("MessageBox.Show") ){  							//change for logic
					adder = line; adder = adder.trim();
					while(!line.contains(";")){
						line = line.trim();
						adder = adder.trim() + " " + line.trim();
						line = br.readLine(); 										//newline
					} errors.add(adder); adder = "\t" + adder;  
					if(adder.contains("range"))
						out.println(adder.replaceAll("MessageBox.Show","").replaceAll("MessageBoxButtons.OK","").replaceAll(
							"MessageBoxIcon.Exclamation","").replaceAll(",", " "));   
				} line = br.readLine(); 
				adder = "";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try{
				out.flush(); out.close();
				br.close();
			} catch(IOException e){
				e.printStackTrace();
			}
		} return true;
	}
}
