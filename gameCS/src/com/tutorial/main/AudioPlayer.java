package com.tutorial.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {

	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load() {
		
		try {
			soundMap.put("buttonClick", new Sound("ressources/ButtonCLick.ogg"));
			
			soundMap.put("punch", new Sound("ressources/FistPunch.ogg"));
			
			soundMap.put("wallHit", new Sound("ressources/BodyWall Impact.ogg"));
			
			musicMap.put("music", new Music("ressources/GamingBackgroundMusic.ogg"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
}
