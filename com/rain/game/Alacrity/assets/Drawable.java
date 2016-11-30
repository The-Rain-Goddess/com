package com.rain.game.Alacrity.assets;

import java.awt.geom.Path2D;

/**
 * 
 * @author Rain
 * 
 */
public interface Drawable {
	/**
	 * obtains the shape of the object that is to be drawn to screen
	 * @return @Path2D
	 */
	public Path2D getPath();
}
