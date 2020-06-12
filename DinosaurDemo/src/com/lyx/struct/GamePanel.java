package com.lyx.struct;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.lyx.modle.*;
import com.lyx.service.FreshThread;
import com.lyx.service.RecordScore;
import com.lyx.src.BackgroundImage;
//游戏面板
public class GamePanel extends JPanel implements KeyListener{
	private static final long serialVersionUID = 1L;
	private BackgroundImage background;	//背景图片
	private BufferedImage image;
	private Dinosaur dinosaur;
	private Graphics2D g2;
	private int addBlockTimer; //添加障碍物的时间
	private int scoreTimer;	//分数时间，超过一定时间则获得对应的分数
	private List<Block> list = new ArrayList<Block>();	//定义一个集合
	private FreshThread thread = new FreshThread(this);
	private final int FRESH = FreshThread.FRESH;
	private int score;	//分数
	public boolean gameOver = false;
	
	public GamePanel(){
		addBlockTimer = 0;
		dinosaur = new Dinosaur();
		list.add(new Block());//默认添加一个障碍物对象
		background = new BackgroundImage();
		image = new BufferedImage(800, 300, BufferedImage.TYPE_INT_BGR);
		g2 = image.createGraphics();
		thread.start();
	}
	
	public void paintImage(){
		dinosaur.move();
		background.roll();
		g2.drawImage(background.image, 0, 0, this);
		g2.drawImage(dinosaur.image, dinosaur.x, dinosaur.y, this);
		if(addBlockTimer >= 1300){
			if(Math.random()*100 > 40){	//60%的概率出现新的障碍物
				list.add(new Block());
			}
			addBlockTimer = 0;
		}
		
		for(int i=0; i<list.size();i++){
			Block b = list.get(i);
			if(b.isLive()){//如果障碍物对象存活，则将其画出来
				b.move();
				g2.drawImage(b.image, b.x, b.y, this);
				//边界碰撞测试
				if(b.getBounds().intersects(dinosaur.getFootBounds())
						||b.getBounds().intersects(dinosaur.getHeadBounds())){
					gameOver();
				}
			}else{
				list.remove(i);	//如果对象不存在，则将其移除集合
				i--;
			}
		}
		
		g2.setFont(new Font("黑体",Font.BOLD,25));
		g2.setColor(Color.BLACK);
		g2.drawString("score:"+score, 600, 40);
		
		if(scoreTimer >= 1000){
			score += 20;
			scoreTimer = 0;
		}
		addBlockTimer += FRESH;
		scoreTimer += FRESH;
	}
	
	public void paint(Graphics g){
		paintImage();
		g.drawImage(image, 0, 0, this);
	}
	
	public void restart(){
		repaint();
	}
	
	public void gameOver(){//游戏结束时，添加新分数
		RecordScore.addNewScore(score);
		gameOver = true;
	}
	
	public int getScore(){
		return score;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP){
			dinosaur.jump();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
}
