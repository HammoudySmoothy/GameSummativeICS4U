package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	private FaceTracker faceTracker;
	
	public Menu (Game game, Handler handler, HUD hud, FaceTracker faceTracker){
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.faceTracker = faceTracker;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (game.gameState == STATE.Menu) {
			//Select Mode (play)
			if (mouseOver(mx,my, 210,150,200,64)) {
				game.gameState = STATE.Select;
				AudioPlayer.getSound("buttonClick").play();
				return;
			}
			//Quit button
			if (mouseOver(mx,my, 210,350,200,64)) {
				System.out.println("Quit");
				System.exit(1);
			}
			//Help button
			if (mouseOver(mx,my, 210,250,200,64)) {
				game.gameState = STATE.Help;
				AudioPlayer.getSound("buttonClick").play();
				System.out.println("Help");
			}
		}
		
		if (game.gameState == STATE.Select) {
			//Normal Button
			if (mouseOver(mx,my, 210,150,200,64)) {
				game.gameState = STATE.Game;
				handler.clearEnemies();
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				
				game.dif = 0;
				game.isFaceTrackOn = false;

				AudioPlayer.getSound("buttonClick").play();
			}
			//Hard button
			if (mouseOver(mx,my, 210,250,200,64)) {
				game.gameState = STATE.Game;
				handler.clearEnemies();
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				
				game.dif = 1;
				game.isFaceTrackOn = false;

				AudioPlayer.getSound("buttonClick").play();
			}
			
			// ADD FACETRACKING MODE HERE....
			if (mouseOver(mx, my, 420, 250, 200, 64)) {
				game.gameState = STATE.Game;
				handler.clearEnemies();
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				faceTracker.beginTrack();
				game.isFaceTrackOn = true;
				game.dif = 0;

				AudioPlayer.getSound("buttonClick").play();
			}
			// ADD 2 PLAYER MODE HERE
			
			//Back button
			if (mouseOver(mx,my, 210,350,200,64)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("buttonClick").play();
				return;
			}
		}
		
		// back button for help
		if (game.gameState == STATE.Help) {
			if (mouseOver(mx,my, 210,350,200,64)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("buttonClick").play();
				System.out.println("Back");
				return;
			}
		}
		
		//Loss Button
		if (game.gameState == STATE.End) {
			if (mouseOver(mx,my, 220,350,200,64)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("buttonClick").play();
				System.out.println("Try Again");
				hud.level(0);
				hud.score(0);
			}
		}
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		
		if(mx > x && mx < x + width) {
			if(my> y && my < y + height) {
				return true;
			}else return false;
		}else return false;

	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Super Square", 150, 70);
			
			g.setFont(fnt2);
			
			
			g.setColor(Color.white);
			g.drawRect(210,150, 200, 64);
			g.drawString("Play", 270, 190);
			
			g.setColor(Color.white);
			g.drawRect(210,250, 200, 64);
			g.drawString("Help", 270, 290);
			
			g.setColor(Color.white);
			g.drawRect(210,350, 200, 64);
			g.drawString("Quit", 270, 390);
		}
		else if(game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 240, 70);
			
			g.setFont(fnt3);
			g.drawString("Use WASD keys to move player and dodge enemies", 50, 200);
			g.setFont(fnt2);
			g.drawRect(210,350, 200, 64);
			g.drawString("Back", 270, 390);
		}
		else if(game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 180, 70);
			
			g.setFont(fnt3);
			g.drawString("You lost with a score of: " + hud.getScore(), 185, 210);
			g.setFont(fnt2);
			g.drawRect(220, 350, 200, 64);
			g.drawString("Try Again?", 245, 390);
		}
		else if(game.gameState == STATE.Select) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT MODE", 150, 70);
			
			g.setFont(fnt2);
			
			
			g.setColor(Color.white);
			g.drawRect(210,150, 200, 64);
			g.drawString("Normal", 270, 190);
			
			g.setColor(Color.white);
			g.drawRect(210,250, 200, 64);
			g.drawString("Hard", 270, 290);
			
			g.setColor(Color.white);
			g.drawRect(420,250, 200, 64);
			g.drawString("FaceTracker", 432, 290);
			
			g.setColor(Color.white);
			g.drawRect(210,350, 200, 64);
			g.drawString("Back", 270, 390);
		}
	}

}
