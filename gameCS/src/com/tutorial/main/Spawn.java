package com.tutorial.main;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private int scoreKeep = 0;
	private Random r = new Random();
	private Game game;
	
	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	
	public void tick() {
		scoreKeep++;
		
		if (scoreKeep >= 100) {
			scoreKeep = 0;
			hud.level(hud.getLevel()+1); 
			
			if(hud.getLevel()<=1) {
				if(game.dif == 0) handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				else handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			}
			else if(hud.getLevel() == 2){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
			}
			else if(hud.getLevel() == 3){
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
			}
			else if(hud.getLevel() == 4){
				if(game.dif == 0) handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				else handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			}
			else if(hud.getLevel() == 5){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
			}
			else if(hud.getLevel() == 6){
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
			}
			else if(hud.getLevel() == 7){
				if(game.dif == 0) handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				else handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			}
			else if(hud.getLevel() == 8){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
			}
			else if(hud.getLevel() == 9){
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
			}
			else if(hud.getLevel() == 10){
				if(game.dif == 0) handler.clearEnemies();
				handler.addObject(new BossEnemy(Game.WIDTH/2 - 36, -120, ID.BossEnemy, handler));
			}else if(hud.getLevel() == 17){
				handler.clearEnemies();
				if(game.dif == 0) handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				else handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));	
			}else if(hud.getLevel() > 17){
				spawnRandomEnemy();
			}
			
		}
		
	}
	
	private void spawnRandomEnemy() {
		int i = r.nextInt(3);
		switch(i) {
			case 1:
				if(game.dif == 0) handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				else handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				break;
			case 2:
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				break;
			case 0:
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
				break;
		}
		
	}
}
