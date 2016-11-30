package com.rain.classwork;

import java.util.Scanner;

public class Midterm {

	public Midterm() {
		
	
	}
	
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter the starting temperature in Celsius:");
		double start = kb.nextDouble();
		while(start<-100.0 || start>100.0){
			System.out.println("Starting temperature should be between -100.0 and 100.0. Please re-enter:");
			start = kb.nextDouble();
		}
		System.out.println("Enter the ending temperature in Celsius:");
		double end  = kb.nextDouble();
		while(end<-40.0 || end>100.0){
			System.out.println("Ending temperature should be between -40.0 and 100.0. Please re-enter:");
			end = kb.nextDouble();
		}
		System.out.println("Enter the step:");
		double step = kb.nextDouble();
		while(step<.1 || step>10.0){
			System.out.println("Step should be between 0.1 and 10.0. Please re-enter:");
			step = kb.nextDouble();
		}
		
		System.out.println("*********************");
		System.out.println("*    C    *    F    *");
		System.out.println("*********************");
		double stop = (end-start)/step;
		for(int i = 0; i<=stop; i++){
			double fahren = (9.0/5.0 * start) + 32.0;
			if(start>=10 )
			System.out.print("*   ");
			else if(start<=-10)
				System.out.print("*  ");
			else if(start<0)
				System.out.print("*   ");
			else if(start==0)
				System.out.print("*    ");
			else
				System.out.print("*    ");
			System.out.print(String.format("%.1f", start)+"  ");
			if(fahren>=100)
				System.out.print("*  ");
			else if(fahren>=10)
				System.out.print("*   ");
			else if( fahren<=-10)
				System.out.print("*  ");
			else if(fahren<0)
				System.out.print("*   ");
			else if(fahren==0)
				System.out.print("*    ");
			else
				System.out.print("*    ");
			System.out.print(String.format("%.1f", fahren));
			
			System.out.print("  *\n");
			
			start+=step;
		}
		System.out.println("*********************");
		
		kb.close();
	}
}
