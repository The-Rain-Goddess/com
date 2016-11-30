package com.rain.classwork;

import java.io.File;
import java.util.Scanner;

public class Files {

	public Files(){
	}

	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter the file name or type QUIT to exit:");
		String fileName = kb.nextLine();
		if(fileName.equalsIgnoreCase("quit")){
			kb.close();
			return;
		}	
		try{
			File file = new File(fileName);
			while(!file.exists()){
				System.out.println("File: "  +fileName + " does not exist.");
				System.out.println("Please enter the file name again or type QUIT to exit:");
				fileName = kb.nextLine();
				if(fileName.equalsIgnoreCase("quit"))
					return;
				file = new File(fileName);
			} 
			Scanner in = new Scanner(file);
			int i = 0;
			double total = 0;
			if(in.hasNextLine()){
				while(in.hasNextLine()){
					i++;
					total = total + in.nextDouble();
					if(in.hasNextLine())
						in.nextLine();
					//in.nextLine();
				}
				System.out.println("Total: "+ String.format("%.3f", total));
				System.out.println("Average: "+ String.format("%.3f", total/i));
				
			} else
				System.out.println("File " + fileName + " is empty.");
			in.close();
		} catch(Exception e){
			e.printStackTrace();
		}finally{
			kb.close();
		}
	}
}
