package com.rain.app.server;
import java.util.Scanner;

public class KeyHandler implements Runnable{
	
//public contructor
	public KeyHandler(){
		
	}

//main loop	
	@Override
	public void run(){
		Scanner kb = null;
	
		try{
			System.out.println("KeyHandler: " + Thread.currentThread().getName() + " is started as KeyHandler.");
			kb = new Scanner(System.in);
			while(true){
				Server.keyboard = kb.nextLine();
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			kb.close();
		}
	}
}
