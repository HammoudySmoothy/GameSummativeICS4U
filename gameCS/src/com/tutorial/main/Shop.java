package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter{
	
	Handler handler;
	HUD hud;
	
	public static int B1 = 100;
	public static int B2 = 100;
	private int B3 = 300;
	
	public Shop(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 0, 48));
		g.drawString("SHOP", Game.WIDTH/2-100, 50);
		
		//Box 1
		g.setFont(new Font("Arial", 0, 12));
		g.drawString("Upgrade Health", 260, 120);
		g.drawString("Cost: " + B1, 260, 140);
		g.drawRect(250, 100, 100, 80);
		
		//Box 2
		g.drawString("Upgrade Speed", 410, 120);
		g.drawString("Cost: " + B2, 410, 140);
		g.drawRect(400, 100, 100, 80);
				
		//Box 3
		g.drawString("Refill Health", 110, 120);
		g.drawString("Cost: " + B3, 110, 140);
		g.drawRect(100, 100, 100, 80);
		
		g.drawString("Gold: " + hud.getGold(), Game.WIDTH/2-50, 300);
		g.drawString("Press B to Leave", Game.WIDTH/2-50, 330);
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//box 1
		if(mx >= 250 && mx<= 350) {
			if(my >=100 && my <=180) {
				if(hud.getGold() >= B1) {
					hud.gold(hud.getGold() - B1);
					B1+= 100;
					hud.bounds += 20;
					hud.HEALTH = 100 + hud.bounds/2;
				}
			}
		}
		
		//box2
		if(mx >= 400 && mx<= 500) {
			if(my >=100 && my <=180) {
				if(hud.getGold() >= B2) {
					hud.gold(hud.getGold() - B2);
					B2+= 100;
					handler.speed++;
				}
				
			}
		}
		
		//box 3
		if(mx >= 100 && mx<= 150) {
			if(my >=100 && my <=180) {
				if(hud.HEALTH < 100 + hud.bounds/2) {
					if(hud.getGold() >= B3) {
						hud.gold(hud.getGold() - B3);
						hud.HEALTH = 100 + hud.bounds/2;
					}
				}
				
			}
		}
		
		
	}

}
