package com.rain.classwork;

/* ==========================================================================
 *
 *	PROGRAM NAME :	YoYoDayneSatellite.java
 *
 *	Compilation : javac YoYoDayneSatellite.java
 *	Execution: java YoYoDayneSatellite
 *
 *	Programmer: Ryan May
 *
 *	Objects imported: 	java.io.BufferedWriter;
						java.io.IOException;
						java.nio.charset.Charset;
						java.nio.charset.StandardCharsets;
						java.nio.file.Files;
						java.nio.file.Path;
						java.nio.file.Paths;
						java.nio.file.StandardOpenOption;
						java.util.ArrayList;
						java.util.Collections;
						java.util.Comparator;
						java.util.List;
 *
 *  Parameters In: None
 *
 *  Returned Values: None 
 *
 *	Date Modified: 1/28/2015
 *
 *	Purpose: 1. Read the data in the file; put it into an array of arbitrary length, but no larger than 86,400 elements, the number of seconds in a day. The data will consist of two items, one is a temperature reading, the other is a timestamp. It will be sorted by timestamp in the input file. I have added a sample data file for you to test your code with. 
			
			2. Convert each of the temperature array elements from degrees Kelvin to degrees Fahrenheit (YoYoDyne's managers don't understand degrees Kelvin. You can draw your own conclusions about YoYoDyne's management from that;=)) and put them into another array. 
			
			3. Sort the Kelvin array in ascending order. 
			
			4. Write the sorted Kelvin data, the Fahrenheit data, and the timestamp to a file; name the file YYD_XXXXXXX.out, where XXXXXXX is your employee (student) ID number. The format for the temperature data will be up to 5 digits in front of the decimal point and 2 behind, as in 99,999.99. Make sure to put a comma in the appropriate place in the data for YoYoDyne management. The date format will be HH:MM:SS for Hours, Minutes, and Seconds. 
			
			5. Close all files and exit the program. 	
			
 *  Modification History: 
 *
 *  Date: 4/27/2017
 *  Programmer: RM
 *  Requirement: Create Program 
 *  
 * ===========================================================================
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class YoYoDayneSatellite {

	public static void main(String[] args) {
		try{
			Path inFile = Paths.get("Satellite Data .dat");
			List<String> input = Files.readAllLines(inFile, Charset.defaultCharset());
			List<SatData> data = populateData(input);
			sort(data);
			outputToFile(data);
						
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void  sort(List<SatData> data) {
		Collections.sort(data, new Comparator<SatData>(){
			@Override
			public int compare(SatData s1, SatData s2){
				return (s1.getTemperatureAsKelvin() > s2.getTemperatureAsKelvin()) ? -1 : 1;
			}
		}); 
	}
	
	public static List<SatData> populateData(List<String> input) {
		List<SatData> data = new ArrayList<>();
		for(int i = 0; i<input.size(); i++){
			SatData tmp = new SatData(input.get(i).trim());
			data.add(tmp);
		} return data;
	}

	
	public static void outputToFile(List<SatData> data) throws IOException{
		Path outFile = Paths.get("YYD_10160991.out");
		Files.deleteIfExists(outFile);
		Files.createFile(outFile);
		BufferedWriter bw = Files.newBufferedWriter(outFile, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
		for(int i = 0; i<data.size(); i++){
			bw.write(data.get(i).toString() + "\n");
		} bw.close();	
	}
}
