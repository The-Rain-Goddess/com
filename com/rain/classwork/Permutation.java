package com.rain.classwork;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Permutation {

	public static void permutation(String str) { 
	    permutation("", str); 
	}

	private static void permutation(String prefix, String str) {
	    int n = str.length();
	    if (n == 0){ //System.out.println(prefix);
	    	try{
	    		File file = new File("names.txt");
	    		FileWriter pw = new FileWriter(file, true);
	    		pw.append(prefix + "\n");
	    		pw.close();
	    	} catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }
	    else {
	        for (int i = 0; i < n; i++)
	            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
	    }
	}
	
	public static void main(String[] args){
		permutation("Michelle Q Miclat");
	}
}
