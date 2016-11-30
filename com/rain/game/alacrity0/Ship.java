package com.rain.game.alacrity0;

import java.awt.geom.Point2D;
import java.util.List;

public interface Ship {
	
	public void updatePosition();
	
	public void rotatePosition();
	
	public void updatePosition(Point2D p);
	
	public void updateVelocity(Point2D p);
	
	public int getVertices();
	
	public List<Point2D> getPosition();
}
