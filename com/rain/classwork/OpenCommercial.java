package com.rain.classwork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OpenCommercial {

	public static void main(String[] args) throws IOException, UnknownHostException{
	
		System.out.println("Please enter the website name: ");
		Scanner kb = new Scanner(System.in);
		String input = kb.nextLine();
		kb.close();
		System.out.println(input);
		
		Socket s = new Socket(InetAddress.getByName("www."+input+".com"), 80);
		InputStream in = s.getInputStream();
		OutputStream out = s.getOutputStream();
		
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(out));
		bw.write("GET / HTTP/1.1\r\n\r\n");
		//bw.write("Host: " + input+".com\r\n\r\n");
		bw.flush();
		
		List<String> responses = new ArrayList<String>(6);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		System.out.println("Recieving Response: ");
		String line;
		
		while(!(line = br.readLine()).contains("</body>")){ //!line.contains("\n") || !line.toLowerCase().contains("</body>")
			//line = br.readLine();
			//System.out.println(line);
			responses.add(line);
			
		}
		in.close();
		out.close();
		s.close();
		
		System.out.println("Response from Server:");
		for(int i = 4; i>=0; i--){
			System.out.println("Line " + (i+1) + ":" + responses.get(i));
		}
	}

}
