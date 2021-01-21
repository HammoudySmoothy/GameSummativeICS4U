package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public int bounds = 0;
	public static float HEALTH = 100;
	
	private int greenValue = 255;
	private int score = 0;
	private int gold = 0;
	private int level = 0;
	
	
	public void tick() {

		HEALTH = Game.clamp(HEALTH, 0, 100 + bounds/2);
		greenValue = (int) (HEALTH*2);
		greenValue = (int) Game.clamp(greenValue, 0, 255);
		score ++;
		gold ++;
		
	}
	
	public void render(Graphics g) {
		
		//Display Health bar
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200 + bounds, 32);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, (int) (HEALTH * 2), 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200 + bounds, 32);
		//Score level etc.
		g.drawString("Score: " + score + " Gold: " + gold, 15, 64);
		g.drawString("Level: "+ level, 15, 80);
		g.drawString("B key for Shop", 15, 94);
	}

	public void score(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void gold(int gold) {
		this.gold = gold;
	}
	
	public int getGold() {
		return gold;
	}
	
	public void level(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}

	public void bounds(int bounds) {
		this.bounds = bounds;
		
	}
	
}