package com.rain.classwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Final {

	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Enter the filename or type QUIT to exit:");
		Scanner kb = new Scanner(System.in);
		String in = kb.nextLine();
		if(in.toLowerCase().equals("quit")){
			kb.close();
				return;
		} else{
			File file = new File(in);
			while(!file.exists()){
				System.out.println("File " + in + " does not exist");
				System.out.println("Enter the filename or type QUIT to exit:");
				in = kb.nextLine();
				if(in.toLowerCase().equals("quit")){
					kb.close();
						return;
				}
				file = new File(in);
			}
			Scanner k = new Scanner(file);
			int count = 0;
			double total = 0;
			double avg = 0;
			while(k.hasNext()){
				count++;
				total = total + k.nextDouble();
			} k.close();
			if(count==0)
			{
				System.out.println("File input4.txt is empty.");
				return;
			}
			avg = total/(count + 0.0);
			System.out.println("Count: " + count);
			System.out.println("Total: " + String.format("%.3f", total));
			System.out.println("Average: " + String.format("%.3f", avg));
		}
		kb.close();
	}
}
