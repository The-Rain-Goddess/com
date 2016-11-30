package com.rain.game.Alacrity.ships;

import com.rain.game.Alacrity.battle.Direction;
import com.rain.game.Alacrity.battle.Ship;

public class Destroyer extends Ship {
	private final String SHIP_NAME;
	private final String SHIP_TYPE = "DESTROYER";
	//combat stats
	private final double TOTAL_HP, TOTAL_SHIELD, FIRE_POWER, HEIGHT, WIDTH;
	private double currentHp, sightRange, combatRange;
	
//public constructor
	public Destroyer(String name, Direction pos, Direction vel, Direction acc){
		super(name,pos, vel, acc);
		
		//logistics
		this.SHIP_NAME = name;
		this.HEIGHT = 20;
		this.WIDTH = 10;
		SHIP_NAME.toUpperCase();
		SHIP_TYPE.toUpperCase();
		
		//combat stats
		this.FIRE_POWER = 30.0/100;
		this.TOTAL_HP = 100.0;
		this.TOTAL_SHIELD = 100.0;
		this.currentHp = TOTAL_HP;
		this.sightRange = 150;
		this.combatRange = 100;
		
		super.scalarStrechPositionComponents(HEIGHT, WIDTH);
		super.changeVelocity(super.getVelocity());
	}

//non-private access and mutators
	//mutators
	@Override
	public void setCurrentHp(double hp) {
		currentHp = hp;
	}
	
	//access
	@Override
	public double getCurrentHp(){ return currentHp;}
	
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
