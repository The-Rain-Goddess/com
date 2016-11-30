package com.rain.classwork;

import java.util.Scanner;

public class PrimalityList {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter a number: ");
		int num = kb.nextInt();
		System.out.print("2 ");
		for (int i = 3; i < num; i++)
			if (isPrime(i))
				System.out.print(i + " ");
		kb.close();
	}

	public static boolean isPrime(int n) {
		if (n == 2)
			return true;
		for (int i = 2; i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
}
