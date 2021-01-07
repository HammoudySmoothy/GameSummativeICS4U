package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	//renders and refreshes the screen
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for (int i = 0; i< object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i< object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject obj) {
		this.object.add(obj);
	}
	
	public void removeObject(GameObject obj) {
		this.object.remove(obj);
	}
}
