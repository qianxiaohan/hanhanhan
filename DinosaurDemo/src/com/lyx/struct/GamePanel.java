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
//��Ϸ���
public class GamePanel extends JPanel implements KeyListener{
	private static final long serialVersionUID = 1L;
	private BackgroundImage background;	//����ͼƬ
	private BufferedImage image;
	private Dinosaur dinosaur;
	private Graphics2D g2;
	private int addBlockTimer; //����ϰ����ʱ��
	private int scoreTimer;	//����ʱ�䣬����һ��ʱ�����ö�Ӧ�ķ���
	private List<Block> list = new ArrayList<Block>();	//����һ������
	private FreshThread thread = new FreshThread(this);
	private final int FRESH = FreshThread.FRESH;
	private int score;	//����
	public boolean gameOver = false;
	
	public GamePanel(){
		addBlockTimer = 0;
		dinosaur = new Dinosaur();
		list.add(new Block());//Ĭ�����һ���ϰ������
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
			if(Math.random()*100 > 40){	//60%�ĸ��ʳ����µ��ϰ���
				list.add(new Block());
			}
			addBlockTimer = 0;
		}
		
		for(int i=0; i<list.size();i++){
			Block b = list.get(i);
			if(b.isLive()){//����ϰ����������仭����
				b.move();
				g2.drawImage(b.image, b.x, b.y, this);
				//�߽���ײ����
				if(b.getBounds().intersects(dinosaur.getFootBounds())
						||b.getBounds().intersects(dinosaur.getHeadBounds())){
					gameOver();
				}
			}else{
				list.remove(i);	//������󲻴��ڣ������Ƴ�����
				i--;
			}
		}
		
		g2.setFont(new Font("����",Font.BOLD,25));
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
	
	public void gameOver(){//��Ϸ����ʱ������·���
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
