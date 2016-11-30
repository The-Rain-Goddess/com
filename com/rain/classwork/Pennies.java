package com.rain.classwork;

import java.util.Scanner;

public class Pennies {

	public Pennies() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter the number of worked days:");
		int days = kb.nextInt();
		while(days<=0 || days>=62){
			System.out.println("Invalid number of days.");
			System.out.println("Enter the number of days again:");
			days = kb.nextInt();
		}
		double tot = 0.01;
		double con = tot;
		//System.out.printf("Day: %d, Payment: $%.2f\n", 1, 0.01);
		for(int i = 0; i<days; i++){
			System.out.printf("Day: %d, Payment: $%,.2f\n", (i+1), tot);
			tot = (tot* 2);
		} tot = tot - con; 
		System.out.printf("Total: $%,.2f\n", tot);
		kb.close();
	}

}
