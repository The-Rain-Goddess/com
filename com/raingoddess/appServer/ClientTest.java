package com.raingoddess.appServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientTest {
	private Socket s;
	protected DataOutputStream out;
	protected DataInputStream in;
	
	public ClientTest() throws IOException{
		s = new Socket("localHost", 48869);
		in = new DataInputStream( new BufferedInputStream(s.getInputStream()));
		out = new DataOutputStream( new BufferedOutputStream(s.getOutputStream()));
		System.out.println("Client is good");
	}
	
	public void send(String command){
		try{
			out.writeUTF(command);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
