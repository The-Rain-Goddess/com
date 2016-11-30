package com.rain.classwork;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ClassLists {

	public ClassLists() {
		// TODO Auto-generated constructor stub
	}
	
	 public static void main(String[] args){
	    	System.out.println("Please enter an integer number or STOP to exit:");
	    	Scanner kb = new Scanner(System.in);
	    	String in = kb.nextLine();
	    	double sum = 0.0;
	    	ArrayList<Integer> list = new ArrayList<>();
	    	while(!in.equals("STOP") && !in.equals("stop")){
	    		System.out.println("Please enter an integer number or STOP to exit:");
	    		list.add(Integer.parseInt(in));
	    		sum = sum + Integer.parseInt(in);
	    		in = kb.nextLine();
	    	}
	    	double avg = sum / list.size();
	    	Collections.sort(list);
	    	if(list.size()<=1){
	    	System.out.println("The largest number is: " + list.get(list.size()-1));
	    	System.out.println("The smallest number is: " + list.get(0));
	    	System.out.println("The total is: " + list.get(0));
	    	}else{
	    	System.out.println("The largest number is: " + list.get(list.size()-1));
	    	System.out.println("The smallest number is: " + list.get(0));
	    	System.out.println("The total is: " + (int)sum);
	    	System.out.println("The average is: " + avg);
	    	}kb.close();
	    }
	     
}
