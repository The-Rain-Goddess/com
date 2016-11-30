package com.rain.ballGame;
import java.awt.Color;

public class ball {
	
//public space variable
	double x; double y;
	Color c;
	
	double vx; double vy;
	
	public ball(){
		//void ball
	}
	
	public ball(double x1, double y1, Color c1, double v)
	{
		x = Math.random()*(x1 - 40) + 40;
		y = Math.random()*(y1 - 40) + 40;
		c = c1;
		
		vx = Math.random()*(v- -v) -v;
		vy = Math.random()*(v- -v) -v;
	}
	
	public boolean update(int highLimitX, int highLimitY)
	{	
		if(x+vx<highLimitX-30 && x+vx>30)
			x += vx;
		else if(x+vx>highLimitX-30 || x+vx<30)
			{vx*=-1; x+=vx;}
		if(y+vy<highLimitY-30 && y+vy>30)
			y += vy;
		else if(y+vy>highLimitY-30 || y+vy<30)
			{vy*=-1; y+=vy;}
		
		return true;
	}

}
