package com.rain.app.server;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientPopulator {
	private Socket s;
	protected DataOutputStream out;
	protected DataInputStream in;
	
	
	
	public ClientPopulator(String command) throws IOException{
		System.out.println("Client is good");
		
		s = new Socket("localHost", 48869);
		in = new DataInputStream( new BufferedInputStream(s.getInputStream()));
		out = new DataOutputStream( new BufferedOutputStream(s.getOutputStream()));
		send(command);
	}
	
	public void send(String command){
		List<String>responses = new ArrayList<>(10);
	try {
                //this.s = new Socket("71.94.133.203", 48869); //  "71.94.133.203"
                //////////very important for net
                //this.in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                //this.out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));

                out.writeUTF(command);
                out.writeUTF("EOF");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    String rsp = in.readUTF();
                    while (!rsp.equals("EOF")) {
                        //System.out.println("RSP: " + rsp);
                        responses.add(rsp);
                        rsp = in.readUTF();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                        out.close();
                        s.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
              System.err.println("Command: " +command+ " from " + Thread.currentThread().getName() + " was sent successfully");  
            } String temp_string = "";
            try{
                temp_string =  responses.get(0);
                System.out.println(temp_string);
            } catch(IndexOutOfBoundsException e){
                e.printStackTrace();
                System.out.println(responses);
            }
	}
}