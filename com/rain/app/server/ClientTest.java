package com.rain.app.server;
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
	
	public static void main(String[] args) throws IOException{
		ClientTest ct = new ClientTest();
		ct.send("There is no god");
	}
	
	public ClientTest() throws IOException{
		System.out.println("Client is good");
		
		s = new Socket("localHost", 48869);
		in = new DataInputStream( new BufferedInputStream(s.getInputStream()));
		out = new DataOutputStream( new BufferedOutputStream(s.getOutputStream()));
		
	}
	
	public void send(String command){
		try{
			out.writeUTF(command);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
