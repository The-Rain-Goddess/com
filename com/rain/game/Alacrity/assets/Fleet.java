/**
 * 
 */
package com.rain.game.Alacrity.assets;

import java.awt.Color;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.rain.game.Alacrity.battle.Direction;
import com.rain.game.Alacrity.battle.Ship;
import com.rain.game.Alacrity.gui.DrawPanel;
import com.rain.game.Alacrity.main.Asset;

/**
 * @author Rain 10/27/16
 *
 */
public class Fleet extends Asset implements Drawable{

	protected List<Ship> shipList = new ArrayList<>();
	protected List<Direction> positionComponents;
	protected List<Direction> positionComponents2;
	protected Direction moveTarget;
	protected Asset actualMoveTarget;
	protected boolean occupying = false;
	
	public Fleet(String name, Direction pos, Direction vel, Direction acc, Direction size, Color col){
		super(name, pos, vel, acc, col);
		super.celestialBody = false;
		super.size = size;
		setupPositionComponents();
		scalarStrechPositionComponents(size.getX(), size.getY());
		changeVelocity(super.getVelocity());
	}
	
//non-private access or mutators
	public void addShip(Ship ship){
		shipList.add(ship);
	}
	
	public void addShips(Collection<Ship> c){
		shipList.addAll(c);
	}
	
	public List<Ship> getShipList(){
		return shipList;
	}
	
	public Ship getShip(int i){
		return shipList.get(i);
	}
	
	public int size(){
		return shipList.size();
	}
	
	@Override
	public Color getColor(){
		return super.starColor;
	}
	
	public Direction getOVelocity(){ return super.oVelocity; }
	
	public Direction getMoveTarget(){ return this.moveTarget; }
	
	public boolean isOccupying(){ return occupying; }
	
	//returns the actualobject that this fleet is moving towards
	public Asset getActualMoveTarget(){ return this.actualMoveTarget; }

	@Override
	public Path2D getPath(){
		Direction offset = DrawPanel.getOffset();
		Path2D path = new Path2D.Double();
		Direction o = getPosition();
		List<Direction> shipPath = positionComponents2;
		path.moveTo(shipPath.get(0).getX()+o.getX()+offset.getX(), shipPath.get(0).getY()+o.getY() + offset.getY());
		for(int i = 1; i<shipPath.size(); i++){
			//if(i%2 == 0)
				path.lineTo(shipPath.get(i).getX()+o.getX() + offset.getX(), shipPath.get(i).getY()+o.getY()+offset.getY());
		}
		path.lineTo(shipPath.get(0).getX()+o.getX()+offset.getX(), shipPath.get(0).getY()+o.getY()+offset.getY());
		
		
		return path;
	}
	
	public void setOccupying(boolean occ){
		occupying = occ;
	}
	
	@Override
	public void changeVelocity(Direction newVel){
		super.velocity = newVel;
		rotateTowards(super.getVelocity());
	}
	
	@Override
	public String toString(){
		return "Fleet: " + super.name;
	}
	
	public void setVelocity(Direction vel){
		super.velocity = vel;
	}
	
	public void setActualMoveTarget(Asset s){
		this.actualMoveTarget = s;
	}
	
	public void setPosition(Direction d){
		this.position = d;
	}
	
	public void setMoveTarget(Direction mt){
		moveTarget = mt;
		double targetX, targetY;
		targetX = mt.getX() - position.getX();	//temp_ship.getPosition().getX();
		targetY = mt.getY() - position.getY();	//temp_ship.getPosition().getY();
		
		//System.out.println("Position of: " + temp_ship);
		//determines the quadrant to move towards I-IV
		/*if(target.getX() >= temp_ship.getPosition().getX())
			xco = +1;
		else
			xco = -1;
		if(target.getY() >= temp_ship.getPosition().getY())
			yco = +1;
		else
			yco = -1; */
		
		Direction actualTarget = new Direction(targetX, targetY);
		Direction currentVelocity = getVelocity();
		
		//System.out.println("Vel 1: " + currentVelocity);
		double scalar = (currentVelocity.dotProduct(actualTarget) + 0.0) / (actualTarget.getMagnitude());
		Direction targetVelocity = new Direction(scalar * actualTarget.getX(), scalar * actualTarget.getY());
		targetVelocity = new Direction(targetVelocity.getX() / targetVelocity.getMagnitude(), targetVelocity.getY() / targetVelocity.getMagnitude());
		targetVelocity = new Direction(targetVelocity.getX() * currentVelocity.getMagnitude(), targetVelocity.getY() * currentVelocity.getMagnitude());
		//System.out.println("Vel 2: " + targetVelocity);
		
		//fixes changes of over 180 degrees
		if(mt.getX() >= position.getX() && targetVelocity.getX() < 0)
			targetVelocity = new Direction(targetVelocity.getX()*-1, targetVelocity.getY());
		if(mt.getX() <= position.getX() && targetVelocity.getX() > 0)
			targetVelocity = new Direction(targetVelocity.getX()*-1, targetVelocity.getY());
		if(mt.getY() >= position.getY() && targetVelocity.getY() < 0)
			targetVelocity = new Direction(targetVelocity.getX(), targetVelocity.getY()*-1);
		if(mt.getY() <= position.getY() && targetVelocity.getY() > 0)
			targetVelocity = new Direction(targetVelocity.getX(), targetVelocity.getY()*-1);
		
		changeVelocity(targetVelocity);
	}
	
//private mutators
	private void rotateTowards(Direction targetLocation){
		
		System.err.println("Rotating here: " + targetLocation);
		double deltaX = targetLocation.getX(), deltaY = 0-targetLocation.getY();
		double angle = Math.atan2(deltaY, deltaX); //i always forget the negative for the ydelta of atan
		ArrayList<Direction> temp_list = new ArrayList<>();
		//apply rotation angle to all points
		for(int i = 0; i<positionComponents.size(); i++){
			Direction temp_direction = positionComponents.get(i);
			double newXCord = (	(temp_direction.getX() * Math.sin(angle)) - (temp_direction.getY() * Math.cos(angle)));
			double newYCord = (	(temp_direction.getX() * Math.cos(angle)) + (temp_direction.getY() * Math.sin(angle)));
			temp_list.add(new Direction(newXCord, newYCord));
		} changePositionComponents2(temp_list);
	}
	
	public void scalarStrechPositionComponents(double h, double w){
		ArrayList<Direction> temp_list = new ArrayList<>();
		temp_list.add(new Direction(positionComponents.get(0).getX(), positionComponents.get(0).getY() * h));
		temp_list.add(new Direction(positionComponents.get(1).getX() * w, positionComponents.get(1).getY()));
		temp_list.add(new Direction(positionComponents.get(2).getX(), positionComponents.get(2).getY() * h));
		temp_list.add(new Direction(positionComponents.get(3).getX() * w, positionComponents.get(3).getY() * h));
		
		positionComponents = temp_list;
		temp_list = null;
	}
	
	private void changePositionComponents2(List<Direction> list){
		positionComponents2 = list;
	}
	
	private void setupPositionComponents(){
		positionComponents = new ArrayList<Direction>();
		positionComponents.add(new Direction(0, 1));
		positionComponents.add(new Direction(-.5, 0));
		positionComponents.add(new Direction(0, -1));
		positionComponents.add(new Direction(.5, 0));
		changePositionComponents2(positionComponents);
	}
}
