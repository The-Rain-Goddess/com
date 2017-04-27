package com.rain.classwork;

import java.util.Locale;

public class SatData {
	String timeStamp;
	double temperature;
	
	public SatData(String input) {
		String[] elements = input.split(" ");
		this.timeStamp = elements[1];
		this.temperature = Double.parseDouble(elements[0]);
	}
	
	double getTemperatureAsKelvin() { return temperature; }
	
	double getTemperatureAsFahrenheit(){ return (9.0/5.0) * (temperature - 273.0) + 32.0; }
	
	String getTimeStamp() { return timeStamp; }
	
	@Override
	public String toString(){
		return String.format(Locale.US, "%,+7.2fK ", temperature) + String.format(Locale.US, "%,+7.2fF ", this.getTemperatureAsFahrenheit()) + timeStamp;	
	}

}
