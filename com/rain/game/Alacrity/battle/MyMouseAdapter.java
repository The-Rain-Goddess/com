package com.rain.game.Alacrity.battle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
//this is all for player 1, the hooman player
public class MyMouseAdapter extends MouseAdapter implements MouseMotionListener {
	private Direction point;
	private boolean rightClick;
	private boolean click = false;
	private boolean drag = false;
	public MyMouseAdapter(){

	}
	
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
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton()==MouseEvent.BUTTON1){
			drag = true;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		point = new Direction(e.getX(), e.getY());
	}
	
	@SuppressWarnings("unused")
	private boolean outOfRange(Direction mousePoint){
		if(		mousePoint.getY() >= (GamePanel.BI_HEIGHT + GamePanel.getOffset().getY() - 30) ||
				mousePoint.getY() <= (0 + GamePanel.getOffset().getY() + 30) ||
				mousePoint.getX() >= (GamePanel.BI_WIDTH + GamePanel.getOffset().getX() - 30) || 
				mousePoint.getX() <= (0 + GamePanel.getOffset().getX() - 30) )
			return true;
		else
			return false;
	}
	
	
//non-private access and mutators
	//reset whether a click happened
	public void setClick(boolean state){
		click = state;
	}
	
	public Direction getPoint(){ return point; }
	
	/** 
	 * returns true if the click was a right click
	 * @return
	 */
	public boolean getClickType(){ return rightClick; }
	
	//to check if a click happened
	public boolean getClick(){ return click; }
	
	public boolean getDrag(){ return drag; }
}
