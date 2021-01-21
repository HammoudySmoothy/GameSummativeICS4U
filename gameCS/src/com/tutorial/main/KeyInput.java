package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.tutorial.main.Game.STATE;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	private Game game;
	
	private FaceTracker faceTracker;
	
	public KeyInput(Handler handler, Game game, FaceTracker faceTracker) {
		this.game = game;
		this.handler = handler;
		this.faceTracker = faceTracker;
		
		for(int i =0; i< keyDown.length; i++) {
			keyDown[i] = false;
		}
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getID() == ID.Player && !game.isFaceTrackOn){
				//key events for player 1
				if(key == KeyEvent.VK_W) {tempObject.setVelY(-handler.speed); keyDown[0] = true;}
				if(key == KeyEvent.VK_S) {tempObject.setVelY(handler.speed); keyDown[1] = true;}
				if(key == KeyEvent.VK_D) {tempObject.setVelX(handler.speed); keyDown[2] = true;}
				if(key == KeyEvent.VK_A) {tempObject.setVelX(-handler.speed); keyDown[3] = true;}
				
			}
			if (tempObject.getID() == ID.Player && game.isFaceTrackOn) {
				//Setting face traker coords to object
				tempObject.x = faceTracker.getX()*game.WIDTH/320;
				tempObject.y = faceTracker.getY()*game.HEIGHT/240;			}
		
		}
		
		if(key == KeyEvent.VK_P) {
			if(game.gameState == STATE.Game) {
				if(Game.paused) Game.paused = false;
				else Game.paused = true;
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		if(key == KeyEvent.VK_B) {
			if(Game.gameState == STATE.Game) {
				Game.gameState = STATE.Shop;
				Game.paused = true;
			}
			else if(Game.gameState == STATE.Shop) {
				Game.gameState = STATE.Game;
				Game.paused = false;
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getID() == ID.Player){
				//key events for player 1
				if(key == KeyEvent.VK_W) keyDown[0] = false;;
				if(key == KeyEvent.VK_S) keyDown[1] = false;
				if(key == KeyEvent.VK_D) keyDown[2] = false;
				if(key == KeyEvent.VK_A) keyDown[3] = false;
				
				//vert movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				//horiz movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
				
			}
		
		}
	}
	
}
