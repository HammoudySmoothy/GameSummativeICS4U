package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random();
	Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		this.handler = handler;
	}

	public void tick() {
		// TODO Auto-generated method stub
		x+= velX;
		y+= velY;
		
		//Borders for Player
		x = Game.clamp((int)x, 0,  Game.WIDTH - 48);
		y = Game.clamp((int)y, 0,  Game.HEIGHT - 72);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, (float) 0.1, handler));
		
		collision();
		
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
		//Collision box shown
		//Graphics2D g2d = (Graphics2D) g;
		//g.setColor(Color.green);
		//g2d.draw(getBounds());;
		
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 32, 32);
		
	}


	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public void collision() {
		
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.BasicEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					//Collision Code
					HUD.HEALTH -=2;
				}
				
			}
			
			if(tempObject.getID() == ID.FastEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					//Collision Code
					HUD.HEALTH -=1;
				}
				
			}
			if(tempObject.getID() == ID.SmartEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					//Collision Code
					HUD.HEALTH -=1;
				}
				
			}
			
		}
	}

}
