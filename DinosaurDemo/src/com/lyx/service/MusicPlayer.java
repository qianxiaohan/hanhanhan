package com.lyx.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;


public class MusicPlayer extends Thread{
	private boolean circulate;	//判断是否循环播放音乐
	public boolean isStop;
	File file;
	
	
	/**
	 * @param path 文件路径
	 */
	public MusicPlayer(String path)throws FileNotFoundException{
		this(path, false);
	}
	
	/**
	 * @param path 文件路径
	 * @param loop 是否循环的标志
	 */
	public MusicPlayer(String path, boolean loop) throws FileNotFoundException{
		file = new File(path);
		circulate = loop;
	}
	
	public void play(){
		this.start();
	}
	
	public void stopBGM(){
		this.interrupt();
	}
	
	public void run(){
		byte[] buffer = new byte[1024 * 128];	//128k的缓冲区
		AudioInputStream ais = null;
		SourceDataLine auline = null;
		do{
			try {
				ais = AudioSystem.getAudioInputStream(file);
				AudioFormat format = ais.getFormat();
				auline = AudioSystem.getSourceDataLine(format);
				auline.open(format);
				auline.start();
				int count = 0;
				while(count != -1){
					count = ais.read(buffer, 0, buffer.length);
					if(count >= 0){
						auline.write(buffer, 0, count);	//将字节数写入混频器
					}
				}
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}finally{
				auline.drain();	//清除数据行
				auline.close();	//关闭数据行
			}
		}while(circulate);
	}
}
