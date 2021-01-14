package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.tutorial.main.Game.STATE;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	private Game game;
	
	public KeyInput(Handler handler, Game game) {
		this.game = game;
		this.handler = handler;
		
		for(int i =0; i< keyDown.length; i++) {
			keyDown[i] = false;
		}
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getID() == ID.Player){
				//key events for player 1
				if(key == KeyEvent.VK_W) {tempObject.setVelY(-handler.speed); keyDown[0] = true;}
				if(key == KeyEvent.VK_S) {tempObject.setVelY(handler.speed); keyDown[1] = true;}
				if(key == KeyEvent.VK_D) {tempObject.setVelX(handler.speed); keyDown[2] = true;}
				if(key == KeyEvent.VK_A) {tempObject.setVelX(-handler.speed); keyDown[3] = true;}
				
			}
			
			if (game.isFaceTrackOn) {
				//Write inputs here....
			}
		
		}
		
		if(key == KeyEvent.VK_P) {
			if(game.gameState == STATE.Game) {
				if(Game.paused) Game.paused = false;
				else Game.paused = true;
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		if(key == KeyEvent.VK_SPACE) {
			if(Game.gameState == STATE.Game) Game.gameState = STATE.Shop;
			else if(Game.gameState == STATE.Shop) Game.gameState = STATE.Game;
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getID() == ID.Player){
				//key events for player 1
				if(key == KeyEvent.VK_W) keyDown[0] = false;//tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) keyDown[1] = false; //tempObject.setVelY(0);
				if(key == KeyEvent.VK_D) keyDown[2] = false;//tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) keyDown[3] = false;//tempObject.setVelX(0);
				
				//vert movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				//horiz movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
				
			}
		
		}
	}
	
}
