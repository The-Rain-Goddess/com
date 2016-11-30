package com.rain.game.Alacrity.battle;

public class Listener implements Runnable {
	private MyMouseAdapter mouseAdapter;
	
//public constructor
	public Listener(MyMouseAdapter mouseAdapter){
		this.mouseAdapter = mouseAdapter;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(Game.getGameRunning()){
			if(mouseAdapter.getClick()){
				
			}
		}
	}

}
