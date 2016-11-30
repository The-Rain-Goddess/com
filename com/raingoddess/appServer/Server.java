package com.raingoddess.appServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
	
	public static void main(String[] args) throws IOException{
		if(args.length != 1)
			throw new RuntimeException("Syntax: Server <port>");
		new Server(48869); //Integer.parseInt(args[0])
		
		System.out.println("Server starts");
		
		ClientTest ct = new ClientTest();
		ct.send("There is no god");
		
	}
	
	@SuppressWarnings("resource")
	public Server(int port) throws IOException{
		ServerSocket server = new ServerSocket(port);
		
		System.out.println("Socket is good");
		
		while(true){
			Socket client = server.accept();
			System.out.println("Accepted from " + client.getInetAddress());
			
			ClientHandler c = new ClientHandler(client);
			c.start();
		}
	}
}
