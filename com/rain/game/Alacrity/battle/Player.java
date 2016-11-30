package com.rain.game.Alacrity.battle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rain.game.Alacrity.ships.Destroyer;
import com.rain.game.Alacrity.ships.Frigate;

public class Player {
	private final String name;
	private List<Ship> ships = Collections.synchronizedList(new ArrayList<Ship>());
	private List<Ship> visible_enemy = Collections.synchronizedList(new ArrayList<Ship>());
	
	//command variables
	private Direction selectedTarget;
	
	private int shipsLost;
	private int shipsKilled;
//public constructor	
	public Player(String name){
		this.name = name;
		addAFleet();
		//addShip(1);
	}
	
	public Player(String name, boolean robot){
		this.name = name;
		addShip(16);
	}
	
//non-private access and mutators
	//access
	public String getName(){ return name;}
	
	public List<Ship> getShips(){ return ships; }
	
	public List<Ship> getVisibleEnemies(){ return visible_enemy; }
	
	public int getShipsLost(){return shipsLost; }
	
	public int getShipsKilled(){ return shipsKilled; }
	
	//mutators
	
	public void destroyShip(Ship lostShip){
		ships.remove(lostShip);
	}
	
	//main logic happens here
	public void update(){
		String currTime = String.format("%.3f", ((Time.getTime() - Game.getTime() + 0.0) / 1000));
		System.err.println("Time: " + currTime + ", updating " + name);
		
		//TODO: update ship position/ vel/accel
		updateShipPosition();
		
		//TODO: update ship ccombat status
	}
	
	public void setVisibleEnemies(List<Ship> visible){
		visible_enemy = visible;
	}
	
	public void resetVisibleEnemies(){
		visible_enemy.clear();
	}
	
	/**
	 * when invoked, changes all of the velocities of the fleet by the arg0
	 */
	public void accelerateShips(double delta){
		for(int i = 0; i<ships.size(); i++){
			Direction newVelocity = new Direction(	ships.get(i).getVelocity().getX()*delta,
													ships.get(i).getVelocity().getY()*delta);
			ships.get(i).changeVelocity(newVelocity);
		}
	}
	
	/**
	 * when invoked, changes velocity of all selected ships
	 * by default: all ships are selected
	 * changes velocity to a proj. of current velocity in direction of target.
	 * @param target
	 */
	public void setMoveTarget(Direction target){
		//target = new Direction(target.getX() - GamePanel.getOffset().getX(), target.getY() - GamePanel.getOffset().getY());
		//TODO: FIX ANGLE TO, AND MOVE TO TARGET
		selectedTarget =  target;//new Direction(target.getX() - GamePanel.getOffset().getX(), target.getY() - GamePanel.getOffset().getY());
		Direction fleet = findFleetCenter(getShips());
		double fleetX = fleet.getX();
		double fleetY = fleet.getY();
		for(int i = 0; i<ships.size(); i++){
			Ship temp_ship = ships.get(i);
			double targetX, targetY; //xco = 0, yco = 0;
	
			targetX = selectedTarget.getX() - fleetX;	//temp_ship.getPosition().getX();
			targetY = selectedTarget.getY() - fleetY;	//temp_ship.getPosition().getY();
			
			System.out.println("Position of: " + temp_ship);
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
			Direction currentVelocity = temp_ship.getVelocity();
			
			System.out.println("Vel 1: " + i + " " + currentVelocity);
			double scalar = (currentVelocity.dotProduct(actualTarget) + 0.0) / (actualTarget.getMagnitude());
			Direction targetVelocity = new Direction(scalar * actualTarget.getX(), scalar * actualTarget.getY());
			targetVelocity = new Direction(targetVelocity.getX() / targetVelocity.getMagnitude(), targetVelocity.getY() / targetVelocity.getMagnitude());
			targetVelocity = new Direction(targetVelocity.getX() * currentVelocity.getMagnitude(), targetVelocity.getY() * currentVelocity.getMagnitude());
			System.out.println("Vel 2: " + i + " " + targetVelocity);
			
			//fixes changes of over 180 degrees
			if(selectedTarget.getX() >= temp_ship.getPosition().getX() && targetVelocity.getX() < 0)
				targetVelocity = new Direction(targetVelocity.getX()*-1, targetVelocity.getY());
			if(selectedTarget.getX() <= temp_ship.getPosition().getX() && targetVelocity.getX() > 0)
				targetVelocity = new Direction(targetVelocity.getX()*-1, targetVelocity.getY());
			if(selectedTarget.getY() >= temp_ship.getPosition().getY() && targetVelocity.getY() < 0)
				targetVelocity = new Direction(targetVelocity.getX(), targetVelocity.getY()*-1);
			if(selectedTarget.getY() <= temp_ship.getPosition().getY() && targetVelocity.getY() > 0)
				targetVelocity = new Direction(targetVelocity.getX(), targetVelocity.getY()*-1);
			
			ships.get(i).changeVelocity(targetVelocity);
			//co=1;
		}
	}
	
	public List<Ship> getSelectedShips(){
		List<Ship> select = new ArrayList<>();
		for(int i = 0; i<ships.size(); i++){
			if(ships.get(i).getSelected())
				select.add(ships.get(i));
		}
		return select;
	}
	
