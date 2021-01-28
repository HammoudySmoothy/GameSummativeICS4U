package com.tutorial.main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1550691097823471818L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	public static boolean paused = false;
	public int dif = 0;
	
	public static int score = 0;
	
	//0 is normal
	//1 is hard
	
	public boolean isFaceTrackOn = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;
	private FaceTracker faceTracker;
	
	public enum STATE{
		Menu(),
		Game(),
		Help(),
		Shop(),
		End(),
		Select()
	};
	
	public static STATE gameState = STATE.Menu;
	
	public Game() throws IOException {
		
		//Create Objects
		handler = new Handler();
		hud = new HUD();
		shop = new Shop(handler, hud);
		faceTracker = new FaceTracker();
		menu = new Menu(this, handler, hud, faceTracker);
		
		//Add Listeners that will respond to mouse and key inputs
		this.addKeyListener(new KeyInput(handler, this, faceTracker));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		//Load music
		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop();
		
		//Create a new window
		new Window(WIDTH, HEIGHT, "Super Square", this);
		
		//Objects...
		spawner = new Spawn(handler, hud, this);
		r = new Random();
		
		//Spawn first objects, and background particles
		if(gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
		}else {
			for(int i = 0; i< 20; i++){
				handler.addObject(new MenuParticle(r.nextInt(WIDTH)-52, r.nextInt(HEIGHT)-52, ID.MenuParticle, handler));
			}
		}
		
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		//gets screen focus to window
		this.requestFocus();
		
		//Game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			this.requestFocus();
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >=1) {
				tick();
				delta--;
			}
			if(running) 
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer >1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		//Refreshes objects every frame
		
		if(!paused) {
			handler.tick();
			if(gameState == STATE.Game) {
				hud.tick();
				spawner.tick();
				
				if(isFaceTrackOn) {
					faceTracker.tick();
				}
				
				if(HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					gameState = STATE.End;
					handler.clearEnemies();
					 try {
					        BufferedReader reader = new BufferedReader(new FileReader("ressources/Highscore"));
					        String line = reader.readLine();
					        while (line != null)                 // read the score file line by line
					        {
					            try {
					                score = Integer.parseInt(line.trim());   // parse each line as an int
					                if (score > Menu.highscore)              // and keep track of the largest
					                { 
					                	Menu.highscore = score; 
					                }
					            } catch (NumberFormatException e1) {
					                // ignore invalid scores
					                //System.err.println("ignoring invalid score: " + line);
					            }
					            line = reader.readLine();
					        }
					        reader.close();

					    } catch (IOException ex) {
					        System.err.println("ERROR reading scores from file");
					    }
					 
					 try {
					        BufferedWriter output = new BufferedWriter(new FileWriter("ressources/Highscore", true));
					        output.newLine();
					        output.append("" + hud.getScore());
					        output.close();

					    } catch (IOException ex1) {
					        System.out.printf("ERROR writing score to file: %s\n", ex1);
					    }
									
					for(int i = 0; i< 20; i++){
						handler.addObject(new MenuParticle(r.nextInt(WIDTH)-52, r.nextInt(HEIGHT)-52, ID.MenuParticle, handler));
					}
				}
			}
		}
		
		else if(gameState == STATE.Menu||gameState == STATE.End||gameState == STATE.Select){
			menu.tick();
			handler.tick();
		}
		
	}
	
	private void render() {
		//Renders graphics every frame 
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs==null) {
			this.createBufferStrategy(3);;
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		
		if(paused) {
			g.setColor(Color.white);
			g.drawString("PAUSED..", 550, 40);
		}
		
		if(gameState == STATE.Game) {
			hud.render(g);
			handler.render(g);
		}
		
		else if(gameState == STATE.Menu|| gameState == STATE.Help||gameState == STATE.End||gameState == STATE.Select){
			menu.render(g);
			handler.render(g);
			
		}else if (gameState == STATE.Shop) {
			shop.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float x, float min, float max) {
		// sets limit on how much a value can increase or decrease
		
		if(x>= max) return x = max;
		else if (x<= min) return x = min;
		else return x;
	}
	
	public static void main(String args[]) {
	
		try {
			new Game();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
