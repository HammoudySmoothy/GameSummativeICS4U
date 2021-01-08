package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random();
	
	public Player(int x, int y, ID id) {
		super(x,y,id);
		
		
	}

	public void tick() {
		// TODO Auto-generated method stub
		x+= velX;
		y+= velY;
		
		//Borders for Player
		x = Game.clamp(x, 0,  Game.WIDTH - 48);
		y = Game.clamp(y, 0,  Game.HEIGHT - 72);
		
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		if(id == ID.Player) g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}

}
