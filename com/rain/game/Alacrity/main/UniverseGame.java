package com.rain.game.Alacrity.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import com.rain.game.Alacrity.assets.Fleet;
import com.rain.game.Alacrity.assets.Planet;
import com.rain.game.Alacrity.assets.Star;
import com.rain.game.Alacrity.battle.Direction;
import com.rain.game.Alacrity.battle.InputActionListener;
import com.rain.game.Alacrity.gui.DrawPanel;
import com.rain.game.Alacrity.gui.InfoPanel;
import com.rain.game.Alacrity.gui.MainGameFrame;
import com.rain.game.Alacrity.ships.Frigate;

public class UniverseGame implements Runnable {
	private static List<Asset> assets = Collections.synchronizedList(new ArrayList<Asset>());
	private static InputActionListener inputActionListener = new InputActionListener();
	private static MouseMotionHandler mouseInputHandler = new MouseMotionHandler();
	private static MouseActionHandler mouseActionHandler = new MouseActionHandler();
	private static List<Direction> dragList = Collections.synchronizedList(new ArrayList<>());
	//private static List<Direction> fleetMoveTargetList = Collections.synchronizedList(new ArrayList<>());
	
	private final double screenDelta = 5.0;
	private boolean dragged = false;
	
	public UniverseGame(){
		Asset s1 = new Star  ("Andromeda", new Direction(300, 300), Color.YELLOW);
		Asset p1 = new Planet("Earth", new Direction(600, 300), Color.GREEN, new Direction(10, 10), (Star)s1, 1);
		Asset p2 = new Planet("Mars", new Direction(700, 300), Color.RED, new Direction(10, 10), (Star)s1, 2);
		Asset p3 = new Planet("Hailey's Comet", new Direction(1000, 300), Color.GRAY, new Direction(5, 5), (Star)s1, 4);
		Asset p4 = new Planet("Jupiter", new Direction(800, 300), Color.ORANGE, new Direction(15, 15), (Star)s1, 3);
		//s1.setSelected(true);
		Fleet f1 = new Fleet("HomeFleet", new Direction(300,200), new Direction(.2,.2), new Direction(0,0), new Direction(10,10), Color.BLUE);		
		f1.addShip(new Frigate());
		f1.addShip(new Frigate());
		//p2.setOccupied(true);
		//p4.setOccupied(true);
		p1.setInitialPopulation(1_000_000_000);
		p1.setIncome(1); //income per cycle of year
		assets.add(f1);
		assets.add(p2);
		assets.add(p3);
		assets.add(p4);
		assets.add(s1);
		
		assets.add(p1);
		
	}
	
