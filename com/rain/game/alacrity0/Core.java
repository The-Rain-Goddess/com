package com.rain.game.alacrity0;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

public class Core implements Runnable{
	public static int player_count = 0;
	public static final String player_1 = "player_1";
	public static final String player_2 = "player_2";
	
	public static List<Player> players = Collections.synchronizedList(new ArrayList<>());
	public static List<Ship> ships = Collections.synchronizedList(new ArrayList<>());
	
	public static DrawPanel dp;
	
//public constructor	
	public Core(int numOfPlayers){
		player_count = numOfPlayers;
		for(int i = 0; i<numOfPlayers; i++){
			Player temp_player = new Player();
			players.add(temp_player);
		}
		System.out.println("constructed");
		
	}
	
	@Override
	public void run(){
		try{
			Thread.sleep(5000L);
			while(true){
				//System.out.println(ships);
				updateAllShips();
				dp.refresh();
				//System.out.println("thers a");
			}
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
//main	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//main event loop queue
		setupShips();
		java.awt.EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
				createUI();
			}
		});
		System.out.println("rad");
		Thread t = new Thread(new Core(2));
		t.start();
		
	}
	
//UI setup
	private static void createUI(){
		dp = new DrawPanel();
		MMouseAdapter mMouseAdapter = new MMouseAdapter(dp);
		dp.addMouseListener(mMouseAdapter);
		dp.addMouseMotionListener(mMouseAdapter);
		
		System.out.println("Bags");
		
		
		JFrame frame = new JFrame("Drawing");
		frame.getContentPane().add(dp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private static void updateAllShips(){
		//System.out.println("never");
		for(int i = 0; i<ships.size(); i++){
			//System.out.println("not here");
			Ship temp_ship = ships.get(i);
			//System.out.println(temp_ship);
			//Path2D.Double path = new Path2D.Double();
			//path.moveTo(temp_ship.getPosition().get(0).getX(), temp_ship.getPosition().get(0).getY());
			dp.curveStart(temp_ship.getPosition().get(0));
			dp.curveAdd(temp_ship.getPosition().get(1));
			dp.curveAdd(temp_ship.getPosition().get(2));
			/*for(int j = 1; j<temp_ship.getVertices()-1; j++){
				dp.curveAdd(temp_ship.getPosition().get(j));
				//path.lineTo(temp_ship.getPosition().get(j).getX(),temp_ship.getPosition().get(j).getY() );
			} */ //path.lineTo(temp_ship.getPosition().get(0).getX(), temp_ship.getPosition().get(0).getY());
			//path.closePath();
			//System.out.println(path.getCurrentPoint());
			//dp.fill(path);
			dp.curveEnd(temp_ship.getPosition().get(0));
			ships.get(i).updatePosition();
		} 
	}
	
	private static void setupShips(){
		ArrayList<Point2D> temp = new ArrayList<>();
		temp.add(new Point2D.Double(100.0, 100.0));
		temp.add(new Point2D.Double(120.0, 100.0));
		temp.add(new Point2D.Double(110, 120.0)); 
		Frigate f2 = new Frigate("Black Snow", temp);
		Frigate f1 = new Frigate("Black Snow", new Point2D.Double(50, 50),
				new Point2D.Double(.1, .01), 10); //TODO fix the bug with this method deforming the ships
		ships.add(f1);
		ships.add(f2);
	}

}
