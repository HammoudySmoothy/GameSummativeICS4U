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
		
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		if(id == ID.Player) g.setColor(Color.white);
		else if (id == ID.Player2) g.setColor(Color.blue);
		g.fillRect(x, y, 32, 32);
	}

}
