package com.rain.classwork;

import java.util.Scanner;

//String manipulator
public class Lab2 {
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		System.out.print("Please enter the amount of a purchase: ");
		double cost = kb.nextDouble();
		System.out.println("Purchase Subtotal: " + cost);
		System.out.printf("State Sales Tax: %,.1f\n", (cost * .04));
		System.out.printf("County Sales Tax: %,.1f\n", (cost * .02));
		System.out.printf("Total Sales Tax: %,.1f\n", ((cost * .04) + (cost * .02)));
		System.out.printf("Total Sales: %,.1f\n", (cost + (cost * .04) + (cost * .02)));
		
		kb.close();
		System.exit(0);
		
		
	}
}
