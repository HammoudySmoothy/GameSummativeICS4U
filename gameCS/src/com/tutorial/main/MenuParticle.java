package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {

	private Handler handler;
	private Random r = new Random();
	
	private Color col;
	
	private int size = r.nextInt(15)+5;
	
	private float trailSize = r.nextInt(10)/1000;
	
	
	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		
		this.handler = handler;
		
		velX = r.nextInt(9)-5;
		velY = r.nextInt(9)-5;
		
		if (velX == 0) velX +=1;
		if (velY == 0) velY +=1;
		
		col = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));

	}


	public void tick() {
		// TODO Auto-generated method stub
		x+= velX;
		y+= velY;
		
		//BORDERS
		if(y<= 0 || y>= Game.HEIGHT - 48) velY *= -1;
		if (x<=0 || x>= Game.WIDTH - 32) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, col, size, size, (float)0.05 , handler));
		
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(col);
		g.fillRect((int)x, (int)y, size, size);
		
	}
	
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		
		return new Rectangle((int)x, (int)y, size, size);
	}


}
