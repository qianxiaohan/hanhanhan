package com.lyx.struct;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.lyx.service.RecordScore;
import com.lyx.service.SoundServer;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public MainFrame(){
		restart();
		setTitle("Dinosaur Game");
		SoundServer.playBGM();
		RecordScore.init();//初始化分数
		addListener();
		setBounds(150, 150, 800, 300);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void restart(){
		Container c = getContentPane();
		c.removeAll();
		GamePanel panel = new GamePanel();
		c.add(panel);
		addKeyListener(panel);
		c.validate();
	}
	
	public void addListener(){
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				RecordScore.scoreRecord();//窗口关闭前，保存分数
			}
		});
	}
}
