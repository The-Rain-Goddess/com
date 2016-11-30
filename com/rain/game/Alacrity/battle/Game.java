package com.rain.game.Alacrity.battle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
/**
 * 
 * @author The_Rain_Goddess
 *	TODO: 
 *		implement combat system
 *		implement unit selection for human using drag mouse	
 */
public class Game implements Runnable {
	private static long time;
	private static List<Player> players = Collections.synchronizedList(new ArrayList<Player>());
	private static boolean gameRunning;
	private static MyMouseAdapter mouseListener = new MyMouseAdapter();;
	private static InputActionListener inputActionListener = new InputActionListener();
	//private static com.rain.game.Alacrity.KeyAdapter keyListener;
	private static InputListener input;
	private List<Direction> dragList;
	private Graphics graphics;
	private final int screenDelta = 10;
	
	public static boolean debug = true;
	
	public void cleanup(){
		players.clear();
		GamePanel.setOffset(new Direction(0.0, 0.0));
	}
	
//public constructor	
	public Game(){
		cleanup();
		
		//gametime start
		System.out.println("Game_Start");
		gameRunning = true;
		time = System.currentTimeMillis();
		dragList = new ArrayList<>();
		
		//player contructors
		players.add(new Player("Player_1"));
		players.add(new Player("Player_2", true));
		
		//graphics thread start
		this.graphics = new Graphics();
		Thread graphics = new Thread(this.graphics);
		graphics.start();
		System.out.println("Main Thread: Graphics_start");
		
		//keylistener
		input = new InputListener();
		Thread keyboard = new Thread(input);
		keyboard.start();
		System.out.println("Main Thread: Keyboard_start");
		
		//combatHandler
		VisionHandler visionHandler = new VisionHandler();
		Thread combat = new Thread(new CombatHandler(visionHandler));
		combat.start();
		
		//visionHandler

	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			Thread.sleep(500L);
			while(Alpha.gameRunning){
				Thread.sleep(100L);
				//TODO: affect all players here
				//System.err.println("Mouse: " +mouseListener.getClickType() + " : " + mouseListener.getPoint());
				
				for(int i = 0; i< players.size(); i++){
					if(i%2==0){ //only for the human, not the cpu
						//gives the move command
						if(mouseListener.getClick() && mouseListener.getClickType()){
							List<Ship> selectShips = players.get(i).getSelectedShips();
							if(selectShips.size()!=0)
								players.get(i).setMoveTarget(new Direction(mouseListener.getPoint().getX() - GamePanel.getOffset().getX(), mouseListener.getPoint().getY() - GamePanel.getOffset().getY()), selectShips);
							else
								players.get(i).setMoveTarget(new Direction(mouseListener.getPoint().getX() - GamePanel.getOffset().getX(), mouseListener.getPoint().getY() - GamePanel.getOffset().getY()));		
							mouseListener.setClick(false);
						}
					
						//this is to have the player select ships
						if(mouseListener.getDrag()){
							//System.out.println("DRAGGING");
							players.get(i).unSelectAll();
							dragList.add(mouseListener.getPoint());
						} else if( dragList.size()!=0){
							//System.out.println("Dragged");
							//findShips betweeen list[0] and list[n]
							System.out.println(dragList);
							dragList.add(mouseListener.getPoint());
							findShips(players.get(i).getShips(), dragList.get(0), dragList.get(dragList.size()-1));
							dragList.clear();
							//clear list
						} else
							System.out.println("Not Draggging");
					}	
					
					if(inputActionListener.hasInput()){	//inputHandler
						String temp =  inputActionListener.getInput() + ":";
						//Game.log(temp);
						switch(temp.charAt(0)){
							case 'a': Game.log(temp + " Ships Accelerating!\n"); players.get(i).accelerateShips(1.1); break;
							case 'd': Game.log(temp + " Ships Decelerating!\n");players.get(i).accelerateShips(.9); break;
							case 's': Game.log(temp + " Ships Stopping!\n");players.get(i).accelerateShips(0); break;
							case 'q': Game.log(temp + "\n"); toggleDebug(); break;
							case 'r': Game.log(temp + "\n"); GamePanel.setOffset(new Direction(0,0)); break;
							default: Game.log(temp + "\n"); break;
						}
						
					}
					players.get(i).update();	
				}
			//non-player related stuff	
				moveScreen();
				
				if(graphics.getWindowClosed()){
					Alpha.setGameRunning(false);
					Thread.currentThread().interrupt();
					return;
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			System.out.println("game_over");
		}
	}
	
//non-private access and mutators
	public static double findRangeBetween(Ship center, Ship satellite){
		double range;
		double dx = Math.pow(center.getPosition().getX() - satellite.getPosition().getX(),2);
		double dy = Math.pow(center.getPosition().getY() - satellite.getPosition().getY(),2);
		double diff = dx + dy;
		range = Math.abs(Math.sqrt(diff));
		return range;
	}
	
	public static void log(String logged){
		JTextArea chat = Graphics.getChatField();
		String time = String.format("%,.3f", (Time.getTime() - Game.getTime() + 0.0 )/ 1000 ) + ": ";
		chat.setText( time + logged + chat.getText());
		Highlighter.HighlightPainter greenPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
		try {
			chat.getHighlighter().addHighlight(0, 6, greenPainter);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	private void findShips(List<Ship> ships, Direction start, Direction end){
		System.out.println("Start, End: " + start + " " + end);
		if(start.getX() > end.getX()){
			double x = end.getX();
			Direction off = GamePanel.getOffset();
			end = new Direction(start.getX() - off.getX(), end.getY() - off.getY());
			start = new Direction(x - off.getX(), start.getY() - off.getY());
		} else if( start.getX() <= end.getX()){
			
		}
		
		if(start.getY() > end.getY()){
			double y = end.getY();
			end = new Direction(end.getX(), start.getY());
			start = new Direction(start.getX(), y);
		}
		
		for(int i = 0; i<ships.size(); i++){
			Direction pos = ships.get(i).getPosition();
			if(pos.getX()<= end.getX() && pos.getX()>= start.getX() && pos.getY()<= end.getY() && pos.getY()>= start.getY()){
				ships.get(i).setSelected(true);
				System.out.println(ships.get(i));
			}
		}
	}
	
	private void moveScreen(){
		Direction offset = GamePanel.getOffset();
		Direction point = mouseListener.getPoint();
		//System.out.println("Point: " + point);
		if(point!=null){
			double offsetX = offset.getX(), offsetY = offset.getY();
			if(	point.getY() > GamePanel.BI_HEIGHT - 50 && point.getY() < GamePanel.BI_HEIGHT - 5){
				offsetY = offset.getY() - screenDelta;
			} if( point.getY() < 0  + 50 && point.getY() > 5){
				offsetY =  offset.getY() + screenDelta;
			} if( point.getX() > GamePanel.BI_WIDTH - 50 && point.getX() < GamePanel.BI_WIDTH - 5){
				offsetX = offset.getX() - screenDelta;
			} if( point.getX() < 0  + 50 && point.getX() > 5){
				offsetX = offset.getX() + screenDelta;
			}
		GamePanel.setOffset(new Direction(offsetX, offsetY));
		}
		//System.out.println("Offset: " + GamePanel.getOffset());
	}
	
	public static void toggleDebug(){
		if(debug)
			debug = false;
		else
			debug = true;
	}
	
	//access
	public static List<Player> getPlayers(){ return players; }
	
	public static MyMouseAdapter getMouseAdapter(){ return mouseListener; }
	
	public static long getTime(){ return time; }
	
	public static boolean getGameRunning(){ return gameRunning; }
	
	public static InputActionListener getInputActionListener(JTextField field){ inputActionListener.setField(field);return inputActionListener; }
}

//private mutators
