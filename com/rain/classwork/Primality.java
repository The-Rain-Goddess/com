package com.rain.classwork;

import java.util.Scanner;

public class Primality {

	public static void main(String[]args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter a number for primality test (or a negative number to exit): ");
		int num = kb.nextInt();
		while(num >=1){
			
			if(isPrime(num))
				System.out.println("Number " + num + " is a prime number.");
			else
				System.out.println("Number " + num + " is not a prime number.");
			System.out.println("Enter a number for primality test (or a negative number to exit): ");
			num = kb.nextInt();
		}
		kb.close();
	}
	
	public static boolean isPrime(int n){
		if(n == 2) return true;
		for(int i = 2; i < Math.sqrt(n)+.1; i++){
			if(n%i==0)
				return false;
		}
		return true;
	}

}
