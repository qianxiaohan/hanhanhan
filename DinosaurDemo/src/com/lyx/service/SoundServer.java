package com.lyx.service;

import java.io.FileNotFoundException;


public class SoundServer {
	
	static final String DIR = "music/"; 
	static final String HIT = "hit.wav";
	static final String JUMP = "jump.wav";
	static final String BGM = "background.wav";
	
	//≤•∑≈Ã¯‘æµƒ“Ù¿÷
	public static void playJump(){
		play(DIR + JUMP);
	}
	
	//≤•∑≈≈ˆ◊≤µƒ“Ù¿÷
	public static void playHit(){
		play(DIR + HIT);
	}
	
	//≤•∑≈±≥æ∞“Ù¿÷
	public static void playBGM(){
		play(DIR + BGM, true);
	}
	
	public static void stop(){
		
	}
	
	public static void play(String filePath){//ƒ¨»œ≤ª—≠ª∑≤•∑≈
		MusicPlayer player;
		try {
			player = new MusicPlayer(filePath);
			player.play();//≤•∑≈“Ù¿÷
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public static void play(String filePath, boolean loop){//—≠ª∑≤•∑≈
		MusicPlayer player;
		try {
			player = new MusicPlayer(filePath, loop);
			player.play();//≤•∑≈“Ù¿÷
			player.interrupt();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
