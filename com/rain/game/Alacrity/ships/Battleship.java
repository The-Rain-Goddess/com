package com.rain.game.Alacrity.ships;

import com.rain.game.Alacrity.battle.Direction;
import com.rain.game.Alacrity.battle.Ship;

public class Battleship extends Ship {
	private final String SHIP_NAME;
	private final String SHIP_TYPE = "BATTLESHIP";
	//combat stats
	private final double TOTAL_HP, TOTAL_SHIELD, FIRE_POWER;
	private final double HEIGHT, WIDTH;
	private double currentHp, sightRange, combatRange;
	
//public constructor
	public Battleship(String name, Direction pos, Direction vel, Direction acc){
		super(name,pos, vel, acc);
		
		//logistics
		this.SHIP_NAME = name;
		this.HEIGHT = 40;
		this.WIDTH = 20;
		SHIP_NAME.toUpperCase();
		SHIP_TYPE.toUpperCase();
		
		//combat stats
		this.FIRE_POWER = 1000.0/100;
		this.TOTAL_HP = 5000.0;
		this.TOTAL_SHIELD = 500.0;
		this.currentHp = TOTAL_HP;
		this.sightRange = 100;
		this.combatRange = 50;
		
		super.scalarStrechPositionComponents(HEIGHT, WIDTH);
		super.changeVelocity(super.getVelocity());
	}
	
//non-private access and mutators
	@Override
	public double getCurrentHp(){
		return currentHp;
	}
	
	@Override
	public void setCurrentHp(double hp) {
		currentHp = hp;
	}
	
	//access
	@Override
	public double getCombatRange(){ return combatRange; } 
	
	@Override
	public double getSightRange(){ return sightRange; }
	
	@Override
	public double getTotalHp(){ return TOTAL_HP; }
	
	@Override
	public double getTotalShield(){ return TOTAL_SHIELD; }
		
	@Override
	public double getFirePower(){ return FIRE_POWER; }
}
