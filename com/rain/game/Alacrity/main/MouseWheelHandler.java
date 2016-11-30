/**
 * 
 */
package com.rain.game.Alacrity.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.rain.game.Alacrity.gui.DrawPanel;

/**
 * @author Rain
 *
 */
public class MouseWheelHandler extends MouseAdapter implements MouseWheelListener {

	/**
	 * 
	 */
	public MouseWheelHandler() {
	
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e){
		int notches = e.getWheelRotation();
		//System.out.println("Notches " + notches);
		
		DrawPanel.incrementScalar((notches+0.0)/10);
	}

}
