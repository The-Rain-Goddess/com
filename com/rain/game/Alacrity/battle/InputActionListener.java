package com.rain.game.Alacrity.battle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class InputActionListener implements ActionListener {
	private String in = " ";
	private JTextField field;
	private boolean hasInput = false;
	
	public InputActionListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if(field.getText()!=null){
	    	in = field.getText();
		    field.setText("");
		    hasInput = true;
	    } else{
	    	in = " ";
	    }
	}
	
	public void setField(JTextField field){
		this.field = field;
	}
	
	public String getInput(){
		hasInput = false;
		return in;
	}
	
	public boolean hasInput(){return hasInput; }

}
