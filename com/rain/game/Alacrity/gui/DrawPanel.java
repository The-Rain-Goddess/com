package com.rain.game.Alacrity.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import com.rain.game.Alacrity.assets.Fleet;
import com.rain.game.Alacrity.assets.Star;
import com.rain.game.Alacrity.battle.Direction;
import com.rain.game.Alacrity.main.Asset;
import com.rain.game.Alacrity.main.UniverseGame;

public class DrawPanel extends JPanel {
	/**
	 *  @author Rain
	 */
	private static final long serialVersionUID = 1L;
	static final int PANEL_WIDTH = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	static final int PANEL_HEIGHT = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	
	private static final Color BACKGROUND_COLOR = Color.ORANGE;
	private BufferedImage bImage = new BufferedImage(PANEL_WIDTH*3, PANEL_HEIGHT*3, BufferedImage.TYPE_INT_RGB);
	private BufferedImage image = new BufferedImage(PANEL_WIDTH*3, PANEL_HEIGHT*3, BufferedImage.TYPE_INT_RGB);
	private static Direction offset = new Direction(0.0,0.0);
	
	private static double scalar = 1.0;
	
//public constructors
	public DrawPanel() {
		// TODO Auto-generated constructor stub
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		Graphics2D g2 = bImage.createGraphics();
		g2.clearRect(0, 0, PANEL_WIDTH*3, PANEL_HEIGHT*3);
		g2.setBackground(DrawPanel.BACKGROUND_COLOR);
		g2.dispose();
		
	}
	
