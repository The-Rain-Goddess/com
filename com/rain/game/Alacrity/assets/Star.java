/**
 * 
 */
package com.rain.game.Alacrity.assets;

import java.awt.Color;
import java.awt.geom.Path2D;

import com.rain.game.Alacrity.battle.Direction;
import com.rain.game.Alacrity.main.Asset;

/**
 * @author Rain-Mobile02
 *
 */
public class Star extends Asset implements Drawable {
	//private Direction position;
	//private List<Direction> positionComponents;
	//private Color starColor;
	//private Direction size;
	protected long mass, diameter;
	
//public constructors	
	public Star() {
		super();
	}
	
	public Star(String name, Direction pos, Color col){
		super(name, pos, col, new Direction(50,50), 5);
	}
	
	public Star(String name, Direction pos, Color col, Direction size, int t){
		super(name, pos, col, size, t);
	}
	
	/* (non-Javadoc)
	 * @see com.rain.game.Alacrity.assets.Asset#getColor()
	 */
	@Override
	public Color getColor() {
		return starColor;
	}

	@Override
	public Direction getPosition() {
		return position;
	}
	
	@Override
	public String toString(){
		return "Star: " + super.name + " at: " + super.getPosition();
	}

	@Override
	public Path2D getPath() {
		// TODO Auto-generated method stub
		return null;
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

}
