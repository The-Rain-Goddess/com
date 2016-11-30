package com.rain.game.Alacrity.main;

import com.rain.game.Alacrity.gui.MainGameFrame;

/**
 * holds the start of the game.
 * @author Black Lotus
 *
 */
public class Genesis {
	static MainGameFrame graphics;
	static UniverseGame game;
	static long GAME_START;
	static boolean GAME_RUNNING;
	
	public static void main(String[] args) {
		GAME_START = System.currentTimeMillis();
		GAME_RUNNING = true;
		
		//graphics start
			graphics = new MainGameFrame();
			new Thread(graphics).start();; //casuing a shit ton of cpu usage, why?
			
		//main game start
			game = new UniverseGame();
			new Thread(game).start();
	}
	
	public static long getTime(){
		return System.currentTimeMillis() - GAME_START;
	}
	
	public static boolean getGameRunning(){
		return GAME_RUNNING;
	}
}
