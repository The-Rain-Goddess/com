/**
 * 
 */
package com.rain.game.Alacrity.main;

import java.awt.Color;

import com.rain.game.Alacrity.assets.Fleet;
import com.rain.game.Alacrity.assets.Star;
import com.rain.game.Alacrity.battle.Direction;

/**
 * @author Black Lotus
 *
 */
public class Asset {
	protected String name;
	protected Direction position;
	protected Direction oPosition, oVelocity;
	protected Direction velocity;
	protected Direction acceleration;
	protected Direction size;
	protected Direction approachTarget;
	protected Color starColor;
	//protected List<Direction> positionComponents;
	protected Star sun;
	protected Fleet occupyingFleet;
	protected long population = 0, oPopulation=0;
	protected double income = 0;
	protected int type; /*
						* 1 = terran w/ water
						* 2 = terran w/ iron
						* 3 = gas w/ sulphur
						* 4 = asteroid 
						* 5 = yellow star
						*/
	
	protected double r; // radius from body to its sun
	protected boolean selected, occupied = false, celestialBody = true, approaching = false;
	
	protected double mag = .05; //magnitude of velocity
	
	protected int ownedBy = 0; /*
								* 0 = noone
								* 1 = player
								* 2 = computer
								*/
	
//public constructor	
	public Asset(){
		
	}
	
	public Asset(String name, Direction pos, Color col){
		this.name = name;
		this.position = pos;
		this.oPosition = pos;
		this.starColor = col;
		this.size = new Direction(50,50);
	}
	
	//used for fleets
	public Asset(String name, Direction pos, Direction vel, Direction acc, Color col){
		this.name = name;
		this.position = pos;
		this.oPosition = pos;
		this.velocity = vel;
		this.oVelocity = vel;
		this.acceleration = acc;
		this.starColor = col;
	}
	
	//stars
	public Asset(String name, Direction pos, Color col, Direction size, int t){
		this.name = name;
		this.position = pos;
		this.oPosition = pos;
		this.starColor = col;
		this.size = size;
		this.type = t;
		//this.velocity = new Direction(0.0,0.0);
	}
	
	//used for planets
	public Asset(String name, Direction pos, Color col, Direction size, Star sun){
		this.name = name;
		this.position = pos;
		this.oPosition = pos;
		this.starColor = col;
		this.size = size;
		this.sun = sun;
		this.r = getDistanceBetween(sun);
		Direction vel = new Direction(	0-mag*Math.sin(mag/r), mag*Math.cos(mag/r));
		Direction acc = new Direction(	0-Math.pow(mag, 2)/r * Math.cos(mag*((double)Genesis.getTime()/1000)/r), 0-Math.pow(mag, 2)/r * Math.sin(mag*((double)Genesis.getTime()/1000)/r));
		velocity = vel;
		acceleration = acc;
	}
	
	//used for planets
		public Asset(String name, Direction pos, Color col, Direction size, Star sun, int t){
			this.name = name;
			this.position = pos;
			this.oPosition = pos;
			this.starColor = col;
			this.size = size;
			this.sun = sun;
			this.type = t;
			this.r = getDistanceBetween(sun);
			Direction vel = new Direction(	0-mag*Math.sin(mag/r), mag*Math.cos(mag/r));
			Direction acc = new Direction(	0-Math.pow(mag, 2)/r * Math.cos(mag*((double)Genesis.getTime()/1000)/r), 0-Math.pow(mag, 2)/r * Math.sin(mag*((double)Genesis.getTime()/1000)/r));
			velocity = vel;
			acceleration = acc;
		}
	
//private mutators
	private double getDistanceBetween(Asset other){
		double distance = Math.pow(Math.pow(position.getX()+size.getX()/2 - (other.position.getX() + other.getSize().getX()/2), 2) + Math.pow(position.getY()+size.getY()/2-(other.getPosition().getY() + other.getSize().getY()/2), 2), .5);
		return distance;
	}
	
//non-private access and mutators
	public void setApproachTarget(Direction d){
		approachTarget = d;
	}
	
