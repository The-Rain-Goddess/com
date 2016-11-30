package com.rain.game.Alacrity.battle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
//import java.util.Random;

//import javax.swing.JComponent;
//import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	/**
	 * The actual graphics panel for the game, all assets are drawn here
	 * Using swing/awt, no fx atm
	 */
	private static final long serialVersionUID = -1804214849072272803L;
	static final int BI_WIDTH = 600;
	static final int BI_HEIGHT = BI_WIDTH;
	private static final Color BACKGROUND_COLOR = new Color(0,0,0);
	private static final Stroke BIMAGE_DRAW_STROKE = new BasicStroke(4);
	
	private BufferedImage bImage = new BufferedImage(BI_WIDTH, BI_HEIGHT, BufferedImage.TYPE_INT_RGB);
	//private JLabel imageLabel;
	//private Random random = new Random();	
	
	//private Direction mousePoint;
	private static Direction offset = new Direction(0.0, 0.0);
	
	public GamePanel(){
		setPreferredSize(new Dimension(BI_WIDTH, BI_HEIGHT));
		Graphics2D g2 = bImage.createGraphics();
		g2.setBackground(BACKGROUND_COLOR);
		g2.clearRect(0, 0, BI_WIDTH*3, BI_HEIGHT*3);
		drawBackground(g2);
		g2.dispose();
		addMouseMotionListener(Game.getMouseAdapter());
	}
	
	@Override
	protected void paintComponent(java.awt.Graphics g){
		super.paintComponent(g);
		//g.drawImage(bImage, (int)(offset.getX()), (int)(offset.getY()),(int)(BI_WIDTH + offset.getX()), (int)(BI_HEIGHT + offset.getY()), null);
		g.drawImage(bImage, 0,0, null);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.drawLine(0+(int)offset.getX(), 0+ (int)offset.getY(), 100 + (int)offset.getX(), (int)offset.getY());
		g2.drawLine(0+(int)offset.getX(), 0+ (int)offset.getY(), (int)offset.getX(), 100 + (int)offset.getY());
		
		drawShips(g2);
		
		repaint();
	}
	
	private void drawBackground(Graphics2D g2){
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(1));
		for(int i = 0; i<1000; i++){
			int x0 = (int) (Math.random()*(BI_WIDTH) + 1);
			int y0 = (int) (Math.random()*(BI_WIDTH) + 1);
			g2.drawLine(x0 + (int)offset.getX(), y0 + (int)offset.getY(), x0+(int)offset.getX(), y0+(int)offset.getY());
		}
		
		g2.fillOval(0, 0, 200, 200);
	}
	
	private void drawShips(Graphics2D g2){
		try{
			List<Player> tmp = Game.getPlayers();
			List<Ship> tmp_ships = new ArrayList<Ship>();
			List<Ship> tmp_enmys = new ArrayList<Ship>();
			tmp_ships.clear(); tmp_enmys.clear();
			for(int i = 0; i<tmp.size(); i++){
				g2.setStroke(BIMAGE_DRAW_STROKE);
				Color shipColor;
				if(i%2==0){
					shipColor = Color.RED;
				} else{
					shipColor = Color.BLUE;	
				}
				tmp_ships = tmp.get(i).getShips();
				tmp_enmys = tmp.get(i).getVisibleEnemies();
				
				//System.out.println("GamePanel: " + tmp.get(i).getName() + ": " + tmp_enmys.size());
				
				for(int j = 0; j<tmp_ships.size(); j++){ //this works
					drawShip(tmp_ships.get(j), g2, shipColor);
				}
				/*if(tmp_enmys!=null){
					for(int j = 0; j<tmp_enmys.size(); j++){
						//drawShip(tmp_enmys.get(j), g2, Color.PINK);
					}
				}*/
				tmp_enmys.clear(); //tmp_ships.clear();
			}
		} catch(ConcurrentModificationException e){
			e.printStackTrace();
		}
	}
	
	//draws the ships and other ship assets
	private void drawShip(Ship ship, Graphics2D g2,  Color  c){
		g2.setColor(c);
		Path2D path = ship.getPath(offset);
		
		if(ship.getVisible() && Game.debug && c==Color.RED){ //is spotted?
			g2.setColor(Color.PINK);
		} else if(ship.getVisible() && Game.debug && c==Color.BLUE){
			g2.setColor(Color.CYAN);
		}
		
		if(ship.getSelected() && c == Color.RED)
			g2.setColor(Color.YELLOW);
		g2.fill(path);	
		
		Direction pos = new Direction(ship.getPosition().getX() + offset.getX(), ship.getPosition().getY()+offset.getY());
		
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(1));
		g2.draw(path);
		
		double hp = 20.0 * (ship.getCurrentHp() / ship.getTotalHp());
		
		Rectangle2D percenthp = new Rectangle.Double(pos.getX() - 5, pos.getY() - 10, hp, 5);
		g2.setColor(Color.GREEN);
		g2.fill(percenthp);
		
		Rectangle2D hpbar = new Rectangle.Double(pos.getX() - 5, pos.getY() - 10, 20, 5);
		g2.setColor(Color.white);
		g2.draw(hpbar);
		
		if(Game.debug){
			Ellipse2D sightRange = new Ellipse2D.Double(pos.getX() - ship.getSightRange(), pos.getY() - ship.getSightRange(), 2*ship.getSightRange(), 2*ship.getSightRange());
			g2.setColor(Color.GRAY);
			g2.draw(sightRange);
			
			Ellipse2D combatRange = new Ellipse2D.Double(pos.getX() - ship.getCombatRange(), pos.getY() - ship.getCombatRange(), 2*ship.getCombatRange(), 2*ship.getCombatRange());
			g2.setColor(Color.RED);
			g2.draw(combatRange);
			
			
			
			g2.setStroke(new BasicStroke(1));
			g2.setColor(c);
			g2.draw(new Line2D.Double( pos.getX(), pos.getY(), ship.getVelocity().getX()*100 + pos.getX(), ship.getVelocity().getY()*100 + pos.getY() ));
			
		}
		
		if(ship.getCombatTarget()!=null){
			Line2D targetLine = new Line2D.Double(pos.getX(), pos.getY(), ship.getCombatTarget().getPosition().getX() + offset.getX() + 5, ship.getCombatTarget().getPosition().getY()+ offset.getY());
			if( c==Color.RED)
				g2.setColor(Color.MAGENTA);
			else
				g2.setColor(Color.BLUE);
			g2.draw(targetLine);
		}
	}
	
	public static Direction getOffset(){ return offset;}
	
	public static void setOffset(Direction off){
		GamePanel.offset = off;
	}

}
