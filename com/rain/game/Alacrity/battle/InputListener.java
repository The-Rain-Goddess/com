package com.rain.game.Alacrity.battle;

import java.util.Scanner;

public class InputListener implements Runnable {
	private Scanner kb;
	private String input;
	private boolean hasInput = false;
	public InputListener() {
		// TODO Auto-generated constructor stub
		kb = new Scanner(System.in);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(Alpha.gameRunning){
				input = kb.next();
				hasInput = true;
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			kb.close();
		}
	}
	
	public String getInput(){
		hasInput = false;
		return input;
	}
	
	public boolean hasInput(){
		return hasInput;
	}

}
