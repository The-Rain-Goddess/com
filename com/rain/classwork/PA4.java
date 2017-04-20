package com.rain.classwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* ==========================================================================
*
*	PROGRAM NAME :	PA4.java
*
*	Compilation : javac PA4.java
*	Execution: java PA4
*
*	Programmer: Ryan May 
*
*	Objects imported: java.util.Scanner
*
*  Parameters In: None
*
*  Returned Values: None 
*
*	Date Modified: 3/25/2017
*
*	1) Ask the User to input either an integer or just <RETURN> (the ENTER key)

2) While the User enters integers, put the numbers into an array of size 10

3) When the User stops entering numbers, print out the array in the same order they were input, but all on one line. 

4) If the User does not enter any numbers and the array is empty, print out "No numbers were entered." 

5) If the User enters too many numbers, put out an Error message that the size of the array has been exceeded; then print out the numbers entered on the next line. 
*
*
*  Modification History: 
*
*  Date: 3/25/2017
*  Programmer: RM
*  Requirement: Create Program 
*
* ===========================================================================
*/

public class PA4 {

	public static void main(String[] args) {
		List<String> inputList = new ArrayList<String>(10);
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter a integer, followed by the return key, or just enter the return key to exit");
		String userIn = kb.nextLine();
		while(!userIn.equals("")){
			if(inputList.size()==10){
				System.out.println("the size of the array has been exceeded");
				break;
			} else{
				inputList.add(userIn);
				userIn = kb.nextLine();
			}
		} if(inputList.size()==0)
			System.out.println("No numbers were entered.");
		else
			System.out.println(inputList);
		kb.close();
	}

}
