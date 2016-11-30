package com.rain.game.Alacrity.battle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyAdapter implements KeyListener {
	private char key;
	private boolean newKey = false;
	
	public KeyAdapter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		key = arg0.getKeyChar();
		newKey = true;
		arg0.consume();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public char getKey(){
		newKey = false;
		return key;
	}
	
	public boolean getNewKey(){
		return newKey;
	}

}
