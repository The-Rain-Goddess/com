package com.rain.game.alacrity0;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


public class Frigate implements Ship {
	private List<Point2D> position = new ArrayList<>();
	private List<Point2D> swap = new ArrayList<>();
	private Point2D velocity;
	private Point2D positionVector;
	private double height;
	private String shipName;
	
	public Frigate(String name, ArrayList<Point2D> pos){
		this.position = pos;
		this.shipName = name;
		this.velocity = new Point2D.Double(.01, .01);
		
	}
	
	public Frigate(String name, Point2D p, Point2D v, double h){
		shipName = name;
		velocity = v;
		height = h;
		this.positionVector = new Point2D.Double(p.getX()+h, p.getY());
		rotatePosition();
	}
	
	private void resetPositionPoints(Point2D p){
		double theta =  Math.acos(p.getX() / height);
		position.add(p);
		position.add(new Point2D.Double((height * Math.sin(theta-(Math.PI/4))) + p.getX(), 
				(height * Math.cos(theta-(Math.PI/4))) + (p.getY())));
		position.add(new Point2D.Double((height * Math.sin(theta+(Math.PI/4)))+ p.getX(), 
				(p.getY()) + (height * Math.cos(theta+(Math.PI/4))) ));
		//positionVector = new Point2D.Double((height * Math.sin(theta))+ p.getX(), (p.getY()) + (Math.sin(theta)) );
	}
	
	@Override
	public void rotatePosition(){
		double vNorm = Math.pow(velocity.getX(), 2) + Math.pow(velocity.getY(), 2);
		vNorm = Math.pow(vNorm, .5);
		double displacement = (velocity.getX() * positionVector.getX() + 
							velocity.getY() * positionVector.getY()) / Math.pow(vNorm, 2);
		positionVector = new Point2D.Double(velocity.getX() * displacement, 
				velocity.getY() * displacement);
		resetPositionPoints(positionVector);
	}

	@Override
	public void updatePosition() {
		// TODO Auto-generated method stub
		swap = new ArrayList<>();
		//System.out.println("Pos, swap: " + position + " " + swap);
		for(int i = 0; i<position.size(); i++){
			Point2D temp_point = position.get(i);
			swap.add(new Point2D.Double(temp_point.getX() + velocity.getX(),
					temp_point.getY() + velocity.getY()));
		} position = swap;
		//System.out.println("Pos, swap: " + position + " " + swap);
		swap = null;
		//System.out.println("Pos, swap: " + position + " " + swap);
	}
	
	@Override
	public int getVertices(){
		return position.size();
	}
	
	@Override
	public List<Point2D> getPosition(){
		return position;
	}

	@Override
	public void updatePosition(Point2D p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateVelocity(Point2D p) {
		// TODO Auto-generated method stub
		velocity = p;
		rotatePosition();
	}
	
	public String getName(){
		return shipName;
	}
	
	@Override
	public String toString(){
		return shipName + ":: Position @ x:" + position.get(0).getX() + ", y: " 
				+ position.get(0).getY();
	}

}
