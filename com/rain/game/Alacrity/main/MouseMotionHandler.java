package com.rain.game.Alacrity.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import com.rain.game.Alacrity.battle.Direction;
import com.rain.game.Alacrity.gui.DrawPanel;
//this is all for player 1, the hooman player
public class MouseMotionHandler extends MouseMotionAdapter{
	private Direction point = new Direction(0.0, 0.0);;
	private boolean rightClick;
	private boolean click = false;
	private boolean drag = false;
	public MouseMotionHandler(){

	}
	//reused code from a mouseactionadapter below
	/*
	@Override
	public void mousePressed(MouseEvent e){
		point = new Direction(e.getPoint().getX(), e.getPoint().getY());
		rightClick = (e.getButton() == MouseEvent.BUTTON3);
		if(!rightClick)
			drag = true;
	}
	
	@Override
	public void mouseReleased(MouseEvent e){
		point  = new Direction(e.getPoint().getX(), e.getPoint().getY());
		rightClick = (e.getButton() == MouseEvent.BUTTON3);
		click = true;
		//if(!rightClick)
			drag = false;
	} */
	
	@Override
	public void mouseDragged(MouseEvent e) {
		point = new Direction(e.getPoint().getX(), e.getPoint().getY());
		
		e.consume();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		point = new Direction(e.getPoint().getX(), e.getPoint().getY());
		e.consume();
	}
	
//non-private access and mutators
	//reset whether a click happened
	public void setClick(boolean state){
		click = state;
	}
	
	public Direction getPoint(){ return point; }
	
	public Direction getRealPoint(){ 
		Direction pos = DrawPanel.getOffset();
		double scale = DrawPanel.getScalar();
		return new Direction((point.getX()/scale) - (pos.getX()/scale), (point.getY()/scale) - (pos.getY()/scale));
	}
	/** 
	 * returns true if the click was a right click
	 * @return
	 */
	public boolean getClickType(){ return rightClick; }
	
	//to check if a click happened
	public boolean getClick(){ return click; }
	
	public boolean getDrag(){ return drag; }
}
