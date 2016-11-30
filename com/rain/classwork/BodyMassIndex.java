package com.rain.classwork;

import java.util.Scanner;

public class BodyMassIndex {
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		p("Please enter your height: ");
		int height = kb.nextInt();
		if(height <= 0 || height > (12*8))
			{p("That's an invalid height."); kb.close(); return;}
		p("Please enter your weight: ");
		int weight = kb.nextInt();
		kb.close();
		if(weight <= 0 || weight > 1000){
			p("That's an invalid weight.");
			return;
		}
		double bmi = weight * 703 / Math.pow(height, 2);
		System.out.printf("BMI = %.2f\n", bmi);
		if(bmi >= 18.5 && bmi <=25){
			p("You are optimal.");
			return;
		} else if(bmi < 18.5){
			p("You are underweight.");
			return;
		} else{
			p("You are overweight.");
			return;
		}
	}
	
	public static void p(String in){
		System.out.println(in);
	}
}
