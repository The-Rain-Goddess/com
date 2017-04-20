package com.rain.classwork;

public class Recursion {

	public static void main(String[] args) {
		int reverse = 123456789;
		//System.out.println(reverseDigits(reverse));
		
		System.out.println(recursiveReverseDigits(reverse, 9));
		
	}
	
	public static String reverseDigits(int in){
		String re = in+"";
		String reversed = "";
		for(int i = re.length(); i>0; i--){
			reversed = reversed + re.substring(i-1, i);
		} return reversed;
	}
	
	public static String recursiveReverseDigits(int in, int index){
		String number = in + "";
		if(index==1)
			return number.substring(index-1, index);
		else 
			return number.substring(index-1, index) + recursiveReverseDigits(in, index-1);
	}
	
	public static void computation(String str, String end, int index){
			
	}

}
