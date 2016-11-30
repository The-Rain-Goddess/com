package com.rain.game.Alacrity.battle;

import java.awt.geom.Point2D;

public class Direction {
	private double x, y;
	
	public Direction(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Direction(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public double getX(){return x;}
	
	public double getY(){return y;}
	
	public Point2D getPoint(){return new Point2D.Double(x,y);}
	
	@Override
	public String toString(){
		return "<" + String.format("%,.2f", x) + ", " + String.format("%,.2f", y) + ">";
	}
	
	public double getMagnitude(){
		double combine = Math.pow(x, 2) + Math.pow(y, 2);
		return Math.abs(Math.sqrt(combine));
	}
	
	public double dotProduct(Direction other){
		double result;
		result = (other.getX() * x) + (other.getY() * y);
		return result;
	}
}