	/**
	 * to be used when certain ships are selected in a collection
	 */
	public void setMoveTarget(Direction target, List<Ship> select){
		//TODO: Fix Fleet Fuckers
		selectedTarget = target;
		Direction fleet = findFleetCenter(select);
		double fleetX = fleet.getX();
		double fleetY = fleet.getY();
		for(int i = 0; i<select.size(); i++){
			if(select.get(i).getSelected()){
				Ship temp_ship = select.get(i);
				double targetX, targetY; //xco = 0, yco = 0;
		
				targetX = selectedTarget.getX() - fleetX;
				targetY = selectedTarget.getY() - fleetY;
				
				System.out.println("Position of: " + temp_ship + " at: " + temp_ship.getPosition());
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
				Direction currentVelocity = temp_ship.getVelocity();
				
				//System.out.println("Vel 1: " + i + " " + currentVelocity);
					double scalar = (currentVelocity.dotProduct(actualTarget) + 0.0) / (actualTarget.getMagnitude());
					Direction targetVelocity = new Direction(scalar * actualTarget.getX(), scalar * actualTarget.getY());
					targetVelocity = new Direction(targetVelocity.getX() / targetVelocity.getMagnitude(), targetVelocity.getY() / targetVelocity.getMagnitude());
					targetVelocity = new Direction(targetVelocity.getX() * currentVelocity.getMagnitude(), targetVelocity.getY() * currentVelocity.getMagnitude());
				//System.out.println("Vel 2: " + i + " " + targetVelocity);
				
				//fixes changes of over 180 degrees
				if(target.getX() >= fleetX && targetVelocity.getX() < 0)
					targetVelocity = new Direction(targetVelocity.getX()*-1, targetVelocity.getY());
				if(target.getX() <= fleetX && targetVelocity.getX() > 0)
					targetVelocity = new Direction(targetVelocity.getX()*-1, targetVelocity.getY());
				if(target.getY() >= fleetY && targetVelocity.getY() < 0)
					targetVelocity = new Direction(targetVelocity.getX(), targetVelocity.getY()*-1);
				if(target.getY() <= fleetY && targetVelocity.getY() > 0)
					targetVelocity = new Direction(targetVelocity.getX(), targetVelocity.getY()*-1);
				
				select.get(i).changeVelocity(targetVelocity);
				//co=1;
			}
		}
	}
	
	public void unSelectAll(){
		for(int i = 0; i < ships.size(); i++){
			ships.get(i).setSelected(false);
		}
	}
	
	public void addShip(){
		ships.add(new Frigate("Frigate_" + String.format("%.3f", Math.random()), new Direction(100 + Math.random()*100, 100 + Math.random() * 100), new Direction(Math.random(), Math.random()), new Direction(0,0)));
		ships.add(new Destroyer("Destroyer_" + String.format("%.3f", Math.random()), new Direction(100 + Math.random()*100, 100 + Math.random() * 100), new Direction(Math.random(), Math.random()), new Direction(0,0)));
	}
	
	public void addShip(int n){
		for(int i = 0; i<n; i++){
			ships.add(new Frigate("Frigate_" + String.format("%.3f", Math.random()), new Direction(400 + Math.random()*100, 400 + Math.random() * 100), new Direction(Math.random(), Math.random()), new Direction(0,0)));
		}
	}
	
	public void addAFleet(){
		for(int i = 0; i<5; i++){
			//ships.add(new Frigate("Frigate_" + String.format("%.3f", Math.random()), new Direction(100 + Math.random()*100, 100 + Math.random() * 100), new Direction(Math.random()+1, Math.random()+1), new Direction(0,0)));
			ships.add(new Destroyer("Destroyer_" + String.format("%.3f", Math.random()), new Direction(100 + Math.random()*100, 100 + Math.random() * 100), new Direction(Math.random()+1, Math.random()+1), new Direction(0,0)));
		} //ships.get(0).setCurrentHp(50);
		//mid-class ships
		//ships.add(new Cruiser("Cruiser_" + String.format("%.3f", Math.random()), new Direction(100 + Math.random()*100, 100 + Math.random() * 100), new Direction(Math.random()+1, Math.random()+1), new Direction(0,0)));
		//ships.add(new Battleship("Battleship_" + String.format("%.3f", Math.random()), new Direction(100 + Math.random()*100, 100 + Math.random() * 100), new Direction(Math.random()+1, Math.random()+1), new Direction(0,0)));
	}

//private mutators
	private Direction findFleetCenter(List<Ship> select){
		Direction fleet;
		double xtot = 0, ytot = 0, size = select.size();
		for(int i = 0; i<size; i++){
			xtot+=select.get(i).getPosition().getX();
			ytot+=select.get(i).getPosition().getY();
		}
		
		fleet = new Direction(xtot / size, ytot / size);
		return fleet;
	}
	
	private void updateShipPosition(){
		for(int i = 0; i<ships.size(); i++){
			//System.out.println(ships.get(i));
			ships.get(i).changePositionByVelocity();
		}
	}
}