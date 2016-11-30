package com.rain.game.Alacrity.battle;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class Ship {
	
//ship variables
	private final Direction FINAL_VELOCITY;
	private Direction velocity;
	private Direction acceleration;
	private Direction position; //center of ship
	private List<Direction> positionComponents;
	private List<Direction> positionComponents2;
	
	private final String SHIP_NAME;
	private final String SHIP_TYPE;
	@SuppressWarnings("unused")
	private final double HEIGHT, WIDTH;
	//combat stats
	private double TOTAL_HP, TOTAL_SHIELD, FIRE_POWER;
	private double currentHp, sightRange;
	private boolean visible = false, selected = true, inCombat = false, alive = true;
	
	//combat target
	private Ship targetShip;
	
//public constructor
	public Ship(){
		FINAL_VELOCITY = null;
		SHIP_NAME = null;
		SHIP_TYPE = null;
		TOTAL_HP = 100;
		TOTAL_SHIELD = 0;
		FIRE_POWER = 0;
		HEIGHT = WIDTH = 0;
		inCombat = false;
	}
	
	public Ship(String name, Direction pos, Direction vel, Direction acc){
		position = pos;
		velocity = vel;
		acceleration = acc;
		
		FINAL_VELOCITY = vel;
		
		SHIP_NAME = name;
		SHIP_TYPE = null;
		TOTAL_HP = 0;
		TOTAL_SHIELD = 0;
		FIRE_POWER = 0;
		
		HEIGHT = 0;
		WIDTH = 0;
		
		//sets up inital position and direction
		setupPositionComponents();
		//changePositionComponents2(positionComponents);
		
	}
	
//non-private access and mutators
	public void changePositionByVelocity(){
		position = new Direction(
					position.getX() + velocity.getX(),
					position.getY() + velocity.getY());
	}
	
	public void changeVelocityByAcceleration(){
		velocity = new Direction(
					velocity.getX() + acceleration.getX(),
					velocity.getY() + acceleration.getY());
	}
	
	//this is for while acceleration isn't added yet
	public void changeVelocity(Direction newVelocity){
		//System.err.println("Velocity changing here " + newVelocity);
		velocity = newVelocity;
		rotateTowards(velocity);
		//changePositionComponents(getPositionComponents2());
	}
	
	//used to scale the ship up to its proper size
	public void scalarStrechPositionComponents(double h, double w){
		ArrayList<Direction> temp_list = new ArrayList<>();
		temp_list.add(new Direction(positionComponents.get(0).getX(), positionComponents.get(0).getY() * h));
		temp_list.add(new Direction(positionComponents.get(1).getX() * w, positionComponents.get(1).getY()));
		temp_list.add(new Direction(positionComponents.get(2).getX(), positionComponents.get(2).getY() * h));
		temp_list.add(new Direction(positionComponents.get(3).getX() * w, positionComponents.get(3).getY() * h));
		
		positionComponents = temp_list;
		temp_list = null;
	}
	
	public void setCurrentHp(double hp) {
		currentHp = hp;
	}
	
	public void setSightRange(double newRange){
		sightRange = newRange;
	}
	
	//visible to enemy
	public void setVisible(boolean spotted){
		visible = spotted;
	}
	
	//in combat or not
	public void setInCombat(boolean inCombat){
		this.inCombat = inCombat;
	}
	
	public void setAlive(boolean stillAlive){
		this.alive = stillAlive;
	}
	
	//sets who this ship is attacking
	public void setCombatTarget(Ship target){
		if(target!=null)
			this.targetShip = target;
		else
			this.targetShip = null;
	}
	
	public void setSelected(boolean select){
		selected = select;
	}
	
	//accessors
	public boolean getVisible(){ return visible; }
	
	public Path2D getPath(){
		Path2D path = new Path2D.Double();
		Direction o = getPosition();
		List<Direction> shipPath = getPositionComponents2();
		path.moveTo(shipPath.get(0).getX()+o.getX(), shipPath.get(0).getY()+o.getY());
		for(int i = 1; i<shipPath.size(); i++){
			//if(i%2 == 0)
			path.lineTo(shipPath.get(i).getX()+o.getX(), shipPath.get(i).getY()+o.getY());
		}
		path.lineTo(shipPath.get(0).getX()+o.getX(), shipPath.get(0).getY()+o.getY());
		
		
		return path;
	}
	
	public Path2D getPath(Direction offset){
		Path2D path = new Path2D.Double();
		Direction o = getPosition();
		List<Direction> shipPath = getPositionComponents2();
		path.moveTo(shipPath.get(0).getX()+o.getX()+offset.getX(), shipPath.get(0).getY()+o.getY() + offset.getY());
		for(int i = 1; i<shipPath.size(); i++){
			//if(i%2 == 0)
			path.lineTo(shipPath.get(i).getX()+o.getX() + offset.getX(), shipPath.get(i).getY()+o.getY()+offset.getY());
		}
		path.lineTo(shipPath.get(0).getX()+o.getX()+offset.getX(), shipPath.get(0).getY()+o.getY()+offset.getY());
		
		
		return path;
	}
	
	public boolean stillAlive(){ return alive; }
	
	//returns whether the ship is in combat(true) or not(false)
	public boolean getSelected(){ return selected; }
	
	public boolean getInCombat(){ return inCombat; }
	
	public Ship getCombatTarget(){ return targetShip; }
	
	public double getCombatRange(){return 0.0;}
	
	public double getSightRange(){ return sightRange; }
	
	public double getCurrentHp(){ return currentHp; }
	
	public Direction getFinalVelocity(){ return FINAL_VELOCITY; }
	
	public Direction getPosition(){ return position; }
	
	public Direction getVelocity(){ return velocity; }
	
	public Direction getAcceleration(){ return acceleration; }
	
	public String getName(){return SHIP_NAME; }
	
	public String getType(){ return SHIP_TYPE; }
	
	public double getTotalHp(){ return TOTAL_HP; }
	
	public double getTotalShield(){ return TOTAL_SHIELD; }
	
	public double getFirePower(){ return FIRE_POWER; }
	
	public List<Direction> getPositionComponents(){ return positionComponents; }
	
	public List<Direction> getPositionComponents2(){ return positionComponents2; }
	
	@Override
	public String toString(){
		return "Name: " + getName() + ", position: " + getPosition() + ", velocity: " + getVelocity();
	}
	
//private access and mutators	
	//@SuppressWarnings("unused")
	private void rotateTowards(Direction targetLocation){
		//TODO: apply rotation to position Components consistantly, has to do with which list i use to update and etc
		
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
	
	private void changePositionComponents2(List<Direction> list){
		positionComponents2 = list;
	}
	
	@SuppressWarnings("unused")
	private void changePositionComponents(List<Direction> list){
		positionComponents = list;
	}
	
	private void setupPositionComponents(){
		positionComponents = new ArrayList<Direction>();
		positionComponents.add(new Direction(0, 1));
		positionComponents.add(new Direction(-.5, 0));
		positionComponents.add(new Direction(0, -1));
		positionComponents.add(new Direction(.5, 0));
	}
}
