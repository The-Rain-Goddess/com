package com.rain.game.Alacrity.ships;

import com.rain.game.Alacrity.battle.Direction;
import com.rain.game.Alacrity.battle.Ship;

public class Frigate extends Ship {
	//logisticss
	private final String SHIP_NAME;
	private final String SHIP_TYPE = "FRIGATE";
	//combat stats
	private final double TOTAL_HP, TOTAL_SHIELD, FIRE_POWER;
	private final double HEIGHT, WIDTH;
	private double currentHp, sightRange, combatRange;
	
//public constructor
	public Frigate(){
		super();
		
		//logistics
		this.HEIGHT = 10;
		this.WIDTH = 5;
		this.SHIP_NAME = "Ghost_Ship";
		
		//combat stats
		this.FIRE_POWER = 10.0/100;
		this.TOTAL_HP = 100.0;
		this.TOTAL_SHIELD = 25.0;
		this.currentHp = TOTAL_HP;
		this.sightRange = 200;
		this.combatRange = 100;
	}
	
	public Frigate(String name, Direction pos, Direction vel, Direction acc){
		super(name,pos, vel, acc);
		
		//logistics
		this.HEIGHT = 10;
		this.WIDTH = 5;
		this.SHIP_NAME = name;
		
		//combat stats
		this.FIRE_POWER = 10.0/100;
		this.TOTAL_HP = 100.0;
		this.TOTAL_SHIELD = 25.0;
		this.currentHp = TOTAL_HP;
		this.sightRange = 200;
		this.combatRange = 100;
		
		
		super.scalarStrechPositionComponents(HEIGHT, WIDTH);
		super.changeVelocity(super.getVelocity());
	}
	
//non-private access and mutators
	//mutators
	@Override
	public void setCurrentHp(double hp){
		currentHp = hp;
	}
	
	//access	
	@Override
	public double getCombatRange(){ return combatRange; } 
	
	@Override
	public String getName(){ return SHIP_NAME; }
	
	@Override
	public String getType(){ return SHIP_TYPE; }
	
	@Override
	public double getCurrentHp(){ return currentHp; }
	
	@Override
	public double getSightRange(){ return sightRange; }
	
	@Override
	public double getTotalHp(){ return TOTAL_HP; }
	
	@Override
	public double getTotalShield(){ return TOTAL_SHIELD; }
		
	@Override
	public double getFirePower(){ return FIRE_POWER; }
	
//private access and mutators
}