	@Override
	protected void paintComponent(java.awt.Graphics g){
		try{
			super.paintComponent(g);
			g.drawImage(bImage, 0, 0, null);
			
			///// all drawing done here
			draw(g);
			
			///// the looping repaint is here
			Thread.sleep(25L);
			repaint();
			
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
//non-private access and mutators
	public static int getScreenWidth(){ return PANEL_WIDTH; }
	
	public static int getScreenHeight(){ return PANEL_HEIGHT; }
	
	public static Direction getOffset(){ return offset; }
	
	public static void setOffset(Direction off){ offset = off; }
	
	public static double getScalar(){ return scalar; }
	
	public static void incrementScalar(double i){ scalar = scalar + i; }
	
	public static void setScalar(double d){ scalar = d; }
	
//private mutators	
	//most of the drawing setup is here
	/**
	 * 
	 * @param obj
	 * @return angle of obj on circle in radians
	 */
	private double findAngle(Direction obj, Direction circle){
		return Math.atan2(obj.getY() - circle.getY(), obj.getX() - circle.getX());
	}
	
	private void draw(java.awt.Graphics g){
		Graphics2D g3 = image.createGraphics();
		g3.clearRect(0, 0, PANEL_WIDTH*3, PANEL_HEIGHT*3);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setBackground(BACKGROUND_COLOR);
		
		
		//all screen assets drawn here
		drawAssets(g3); //change to g3 for scaling of image
		
		drawHud(g3);
		
		g3.dispose();
		if(scalar != 1.0)
			scaleImage();
		g2.drawImage(image,0,0, null);
		image.flush();
	}
	
	private void drawAssets(Graphics2D g2){
		List<Asset> assets = UniverseGame.getAssets();
		Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		double ox = offset.getX(); double oy = offset.getY();
	//connecting lines
		for(int i = 0; i < assets.size(); i++){
			Star s = assets.get(i).getSun();
			if(s!=null){
				Asset s1 = assets.get(i);
				g2.setStroke(dashed);
				Line2D hyperLane = new Line2D.Double(	ox+s1.getPosition().getX() + s1.getSize().getX()/2, 
														oy+s1.getPosition().getY() + s1.getSize().getX()/2, 
														ox+s.getPosition().getX() + s.getSize().getX()/2, 
														oy+s.getPosition().getY() + s.getSize().getY()/2);
				g2.setColor(Color.LIGHT_GRAY);
				g2.draw(hyperLane);
				g2.setStroke(new BasicStroke(1));
			}
		}
		
	//game assets	
		for(int i = 0; i < assets.size(); i++){
			drawAsset(g2, assets.get(i));
		}
		
	//dragLines	
		List<Direction> drag = UniverseGame.getDragList();
		if(drag.size()>=2){
			g2.setColor(Color.PINK);
			
			g2.setStroke(dashed);
			Line2D dragLine = new Line2D.Double(ox+drag.get(0).getX(), 
												oy+drag.get(0).getY(), 
												ox+drag.get(drag.size()-1).getX(), 
												oy+drag.get(drag.size()-1).getY());
			g2.draw(dragLine);
			g2.setStroke(new BasicStroke(1));
		}
	}
	
	private void scaleImage(){
		BufferedImage before = image;//image.getSubimage((int)offset.getX(), (int)offset.getY(), PANEL_WIDTH, PANEL_HEIGHT);
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(scalar, scalar);
		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		image = scaleOp.filter(before, after);
	}
	
	private void drawScale(int start, Graphics2D g2){
		g2.setColor(Color.ORANGE);
		g2.drawLine(300+(int)offset.getX(), 300+(int)offset.getY(), 1200+(int)offset.getX(), 300+(int)offset.getY());
		for(int i = start; i<=1200; i+=100){
			String a = i+"";
			g2.drawString(a, i+(int)offset.getX(), 300+(int)offset.getY());
			g2.drawLine(i+(int)offset.getX(), 300+(int)offset.getY(), i+(int)offset.getX(), 275+(int)offset.getY());
		}
	}
	
	private void drawHud(Graphics2D g2){
		drawScale(300, g2);
	}

	private void drawAsset(Graphics2D g2, Asset s1){
		Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		double ox = offset.getX(); double oy = offset.getY();
		
		//celestial body rendering!
		if(s1.isCelestialBody()){
			
			//approach target drawing
			if(s1.getApproaching()){
				Direction approach = s1.getApproachTarget();
				Line2D approachLine = new Line2D.Double(approach.getX()+ox, 
														approach.getY()+oy, 
														ox+s1.getPosition().getX()+s1.getSize().getX()/2, 
														oy+s1.getPosition().getY()+s1.getSize().getY()/2);
				double angle1 = findAngle(approach, s1.getSun().getPosition());
				double angle2 = findAngle(s1.getPosition(), s1.getSun().getPosition());
				//System.out.printf("Angles: %,.3f :: %,.3f\n", angle1, angle2);
				@SuppressWarnings("unused")
				Arc2D approachArc;
				if(angle2 > angle1)
					approachArc = new Arc2D.Double(	ox+s1.getSun().getPosition().getX()-s1.getR()+s1.getSun().getSize().getX()/2, oy+s1.getSun().getPosition().getY()-s1.getR()+s1.getSun().getSize().getY()/2, s1.getR()*2, s1.getR()*2, angle1, angle2, Arc2D.PIE);
				else
					approachArc = new Arc2D.Double(	ox+s1.getSun().getPosition().getX()-s1.getR()+s1.getSun().getSize().getX()/2, oy+s1.getSun().getPosition().getY()-s1.getR()+s1.getSun().getSize().getY()/2, s1.getR()*2, s1.getR()*2, angle2, angle1, Arc2D.PIE);
					
				g2.setColor(Color.green);
				//g2.draw(approachArc);
				g2.setStroke(dashed);
				g2.draw(approachLine);
				
				g2.setStroke(new BasicStroke(1));
				Ellipse2D app = new Ellipse2D.Double(ox+approach.getX()-2.5,oy+approach.getY()-2.5,5,5);
				g2.draw(app);
			}
			
			//selected asset drawing
			if(s1.getSelected()){
				Rectangle2D selectedSquare = new Rectangle2D.Double(ox+s1.getPosition().getX()-s1.getSize().getX(), oy+s1.getPosition().getY()-s1.getSize().getY(), s1.getSize().getX()*3, s1.getSize().getY()*3);
				g2.setColor(Color.RED);
				g2.draw(selectedSquare);
			}
			
			//asset drawing
			Direction pos = s1.getPosition();
			if(s1.getOccupied()){
				Path2D occFleet = new Path2D.Double();
				occFleet.moveTo(ox+pos.getX() - s1.getSize().getX(), oy+pos.getY() + s1.getSize().getY()/2 );
				occFleet.lineTo(ox+pos.getX() + s1.getSize().getX()*3/2, oy+pos.getY() - s1.getSize().getY()/2);
				occFleet.lineTo(ox+pos.getX() + s1.getSize().getX()*3/2, oy+pos.getY() + s1.getSize().getY()*3/2);
				occFleet.lineTo(ox+pos.getX() - s1.getSize().getX(), oy+pos.getY() + s1.getSize().getY()/2 );
				
				//if(s1.getOccupyingFleet().getPlayer()==1)
					g2.setColor(Color.BLUE);
				//
					//g2.setColor(Color.RED);
				g2.fill(occFleet);
			}
			
			if(true && s1.getVelocity()!=null){
					Line2D projectionLine = new Line2D.Double(	ox+s1.getPosition().getX()+ s1.getSize().getX()/2, 
																oy+s1.getPosition().getY() + s1.getSize().getY()/2, 
																ox+s1.getVelocity().getX()*1000 + s1.getPosition().getX(),
																oy+s1.getVelocity().getY()*1000 + s1.getPosition().getY());
					g2.setColor(Color.RED);
					g2.draw(projectionLine);
			}
			
			Ellipse2D star = new Ellipse2D.Double(	ox+s1.getPosition().getX() ,
													oy+s1.getPosition().getY() , 
													s1.getSize().getX() , 
													s1.getSize().getY());
			g2.setColor(s1.getColor());
			g2.fill(star);
			
			//showing ownership of the celestial body
			if(s1.getOwnedBy()!=0){
				//SizeF size = new SizeF();
				int width = g2.getFontMetrics().stringWidth(s1.getName());
				if(s1.getOwnedBy()==1){
					g2.setColor(Color.BLUE);
					g2.fillRect((int)(ox+s1.getPosition().getX() - s1.getSize().getX()/2)-1, (int)(oy+s1.getPosition().getY())-9, width+2, 10);
				} else if(s1.getOwnedBy()==2){
					g2.setColor(Color.RED);
					g2.fillRect((int)(ox+s1.getPosition().getX() - s1.getSize().getX()/2)-1, (int)(oy+s1.getPosition().getY())-9, width+2, 10);
				}
			}
			
			g2.setColor(Color.WHITE);
			g2.drawString(	s1.getName(), 
							(float)(ox+s1.getPosition().getX() - s1.getSize().getX()/2), 
							(float)(oy+s1.getPosition().getY()) );
		} 
		
		//fleet rendering!
		else{
			Fleet f = (Fleet)s1;
			if(!f.isOccupying()){
				if(((Fleet)s1).getMoveTarget()!=null){
					g2.setColor(Color.RED);
					g2.setStroke(dashed);
					Asset s2 = ((Fleet)s1).getActualMoveTarget();
					double aa = 0, ab = 0;
					if(s2!=null){
						//aa = s2.getSize().getX();
						//ab = s2.getSize().getY();
					}
						
					Line2D movePath = new Line2D.Double(ox+((Fleet)s1).getMoveTarget().getX()+ aa, 
														oy+((Fleet)s1).getMoveTarget().getY() + ab, 
														ox+s1.getPosition().getX(), 
														oy+s1.getPosition().getY());
					g2.draw(movePath);
					g2.setStroke(new BasicStroke(1));
				}
				//System.out.println(s1);
				g2.setColor(Color.BLUE);
				Path2D path = ((Fleet) s1).getPath();
				//System.out.println();
				g2.fill(path);
				g2.setColor(Color.WHITE);
				g2.draw(path);
				
				if(s1.getSelected()){
					Rectangle2D selectedSquare = new Rectangle2D.Double(ox+s1.getPosition().getX()-s1.getSize().getX(), 
																		oy+s1.getPosition().getY()-s1.getSize().getY(), 
																		s1.getSize().getX()*2, 
																		s1.getSize().getY()*2);
					g2.setColor(Color.RED);
					g2.draw(selectedSquare);
				}
			}
		}
	}
}
