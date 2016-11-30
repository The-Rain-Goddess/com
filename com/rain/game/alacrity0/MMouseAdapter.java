package com.rain.game.alacrity0;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MMouseAdapter extends MouseAdapter {
	
	private DrawPanel drawPanel;

   	public MMouseAdapter(DrawPanel drawPanel) {
   		this.drawPanel = drawPanel;
   	}

   	@Override
   	public void mousePressed(MouseEvent e) {
   		drawPanel.curveStart(e.getPoint());
   	}

   	@Override
   	public void mouseReleased(MouseEvent e) {
   		drawPanel.curveEnd(e.getPoint());
   	}

   	@Override
   	public void mouseDragged(MouseEvent e) {
   		drawPanel.curveAdd(e.getPoint());	
   	}
}
