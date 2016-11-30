package com.rain.classwork;

import java.util.Scanner;

public class Populations {

	public Populations() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int startNum = 0, daysNum = 0;
		double percentPop = 0.0;
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter the starting number of organisms:");
		while( startNum<2 ){
			
			
			startNum = kb.nextInt();
			if(startNum < 2){
				System.out.println("Invalid number. The starting number cannot be less than 2. Please re-enter:");
				//System.out.println("Please enter the starting number of organisms:");
			}
		}
		System.out.println("Please enter the average daily population increase (percentage):");
		while(percentPop <= 0){
			
			percentPop = kb.nextDouble();
			if(percentPop <= 0){
				System.out.println("Invalid number. The average daily population increase cannot be negative. Please re-enter:");
				//System.out.println("Please enter the average daily population increase (percentage):");
			}
		}
		System.out.println("Please enter the number of days they will multiply:");
		while(daysNum <= 1){
			
			daysNum = kb.nextInt();
			if(daysNum <= 1){
				System.out.println("Invalid number. The number of days cannot be less than 1. Please re-enter:");
				//System.out.println("Please enter the number of days they will multiply:");
			}
		}
		
		for(int i = 0; i<daysNum; i++){
			startNum = (int) (startNum + (startNum * percentPop / 100));
		}
		System.out.print("The final population after "+ daysNum + " days is " + startNum + ".");
		
		kb.close();
	}

}
