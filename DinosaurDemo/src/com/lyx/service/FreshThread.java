package com.lyx.service;

import java.awt.Container;

import com.lyx.struct.GamePanel;
import com.lyx.struct.MainFrame;
import com.lyx.struct.ScoreDialog;

public class FreshThread extends Thread{
	public static final int FRESH = 15;
	GamePanel p;
	MainFrame frame;
	
	public FreshThread(GamePanel p){
		this.p = p;
	}
	
	public void run(){
		while(!p.gameOver){
			p.repaint();
			try {
				Thread.sleep(FRESH);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Container c = p.getParent(); //获取游戏面板的父容器
		while(!(c instanceof MainFrame)){
			c = c.getParent();
		}
		MainFrame frame = (MainFrame)c;
		new ScoreDialog(frame);
		frame.restart();
	}
}
