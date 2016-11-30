package com.rain.game.Alacrity.battle;

import java.util.ArrayList;
import java.util.List;

public class VisionHandler implements Runnable {
	
//public constructor
	public VisionHandler() {
		
	}

	@Override
	public void run() {
		// TODO Lists of visible ships dont work atm, ships themselves know if they have been spotted
		try{
			List<Player> playerList = Game.getPlayers();
			List<Ship> temp_list = new ArrayList<Ship>();
			while(Alpha.getGameRunning()){
				Thread.sleep(100L);
				
				//iterates through the players
				for(int i = 0; i<playerList.size(); i++){
					temp_list.clear();
					List<Ship> shipList = playerList.get(i).getShips();
					playerList.get(i).resetVisibleEnemies(); //makes sure there is no overadding visible ships
					
					//iterates through the players' ships
					for(int j = 0; j<shipList.size(); j++){
						Ship temp_ship = shipList.get(j);
						
						//iterates through other players
						for(int k = 0; k<playerList.size(); k++){
							if(k!=i){
								List<Ship> enemyList = playerList.get(k).getShips();
								int count = 0;
								
								//iterates through other players' shiplist to find ships in range
								for(int l = 0; l< enemyList.size(); l++){
									Ship enemyShip = enemyList.get(l);
									double range = Game.findRangeBetween(temp_ship, enemyShip);
									//System.out.println("Vision: Ship1 is " + range + " from Ship2");
									if(range <= temp_ship.getSightRange()){
										//TODO: add it to sighted lists and set ship boolean to spotted
										if(!temp_list.contains(enemyShip)){
											temp_list.add(enemyShip);
											enemyShip.setVisible(true);
										}
									} else{
										count++;
									}
								} if(count==enemyList.size()){
									temp_ship.setVisible(false);
								}
							}
						}
					}
					
					playerList.get(i).setVisibleEnemies(temp_list);
				}
			}
			
		} catch(InterruptedException e){
			e.printStackTrace();
		} finally{
			
		}

	}
	
//private access  and mutators
	public int fact(int n){
        if(n == 1){
            return 1;
        }
        return n * (fact(n-1));
    }
}
