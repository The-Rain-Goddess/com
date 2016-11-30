package com.rain.classwork;

public class Employer {

	public static void main(String[] args) {
		Employee a = new Employee("Susan Meyers", 47899, "Accounting", "Vice President");
		Employee b = new Employee("Mark Jones", 39119, "IT", "Programmer");
		Employee c = new Employee("Joy Rogers", 81774, "Manufacturing", "Engineer");
		System.out.println("Name                 ID Number  Department           Position            ");
		System.out.println("____________________ __________ ____________________ ____________________");
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
	}

}
