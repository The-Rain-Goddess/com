package com.rain.classwork;

public class Car {
	private int year;
	private String make;
	private int speed;

	public Car(int y, int s, String make) {
		this.year = y;
		this.make = make;
		this.speed = s;
	}
	
	public int getSpeed(){ return speed;}
	public int getYear(){ return year;}
	public String getMake(){ return make;}
	
	public void accelerate(){
		speed+=25;
		System.out.println("Accelerating...");
		System.out.println("Now the speed is " + speed);
	}
	
	public void brake(){
		if(speed <=5)
			speed-=5;
		
		System.out.println("Braking...");
		System.out.println("Now the speed is " + speed);
	}

}