	public void setApproaching(boolean t){
		approaching = t;
	}
	
	public void changePositionByVelocity(){
		if(velocity!=null)
			position = new Direction(	position.getX() + velocity.getX(),
										position.getY() + velocity.getY() );
				
	}
	
	public void changeVelocityByAcceleration(){
		if(acceleration!=null){
					velocity = new Direction( 	velocity.getX() + acceleration.getX(),
												velocity.getY() + acceleration.getY());
		}
	}
	
	public void changeAccelerationByTime(int cycles){
		if(acceleration!=null)
			acceleration = new Direction(	0-Math.pow(mag, 2)/r * Math.cos(mag*cycles/r), 
											0-Math.pow(mag, 2)/r * Math.sin(mag*cycles/r));
	}
	
	public void changeVelocityByTime(int cycles){
		if(velocity!=null){
			velocity = new Direction(	0-  mag*Math.sin(mag*cycles/r), 
											mag*Math.cos(mag*cycles/r));
		}
	}
	
	public void changePositionByTime(int cycles){
		if(this.sun!=null)
			;//r = this.getDistanceBetween(sun);
		if(velocity!=null){
			position = new Direction(	r*Math.cos(mag/r*(cycles+0.0)) + sun.getPosition().getX()+sun.getSize().getX()/2, 
										r*Math.sin(mag/r*(cycles+0.0)) + sun.getPosition().getY()+sun.getSize().getY()/2);
		}
	}
	
	public void changePopulationByTime(int cycles){
		//System.out.println("Population change: " + (Math.exp(.5*cycles/10000)));
		population = (long) (oPopulation * (Math.exp(.02*cycles/10000)));
	}
	
	public Direction getPositionAtTime(double cycles){
			Direction direction = new Direction(	r*Math.cos(mag/r*(cycles+0.0)) + sun.getPosition().getX()+sun.getSize().getX()/2, 
													r*Math.sin(mag/r*(cycles+0.0)) + sun.getPosition().getY()+sun.getSize().getY()/2);
		return direction;
	}
	
	public void changeVelocityMag(double v){
		mag = v;
	}
	
	public void changeVelocity(Direction newVel){
		velocity = newVel;
	}
	
	public void setSelected(boolean selected){
		this.selected = selected;
	}
	
	public void setOccupied(boolean occ){
		this.occupied = occ;
		//this.occupyingFleet = fleet;
	}
	
	public void setOccupyingFleet(Fleet f){
		this.occupyingFleet = f;
	}
	
	public void setIncome(double incrementIncome){
		this.income = incrementIncome;
	}
	
	public void setPopulation(long pop){
		this.population = pop;
	}
	
	public void setInitialPopulation(long pop){
		this.oPopulation = pop;
	}
	
	public void setType(int t){
		this.type = t;
	}

	public void setOwnedBy(int ownedBy) {
		this.ownedBy = ownedBy;
	}
	
	public int getOwnedBy() { return ownedBy;}
	
	public int getType(){ return type; }
	
	public long getPopulation(){ return population; }
	
	public double getIncome(){ return income; }
	
	public double getIncomeOverYear(){ return income*population*10000; }
	
	public double getR(){return this.r;}
	
	public boolean isCelestialBody(){ return celestialBody; }
	
	public boolean getSelected(){ return selected; }
	
	public boolean getOccupied(){ return occupied; }
	
	public boolean getApproaching(){ return approaching; }
	
	public Direction getApproachTarget(){ return approachTarget; }
	
	public Direction getVelocity(){ return velocity; }
	
	public Direction getAcceleration(){ return acceleration; }

	public Direction getPosition() { return position; }
	
	public Direction getSize(){ return size; }
	
	public Star getSun(){ return sun; }
	
	public Fleet getOccupyingFleet(){ return this.occupyingFleet; }
	
	public String getName(){ return name; }

	public Color getColor() { return starColor; }
}
