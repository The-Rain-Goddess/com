package com.raingoddess.appServer;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;
public class ClientHandler extends Thread {
	protected Socket s;
	protected DataInputStream in;
	protected DataOutputStream out;
	
	public ClientHandler(Socket s) throws IOException{
		this.s = s;
		in = new DataInputStream( new BufferedInputStream(
				s.getInputStream()));
		out = new DataOutputStream( new BufferedOutputStream(
				s.getOutputStream()));
		
	}
	
	protected static Vector<ClientHandler> handlers = new Vector<ClientHandler>();
	
	@Override
	public void run(){
		
		try{
			handlers.addElement(this);
			while(true){
				String msg = in.readUTF();
				broadcast(msg);
			}
		} catch(IOException e){
			e.printStackTrace();
		} finally{
			handlers.removeElement(this);
			try{
				s.close();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	protected static void broadcast(String msg){
		System.out.println(msg);
	}
}
