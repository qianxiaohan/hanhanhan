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
	private boolean circulate;	//�ж��Ƿ�ѭ����������
	public boolean isStop;
	File file;
	
	
	/**
	 * @param path �ļ�·��
	 */
	public MusicPlayer(String path)throws FileNotFoundException{
		this(path, false);
	}
	
	/**
	 * @param path �ļ�·��
	 * @param loop �Ƿ�ѭ���ı�־
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
		byte[] buffer = new byte[1024 * 128];	//128k�Ļ�����
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
						auline.write(buffer, 0, count);	//���ֽ���д���Ƶ��
					}
				}
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}finally{
				auline.drain();	//���������
				auline.close();	//�ر�������
			}
		}while(circulate);
	}
}
