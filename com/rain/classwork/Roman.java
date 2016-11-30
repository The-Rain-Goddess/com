package com.rain.classwork;

import java.util.Scanner;

public class Roman {
	public static void main(String[] args){
		System.out.println("Please enter a number 1..10: ");
    	Scanner kb = new Scanner(System.in);
    	int in = kb.nextInt();
    	kb.close();
    	if(in>10 || in<1)
    		{System.out.println("That's not a valid number."); return; }
    	
    	//String out = "";
    	if(in<4){
    		System.out.print("The roman numeral for " + in + " is ");
    		for(int i = 0; i<in; i++)
    			System.out.print("I");
    		System.out.print(".\n");
    	} else if(in == 4)
    		System.out.println("The roman numeral for "  + in + " is IV.");
    	else if(in ==5)
    		System.out.println("The roman numeral for " + in + " is V.");
    	else if(in < 9){
    		System.out.print("The roman numeral for " + in + " is V.");
    		for(int i = 0; i< (in-5); i++){
    			System.out.print("I");
    		} System.out.print(".\n");
    	} else if(in==9)
    		System.out.println("The roman numeral for " + in + " is IX.");
    	else 
    		System.out.println("The roman numeral for " + in + " is X.");
    	
    	
	}
}
