package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject {

	private Handler handler;
	
	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		
		this.handler = handler;
		
		velX = 2;
		velY = 10;	
	}


	public void tick() {
		// TODO Auto-generated method stub
		x+= velX;
		y+= velY;
		
		//BORDERS
		if(y<= 0 || y>= Game.HEIGHT - 48) velY *= -1;
		if (x<=0 || x>= Game.WIDTH - 32) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.pink, 12, 12, (float) 0.05, handler));
		
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.pink);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}
	
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		
		return new Rectangle((int)x, (int)y, 16, 16);
	}


}
