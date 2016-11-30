package com.rain.classwork;

import java.io.IOException;
import java.util.Scanner;

public class Wholesale {

	public Wholesale() {
		
	}
	
	public static void main(String[] args)throws IOException{
		int input = 0;
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		while(true){
			
			System.out.println("Please enter the wholesale cost or -1 exit:");
			input = kb.nextInt();
			if(input==-1) System.exit(0);;
			while(input <-1){
				System.out.println("Wholesale cost cannot be a negative value.");
				System.out.println("Please enter the wholesale cost again or -1 exit:");
				input = kb.nextInt();
				if(input==-1) return;
			}
			System.out.println("Please enter the markup percentage or -1 exit:");
			int markup = kb.nextInt();
			if(markup==-1) return;
			while(markup<-100){
				System.out.println("Markup cannot be less than -100%.");
				System.out.println("Please enter the markup again or -1 exit:");
				markup = kb.nextInt();
				if(markup==-1) return;
			}
			System.out.println("The retail price is: " + String.format("%.2f", ((0.0+markup/100.0)*(input+0.0)+input) ));
		}
	}
}
