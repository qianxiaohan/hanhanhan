package com.lyx.service;

import java.io.FileNotFoundException;


public class SoundServer {
	
	static final String DIR = "music/"; 
	static final String HIT = "hit.wav";
	static final String JUMP = "jump.wav";
	static final String BGM = "background.wav";
	
	//������Ծ������
	public static void playJump(){
		play(DIR + JUMP);
	}
	
	//������ײ������
	public static void playHit(){
		play(DIR + HIT);
	}
	
	//���ű�������
	public static void playBGM(){
		play(DIR + BGM, true);
	}
	
	public static void stop(){
		
	}
	
	public static void play(String filePath){//Ĭ�ϲ�ѭ������
		MusicPlayer player;
		try {
			player = new MusicPlayer(filePath);
			player.play();//��������
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public static void play(String filePath, boolean loop){//ѭ������
		MusicPlayer player;
		try {
			player = new MusicPlayer(filePath, loop);
			player.play();//��������
			player.interrupt();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
