package com.rain.ballGame;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ballGame extends Applet implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6066024042725699399L;
	//graphics
	Graphics g;
	Graphics2D bufferedGraphics;
	Graphics2D bufferedGraphics2;
	
	
	BufferedImage offScreen;
	BufferedImage backGround;
	
	//public ints
	public int gameSizeX = 700;//1590;//1590;
	public int gameSizeY = 500;//778;//778;
	
	//public arraylists
	public ArrayList<ball> cold;
	
	//dummy variables
	ball bb;
	
	Thread t;
	
//initiation
	@Override
	public void init(){
		
		cold = new ArrayList<ball>();
		fillList(30);
		
		setBackground(Color.black);
		
		offScreen = new BufferedImage(gameSizeX,gameSizeY,BufferedImage.TYPE_INT_ARGB);
		backGround = new BufferedImage(gameSizeX,gameSizeY,BufferedImage.TYPE_INT_ARGB);
		
        //g2 = (Graphics2D) g;
        //g2 = offScreen2.createGraphics();
		bufferedGraphics2 = offScreen.createGraphics();
        bufferedGraphics = backGround.createGraphics(); 
        
        ////////////////////////////////////////////////////////
        t = new Thread(this);

		this.resize(gameSizeX,gameSizeY);

		t.start();
	}
	
//populates the list with balls
	public void fillList(int n)
	{
		for(int i = 0; i<n; i++)
		{	
			bb = new ball(gameSizeX-40,gameSizeY-40, Color.green, 5);
			cold.add(bb);
			bb = new ball(gameSizeX-40, gameSizeY-40, Color.red, 1);
			cold.add(bb);
			bb = new ball(gameSizeX-40, gameSizeY-40, Color.yellow, 3);
			cold.add(bb);
		}
		System.out.println(cold.size());
	}
	
//runnn method
	@Override
	public void run() {
		
		while(true)
		{
			repaint();
			try{

				Thread.sleep(4000/50);
			}
			catch( InterruptedException e)
			{;}
		}
	}
	
	public ballGame(){
		//void bitches
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ballGame game = new ballGame();
		game.start();
	}
	
//drawforballs
	public void draw(Graphics g2)
	{
		for(int i = 0; i<cold.size(); i++)
		{
			g2.setColor(cold.get(i).c);
			g2.fillOval((int)cold.get(i).x, (int)cold.get(i).y, 20, 20);
			g2.setColor(Color.white);
			g2.drawOval((int)cold.get(i).x, (int)cold.get(i).y, 20, 20);
		}
	}
	
//update method
	public void update()
	{
		for(int i = 0; i<cold.size(); i++)
		{
			cold.get(i).update(gameSizeX, gameSizeY);
		}
	}

//main paint method
	@Override
	public void paint(Graphics bufferedGraphics)
	{	bufferedGraphics2.clearRect(0,0,gameSizeX,gameSizeY); 
		//bufferedGraphics.clearRect(0,0,gameSizeX,gameSizeY);
//draw the balls
		draw(bufferedGraphics2);
		bufferedGraphics.drawImage(offScreen, 0, 0, this);
//update everything
		update();
		bufferedGraphics2.clearRect(0,0,gameSizeX,gameSizeY); 
//testing secondary draw
		draw(bufferedGraphics2);
		bufferedGraphics.drawImage(offScreen, 0, 0, this);
	}
}
