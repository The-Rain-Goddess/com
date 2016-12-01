/**
 * 
 */
package com.rain.game.Alacrity.assets;

import java.awt.Color;
import java.awt.geom.Path2D;

import com.rain.game.Alacrity.battle.Direction;
import com.rain.game.Alacrity.main.Asset;

/**
 * @author Black Lotus
 *
 */
public class Planet extends Asset implements Drawable {
	protected long mass, diameter;

	/**
	 * @param s1
	 * @param direction2 
	 * @param blue 
	 * @param direction 
	 * 
	 */
	public Planet(String name, Direction pos, Color col, Direction size, Star s) {
		super(name, pos, col, size, s);
	}
	
	public Planet(String name, Direction pos, Color col, Direction size, Star s, int t) {
		super(name, pos, col, size, s, t);
	}

	/**
	 * @param pos
	 * @param col
	 */
	public Planet(String name, Direction pos, Color col) {
		super(name, pos, col, new Direction(10,10), 1);
	}

	/**
	 * @param pos
	 * @param col
	 * @param size
	 */
	public Planet(String name, Direction pos, Color col, Direction size) {
		super(name, pos, col, size, 1);
	}

	/* (non-Javadoc)
	 * @see com.rain.game.Alacrity.assets.Drawable#getPath()
	 */
	@Override
	public Path2D getPath() {
		//TODO: add return path
		return null;
	}
	
	@Override
	public String toString(){
		return "Planet: " + super.name + " at: " + super.getPosition();
	}
	
	public long getMass() {
		return mass;
	}

	public void setMass(long mass) {
		this.mass = mass;
	}

	public long getDiameter() {
		return diameter;
	}

	public void setDiameter(long diameter) {
		this.diameter = diameter;
	}
	
	public String getComposition(){
		if(super.getColor()==Color.RED)
			return "Iron";
		else if(super.getColor()==Color.BLUE)
			return "H2O";
		else if(super.getColor()==Color.GRAY)
			return "Silicon";
		else if(super.getColor()==Color.GREEN)
			return "Silicon";
		else 
			return "Unidentified";
	}
	
	public String getAtmosphere(){
		return "N/A";
	}
}
