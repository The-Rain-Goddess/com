package com.rain.game.Alacrity.battle;

public class Alpha {
	public static boolean gameRunning;
	public Alpha(){
		gameRunning = true;
		new Thread(new Game()).start();
	}

	public static void main(String[] args) {
		gameRunning = true;
		new Thread(new Game()).start();
	}
	
	public static boolean getGameRunning(){ return gameRunning; }
	
	public static void setGameRunning(boolean running){ gameRunning = running;}
}
