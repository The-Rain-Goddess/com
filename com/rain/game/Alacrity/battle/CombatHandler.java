package com.rain.game.Alacrity.battle;

import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * 
 * @author The Rain Goddess
 *	Thread Class to handle combat, also starts the VisionHandler
 */
public class CombatHandler implements Runnable {
	private long combatTickOffset = 10L;
	
//public constructor	
	public CombatHandler(VisionHandler visionHandler) {
		// TODO Auto-generated constructor stub
		Thread vision = new  Thread(visionHandler);
		vision.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try{
			List<Player> playerList = Game.getPlayers();
			List<Ship> playerShips;
			
			while(Alpha.gameRunning){
				Thread.sleep(combatTickOffset);
				
				//iterates through players
				for(int i = 0; i<playerList.size(); i++){
					playerShips = playerList.get(i).getShips();
					
					//itereates through the players' ships
					for(int j = 0; j<playerShips.size(); j++){
						Ship ship = playerShips.get(j);
						
						//iterates through other players
						for(int k = 0; k< playerList.size(); k++){
							if(k!=i){
								List<Ship> enemyShips = playerList.get(k).getShips();
								int count = 0;
								
								//iterates through other player's shipLists to find enemies in combat range
								for(int l = 0; l<enemyShips.size(); l++){
									Ship enemyShip = enemyShips.get(l);
									double range = Game.findRangeBetween(ship, enemyShip);
									if(range<=ship.getCombatRange()){
										Ship targetShip = ship.getCombatTarget();
										if(targetShip==null){
											ship.setCombatTarget(enemyShip);
											ship.setInCombat(true);
										} else if(!targetShip.stillAlive()){
											ship.setCombatTarget(null);
											ship.setInCombat(false);
										} else{
											targetShip.setCurrentHp(targetShip.getCurrentHp()-ship.getFirePower());
										}
									} else{
										count++;
									}
								} if(count==enemyShips.size()){
									ship.setCombatTarget(null);
									ship.setInCombat(false);
								}
							}
						}
					}
					for(int t = 0; t<playerShips.size(); t++){
						Ship ship = playerShips.get(t);
						if(ship.getCurrentHp()<=0){
							ship.setAlive(false);
							Game.log(ship.toString() + " was destroyed.\n");
							playerList.get(i).destroyShip(ship);
						}
							
					}
				}
			}
		} catch(ConcurrentModificationException | InterruptedException e){
			e.printStackTrace();
		}
	}
}
