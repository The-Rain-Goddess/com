package com.rain.game.alacrity0;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6743978570972763387L;
	private static final int ST_WIDTH = 900;
	private static final int ST_HEIGHT = 700;
	private static final Color BACKGROUND_COLOR = Color.BLACK;
	private static final float STROKE_WIDTH = 1f;
	private static final Stroke STROKE = new BasicStroke(STROKE_WIDTH,
            BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	private static final Color[] colors = {Color.black, Color.blue, Color.red,
			Color.green, Color.orange, Color.MAGENTA};
	
	private BufferedImage bImage = new BufferedImage(ST_WIDTH, ST_HEIGHT, BufferedImage.TYPE_INT_RGB);
	//private Color color = Color.black;
	private ArrayList<Point2D> Point = new ArrayList<Point2D>();
	private Path2D.Double path;
	private int colorIndex = 0;

	public DrawPanel() {
	      Graphics g = bImage.getGraphics();
	      g.setColor(BACKGROUND_COLOR);
	      g.fillRect(0, 0, ST_WIDTH, ST_HEIGHT);
	      g.dispose();
	   }
	
	protected void refresh(){
		Graphics2D g2 = bImage.createGraphics();
		g2.clearRect(0, 0, ST_WIDTH, ST_HEIGHT);
	}
	
	public void fill(Path2D.Double path1){
		Graphics2D g2 = bImage.createGraphics();
		g2.setColor(Color.WHITE);
		g2.fill(path1);
		this.path = path1;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
  		g.drawImage(bImage, 0, 0, null);
      	Graphics2D g2 = (Graphics2D) g;
      	drawCurve(g2);
      	//g2.draw(new Line2D.Double(0,0,300,300));
      	
      	//if(path!=null){
      		//g2.draw(path);
      		//g2.fill(path.getBounds2D());
      		//g2.fill(path);
      		//System.out.println("not null");
      	//}
      	repaint();
	}

	private void addCurveToBufferedImage() {
	   	Graphics2D g2 = bImage.createGraphics();
	   	g2.setColor(Color.red);
      	drawCurve(g2);
      	if(path!=null)
      		g2.fill(path);
      	g2.dispose();
   	}

   	private void drawCurve(Graphics2D g2) {
   		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
               RenderingHints.VALUE_ANTIALIAS_ON);
      	g2.setStroke(STROKE);
      	g2.setColor(Color.red);
      	if (Point != null && Point.size() > 1) {
      		for (int i = 0; i < Point.size() - 1; i++) {
      			double x1 = Point.get(i).getX();
            	double y1 = Point.get(i).getY();
            	double x2 = Point.get(i + 1).getX();
            	double y2 = Point.get(i + 1).getY();
            	//g2.drawLine(x1, y1, x2, y2);
            	g2.draw(new Line2D.Double(x1, y1, x2, y2));
      		}
      	}
   	}

   	@Override
   	public Dimension getPreferredSize() {
	   	return new Dimension(ST_WIDTH, ST_HEIGHT);
   	}

   	public void curveStart(Point2D point) {
	   	Point.clear();
      	Point.add(point);
   	}

   	public void curveEnd(Point2D point) {
	   Point.add(point);
      	addCurveToBufferedImage();
      	Point.clear();
      	repaint();

      	colorIndex++;
      	colorIndex %= colors.length;
      	setColor(colors[colorIndex]);
   	}

   	public void curveAdd(Point2D point) {
	   Point.add(point);
	   repaint();
   	}

   	public void setColor(Color color) {
	   //this.color = color;
   	}
}
