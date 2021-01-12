package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemyBullet extends GameObject {

	private Handler handler;
	Random r = new Random();
	
	public BossEnemyBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		
		this.handler = handler;
		
		velX = (r.nextInt(5 - -5) + -5);
		velY = 5;
	}


	public void tick() {
		// TODO Auto-generated method stub
		x+= velX;
		y+= velY;
		
		//BORDERS
	//if(y<= 0 || y>= Game.HEIGHT - 48) velY *= -1;
	//	if (x<=0 || x>= Game.WIDTH - 32) velX *= -1;
		if(y<= 0 || y>= Game.HEIGHT ) handler.removeObject(this);
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 16, 16, (float) 0.1, handler));
		
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}
	
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		
		return new Rectangle((int)x, (int)y, 16, 16);
	}


}
