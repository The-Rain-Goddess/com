package com.rain.classwork;

public class Care {

	public static void main(String[] args){
		Car a = new Car(2004, 0, "Porsche");
		System.out.println("Current status of the car:");
		System.out.println("Year model: " + a.getYear());
		System.out.println("Make: " + a.getMake());
		System.out.println("Speed: " + a.getSpeed());
		System.out.println("");
		a.accelerate();
		System.out.println("");
		a.brake();
		//System.out.println("");
	}

}
