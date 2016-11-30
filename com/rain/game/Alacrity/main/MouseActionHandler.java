/**
 * 
 */
package com.rain.game.Alacrity.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import com.rain.game.Alacrity.battle.Direction;
import com.rain.game.Alacrity.gui.DrawPanel;

/**
 * @author Rain
 *
 */
public class MouseActionHandler extends MouseAdapter{
	private Direction point;
	private boolean hasClick = false;
	private boolean rightClick, drag =false;
	/**
	 * 
	 */
	public MouseActionHandler() {
		
	}
	
	private Direction scaleClick(double x, double y, double s){
		return new Direction(x/s, y/s);
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e){
		//int notches = e.getWheelRotation();
		//System.out.println("Notches: " + notches);
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		double scale = DrawPanel.getScalar();
		if(scale!=1){
			Direction newClick = scaleClick(e.getPoint().getX(), e.getPoint().getY(), scale);
			point = newClick;
		} else{
			point = new Direction(e.getPoint().getX(), e.getPoint().getY());
		}
		
		if(e.getButton()==MouseEvent.BUTTON3)
			rightClick = true;
		hasClick = true;
		drag = true;
		e.consume();
	}
	
	@Override
	public void mouseReleased(MouseEvent e){
		double scale = DrawPanel.getScalar();
		if(scale!=1){
			Direction newClick = scaleClick(e.getPoint().getX(), e.getPoint().getY(), scale);
			point = newClick;
		} else{
			point = new Direction(e.getPoint().getX(), e.getPoint().getY());
		} 
		
		if(e.getButton()==MouseEvent.BUTTON3)
			rightClick = true;
		hasClick = true;
		drag =false;
		e.consume();
	}
	
	public boolean isDrag(){return drag; }
	
	public Direction getPoint(){ 
		double scale = DrawPanel.getScalar();
		if(scale==1)
			return new Direction(point.getX() - DrawPanel.getOffset().getX(), point.getY() - DrawPanel.getOffset().getY());
		else{
			Direction pos = DrawPanel.getOffset();
			return new Direction(point.getX() - (pos.getX()/scale), point.getY() - (pos.getY()/scale));
		}
	}
	
	public boolean hasClick() { 
		if(hasClick){
			hasClick = false;
			return true;
		} else
			return false;
	}
	
	public boolean rightClick(){ return rightClick; }

}
