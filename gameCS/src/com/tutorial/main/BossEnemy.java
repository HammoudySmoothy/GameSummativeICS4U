package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy extends GameObject {

	private Handler handler;
	private int timer = 60;
	private int timer2 = 50;
	Random r = new Random();
	
	public BossEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		
		this.handler = handler;
		
		velX = 0;
		velY = 2;
	}


	public void tick() {
		// TODO Auto-generated method stub
		x+= velX;
		y+= velY;
		
		if(timer<= 0) {velY = 0; timer2--;}
		else timer --;
		
		if(timer2 <=0) {
			
			if(velX == 0) velX = 2;
			if(velX > 0) velX += (float) 0.05;
			if(velX < 0) velX -= (float) 0.05;
			velX = Game.clamp(velX, -10 , 10);
				
			int spawn = r.nextInt(7);
			if(spawn ==0) handler.addObject(new BossEnemyBullet((int)x +48, (int)y + 48, ID.BossEnemyBullet, handler));
			
		}
		
		if (x<=0 || x>= Game.WIDTH - 96) velX *= -1;		
		
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 96, 96);
		
	}
	
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		
		return new Rectangle((int)x, (int)y, 96, 96);
	}


}
