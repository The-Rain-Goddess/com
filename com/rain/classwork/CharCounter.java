package com.rain.classwork;

import java.io.File;
import java.util.Scanner;

public class CharCounter {

	public CharCounter() {
		
	}

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter the file name or type QUIT to exit:");
		String fileName = kb.nextLine();
		if(fileName.equalsIgnoreCase("quit")){
			kb.close();
			return;
		}
		File file = new File(fileName);
		while(!file.exists()){
			System.out.println("File: "+fileName+" does not exist.");
			System.out.println("Please enter the file name again or type QUIT to exit:");
			fileName = kb.nextLine();
			if(fileName.equalsIgnoreCase("quit"))
			{
				kb.close(); return;
			}
			file = new File(fileName);
		}
		System.out.println("Please enter a character: ");
		String letter = kb.nextLine();
		
		try{
			
			Scanner in = new Scanner(file);
			String line;
			int count = 0;
			while(in.hasNext()){
				line = in.nextLine();
				//System.out.println(line);
				for(int i = 0; i<line.length();i++){
					if(line.charAt(i)==letter.charAt(0))
						count++;
				}
			}
			System.out.println("Letter '" + letter + "' occurs " + count + " times in the file '" + fileName + "'." );
			in.close();
		} catch(Exception e){
			System.out.println("Error");
			e.printStackTrace();
		} finally{
			kb.close();
		}
	}

}