	@Override
	public void run() {
		int cycles = 0;
		//main game logic and run loop here
		try{
			Thread.sleep(1000L);
			while(Genesis.getGameRunning()){
				Thread.sleep(100L/6L);
				
				//System.out.println(assets.get(assets.size()-1).getName() + " population: " + assets.get(assets.size()-1).getPopulation());
			
			//handles all keyboard events
				if(inputActionListener.hasInput()){	
					String temp = inputActionListener.getInput() + "\n";
					log(temp);
					System.out.println("logged");
					//if(temp.substring(temp.length()-2, temp.length()-1).equals("\n"))
						temp = temp.replaceAll("\\\\n", "");
					switch(temp.charAt(0)){
						case 'a': break;
						case 'd': break;
						case 's': break;
						case 'q': break;
						case 'r': DrawPanel.setOffset(new Direction(0.0,0.0));break;
						case 'x': DrawPanel.incrementScalar(.1); break;
						case 'z': DrawPanel.incrementScalar(-.1); break;
						case 'c': DrawPanel.setScalar(1.0); break;
						case 'l': String[] comm = temp.split(" "); if(comm.length>1)speedPlanets(Double.parseDouble(comm[1])); break;
						case 't': String[] comm2 = temp.split(" "); if(comm2.length>1)cycles+=(int)Double.parseDouble(comm2[1]); break;
					}
				}
				
			//handles all click events
				if(mouseActionHandler.hasClick()){
					System.out.println(mouseActionHandler.getPoint());
					System.out.println("Off: " + DrawPanel.getOffset() + "\n" + DrawPanel.getScalar());
					checkForAssetsInRange(mouseActionHandler.getPoint());
					
				}
				
			//handles all drag events
				if(mouseActionHandler.isDrag()){
					dragged = true;
					dragList.add(mouseInputHandler.getRealPoint());
					if(dragList.size()==1)
						checkForAssetsInRange(dragList.get(0));
					if(assetIsSelected()){
						Asset asset = getAssetSelected();
						Direction n = new Direction(asset.getPosition().getX()+ asset.getSize().getX()/2, asset.getPosition().getY() + asset.getSize().getY()/2);
						if(dragList.get(0)!=n)
							dragList.add(0, n);
					}
					if(dragList.size()>=5)
						dragList.remove(4);
			
				} else if(dragged){ //all of the drag points are in the draglist
					checkForAssetsInRange(dragList.get(0));
					
					if((this.assetIsSelected() && !this.getAssetSelected().isCelestialBody())){
						Fleet f2 = (Fleet) (this.getAssetSelected());
						checkForAssetsInRange(dragList.get(dragList.size()-1));
						if(f2.getApproaching())
							if(f2.getActualMoveTarget()!=null)
								f2.getActualMoveTarget().setApproaching(false);
						
						if(f2.getVelocity().getMagnitude()==0)
							f2.setVelocity(f2.getOVelocity());
						
						//if moving fleet to a orbital body, then
						if(this.assetIsSelected() && this.getAssetSelected().celestialBody){
							
							
							f2.setActualMoveTarget(null);
							Asset s1 = this.getAssetSelected();
							double time = this.findDistanceBetween(f2, s1) / f2.getVelocity().getMagnitude();
							System.out.println("time: " + time);
							Direction d;
							if(s1.getVelocity()!=null){
								d = s1.getPositionAtTime(time+cycles);
								s1.setApproaching(true);
								s1.setApproachTarget(d);
								f2.setActualMoveTarget(s1); //sets the target asset that the fleet is movign to
							} else
								d = new Direction(s1.getPosition().getX(),s1.getPosition().getY());
							
							Direction intersection = d;
							
							f2.setMoveTarget(intersection);
							f2.setApproaching(true);
						} else{
							f2.setMoveTarget(dragList.get(dragList.size()-1));
							f2.setActualMoveTarget(null);
						}		
					} else if((this.assetIsSelected() && this.getAssetSelected().getOccupied())){
						//TODO: LOOK FOR BUGS BELOW HERE
						Asset s = this.getAssetSelected(); //grabs the object at the beginning of the drag
						checkForAssetsInRange(dragList.get(dragList.size()-1)); //looks for a target at the end of the drag
						Fleet f2 = s.getOccupyingFleet();
						f2.setVelocity(f2.getOVelocity());
						f2.setPosition(s.getPosition());
						
						if (this.assetIsSelected() && this.getAssetSelected().celestialBody
								&& this.getAssetSelected() != s) {
							f2.setActualMoveTarget(null);
							Asset s1 = this.getAssetSelected();
							double time = this.findDistanceBetween(f2, s1) / f2.getVelocity().getMagnitude();
							System.out.println("time: " + time);
							Direction d;
							if (s1.getVelocity() != null) {
								d = s1.getPositionAtTime(time + cycles);
								s1.setApproaching(true);
								s1.setApproachTarget(d);
								f2.setActualMoveTarget(s1); // sets the target
															// asset that the
															// fleet is movign
															// to

							} else {
								d = dragList.get(dragList.size() - 1);
								System.err.println("Final:" + d);
							}

							Direction intersection = d;

							f2.setOccupying(false);
							s.setOccupied(false);
							s.setOccupyingFleet(null);
							f2.setMoveTarget(intersection);
							f2.setApproaching(true);
						} else if (this.getAssetSelected() == s) {

						} else {
							Direction d = dragList.get(dragList.size() - 1);
							System.err.println("Final:" + d);

							Direction intersection = d;

							f2.setOccupying(false);
							s.setOccupied(false);
							s.setOccupyingFleet(null);
							s.setApproaching(false);
							f2.setMoveTarget(intersection);
							f2.setActualMoveTarget(null);
							f2.setApproaching(true);
						}
					}
					//use first point and second point to drag the object 
					//at first point towards
					//object at second point
					dragList.clear();
					dragged = false;
				}
				
				
				if(DrawPanel.getScalar() <= .3){
					//System.out.println("We switched modes");
				}
			//update planet positions
				cycles++;
				updateCelestialBodies(cycles);
			//screen-movement
				moveScreen();

			//update clock
				setClock();
				
		//updates info screen										need a better cycle time here
				if(MainGameFrame.getDrawPanel().getComponentCount()>=3 && cycles%100 == 0){
					
					/*Asset s = ((InfoPanel)MainGameFrame.getDrawPanel().getComponent(2)).getAsset();
					MainGameFrame.getDrawPanel().add(setupInfoPanel(s), BorderLayout.EAST);
					MainGameFrame.getDrawPanel().remove(2); */
					((InfoPanel)MainGameFrame.getDrawPanel().getComponent(2)).update();
				}
				
			//check fleets for necessary action 
				checkFleetsForArivalAtMoveTarget();
			}
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
//non-private access	
	public static void log(String logged){
		JTextArea chat = MainGameFrame.getMsgField();
		String time = String.format("%,.3f", ((double)Genesis.getTime() )/ 1000 ) + ": ";
		chat.setText( time + logged + chat.getText());
		Highlighter.HighlightPainter greenPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
		try {
			chat.getHighlighter().addHighlight(0, 6, greenPainter);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public double findDistanceBetween(Asset a, Asset b){
		double distance = Math.pow(Math.pow(a.getPosition().getX()-b.getPosition().getX(), 2) + Math.pow(a.getPosition().getY() - b.getPosition().getY(), 2), .5);
		return distance;
	}
	
	/**
	 * @return the assets
	 */
	public static List<Asset> getAssets() {
		return assets;
	}

	/**
	 * @param drawables the assets to set
	 */
	public static void setAssets(List<Asset> drawables) {
		UniverseGame.assets = drawables;
	}
	
	public static List<Direction> getDragList(){ return dragList; }
	
	public static InputActionListener getInputActionListener(JTextField field){ inputActionListener.setField(field); return inputActionListener; }
	
	public static MouseMotionHandler getMouseHandler(){ return mouseInputHandler; }
	
	public static MouseActionHandler getMouseActionHandler(){ return mouseActionHandler; }
	
//private access and mutators
	private void checkFleetsForArivalAtMoveTarget(){
		for(int i = 0; i<assets.size(); i++){
			if(!assets.get(i).isCelestialBody()){
				Fleet f1 = (Fleet)assets.get(i);
				Asset s = f1.getActualMoveTarget();
				Direction d;
				if(s!=null){
					d = new Direction(f1.getMoveTarget().getX() + s.getSize().getX()/2, f1.getMoveTarget().getY() + s.getSize().getY()/2);
				}else
					d = f1.getMoveTarget();
				
				if(isAssetAtPosition(f1, d) && !f1.isOccupying() && s!=null){
					f1.setVelocity(new Direction(0,0));
					f1.setApproaching(false);
					 log(f1.getName()+" arrived at: " + s.getName()+"\n");
					 f1.setOccupying(true);
					//TODO: fix the null bug on move targets that are stars
					s.setOccupied(true);
					s.setOccupyingFleet(f1);
					s.setApproaching(false);
				} else if(isAssetAtPosition(f1, d) && !f1.isOccupying() && s==null && f1.getVelocity().getMagnitude()!=0){
					f1.setVelocity(new Direction(0,0));
					f1.setApproaching(false);
					log(f1.getName() + " arrived at: " + f1.getPosition()+"\n");
				}
			}
		}
	}
	
	private void speedPlanets(double v){
		for(int i = 0; i<assets.size(); i++){
			if(assets.get(i).isCelestialBody()){
				assets.get(i).changeVelocityMag(v);
			}
		}
	}
	
	private boolean isAssetAtPosition(Asset s, Direction d){
		if(s!=null&&d!=null){
			//System.out.println("Yes");
			Direction pos = s.getPosition();
			double t = 25;
				if(d.getX()-t <= pos.getX() && pos.getX() <= d.getX()+t && d.getY()-t <= pos.getY() && pos.getY() <= d.getY()+t)
					{
					return true;}
		}
		
		return false;
	}
	
	private boolean clickInRange(Asset s, Direction c){
		//Direction o = DrawPanel.getOffset();
		double scale = DrawPanel.getScalar();
		//Direction op = new Direction(o.getX()/scale, o.getY()/scale);
		//Direction = new Direction(0.0,0.0);
		if(scale==1.0){
			if(		s.getPosition().getX()  - s.getSize().getX() <= c.getX() && c.getX() <= s.getPosition().getX()   + 2*s.getSize().getX()  
				&& 	s.getPosition().getY()  - s.getSize().getY() <= c.getY() && c.getY() <= s.getPosition().getY()   + 2*s.getSize().getY())
				return true;
			else
				return false;
		} else{
			if(		s.getPosition().getX()  - s.getSize().getX() <= c.getX() && c.getX() <= s.getPosition().getX()   + 2*s.getSize().getX()  
				&& 	s.getPosition().getY()  - s.getSize().getY() <= c.getY() && c.getY() <= s.getPosition().getY()   + 2*s.getSize().getY())
					return true;
				else
					return false;
		}		
	}
	
	private boolean assetIsSelected(){
		boolean re = false;
		for(int i = 0; i< assets.size(); i++){
			if(assets.get(i).getSelected())
				re = true;
		}
		return re;
	}
	
	private Asset getAssetSelected(){
		for(int i = 0; i< assets.size(); i++){
			if(assets.get(i).getSelected())
				return assets.get(i);
		}
		return null;
	}
	
	//checks if the player clicked an asset
	private boolean checkForAssetsInRange(Direction c){
	//	for(int i = 0; i<assets.size(); i++){
	//		assets.get(i).setSelected(false);
	//	} 
		Asset isis = null;
		if(assetIsSelected())
			isis = getAssetSelected();
		
		unselectOtherAssets(isis);
		
		
		for(int i = 0; i<assets.size(); i++){
			//System.out.println("List iterate: " + i);
			if (clickInRange(assets.get(i), c)) {
				//System.out.println("List iterate: " + assets.get(i).name + " : " + i);
				if (!assets.get(i).getSelected() && assets.get(i)!=isis) {
					if (!assets.get(i).isCelestialBody()) { // checks to make sure the object isnt an occupying obj
						if (!((Fleet) (assets.get(i))).isOccupying()) {
							UniverseGame.log(assets.get(i) + " was selected (fleet). \n");
							if(MainGameFrame.getDrawPanel().getComponentCount()>=3)
									MainGameFrame.getDrawPanel().remove(2);
							MainGameFrame.getDrawPanel().add(setupInfoPanel(assets.get(i)), BorderLayout.EAST);
							unselectOtherAssets(assets.get(i));
							assets.get(i).setSelected(true);
							return true;
						}
					} else {
						UniverseGame.log(assets.get(i) + " was selected. \n");
						if(MainGameFrame.getDrawPanel().getComponentCount()>=3)
							MainGameFrame.getDrawPanel().remove(2);
						MainGameFrame.getDrawPanel().add(setupInfoPanel(assets.get(i)), BorderLayout.EAST);
						unselectOtherAssets(assets.get(i));
						assets.get(i).setSelected(true);
						return true;
					}
				} else if(assets.get(i)==isis){
					return true;
				}
			}
		}
		if(assetIsSelected())
			if(getAssetSelected()==isis){
				unselectOtherAssets(null);
				if(MainGameFrame.getDrawPanel().getComponentCount()>=3)
					MainGameFrame.getDrawPanel().remove(2);
			}
		return false;
	}
	
	private JPanel setupInfoPanel(Asset s){
		JPanel temp_panel =  new InfoPanel(s);
		temp_panel.setFocusable(true);
		return temp_panel;
	}
	
	private void unselectOtherAssets(Asset s) {
		if (s != null) {
			for (int i = 0; i < assets.size(); i++) {
				if (assets.get(i) != s) {
					assets.get(i).setSelected(false);
				}
			}
		} else {
			for (int i = 0; i < assets.size(); i++) {
				assets.get(i).setSelected(false);
			}
		}
	}
	
	private void updateCelestialBodies(int cycles){ 
		//TODO: fix the bug with it changing centers
		for(int i = 0; i<assets.size(); i++){

			if(assets.get(i).isCelestialBody()){
				//assets.get(i).changeAccelerationByTime(cycles);
				assets.get(i).changeVelocityByTime(cycles);
				//assets.get(i).changeVelocityByAcceleration();
				//assets.get(i).changePositionByVelocity();
				assets.get(i).changePositionByTime(cycles);
				//System.out.println(assets.get(i).getName() + ": " +assets.get(i).getR()+ ": " + cycles);
				assets.get(i).changePopulationByTime(cycles);
			} else{
				assets.get(i).changePositionByVelocity();
			}
			
		}
	}
	
	private void moveScreen(){
		Direction offset = DrawPanel.getOffset();
		Direction point = mouseInputHandler.getPoint();
		//System.out.println("Point: " + point);
		if(point!=null){
			//System.out.println("Height: " + DrawPanel.getScreenHeight() + " Width: " + DrawPanel.getScreenWidth());
			double offsetX = offset.getX(), offsetY = offset.getY();
			if(	point.getY() > DrawPanel.getScreenHeight() - 80 && point.getY() < DrawPanel.getScreenHeight() - 50){
				offsetY = offset.getY() - screenDelta;
			} if( point.getY() < 0  + 25 && point.getY() > 5){
				offsetY =  offset.getY() + screenDelta;
			} if( point.getX() > DrawPanel.getScreenWidth() - 270 && point.getX() < DrawPanel.getScreenWidth() - 5){ //doesnt always know where the end of the screen is
				offsetX = offset.getX() - screenDelta;
			} if( point.getX() < 0  + 50 && point.getX() > 5){
				offsetX = offset.getX() + screenDelta;
			}
		DrawPanel.setOffset(new Direction(offsetX, offsetY));
		}
		//System.out.println("Offset: " + GamePanel.getOffset());
	}
	
	private void setClock(){
		String tmp = "Time: " + String.format("%.1f", (double)Genesis.getTime()/1000);
		String time = "<html><font color='red'>" + tmp + "</font></html>";
		MainGameFrame.getGameClock().setText(time);
	}
}
