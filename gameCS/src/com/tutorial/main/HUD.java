package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public int bounds = 0;
	public static float HEALTH = 1000000;
	
	private int greenValue = 255;
	private int score = 0;
	private int level = 0;
	
	
	public void tick() {

		HEALTH = Game.clamp(HEALTH, 0, 100 + bounds/2);
		greenValue = (int) (HEALTH*2);
		greenValue = (int) Game.clamp(greenValue, 0, 255);
		score ++;
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200 + bounds, 32);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, (int) (HEALTH * 2), 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200 + bounds, 32);
		
		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: "+ level, 15, 80);
		g.drawString("Space for Shop", 15, 94);
	}

	public void score(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void level(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
}