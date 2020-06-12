package com.lyx.modle;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.lyx.service.FreshThread;
import com.lyx.service.SoundServer;

public class Dinosaur {
	public int x, y;	//����
	public BufferedImage image;
	public boolean jumpStage = false;	//Ĭ��û������
	private int jumpValue;
	private BufferedImage image1, image2, image3;
	private int stepTimer;
	private final int LOWEST_Y = 120;
	private final int JUMP_HIGHT = 100;
	private static final int FRESH = FreshThread.FRESH;
	
	public Dinosaur(){
		x = 50;//����Ĭ���Ǻ�������50����
		y = LOWEST_Y;

		try {
			image1 = ImageIO.read(new File("image/����1.png"));
			image2 = ImageIO.read(new File("image/����2.png"));
			image3 = ImageIO.read(new File("image/����3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void step(){
		int temp = (stepTimer / 250) % 3;	//3�ſ���ͼƬ�����л�
		switch (temp) {
		case 1:
			image = image1;
			break;
		case 2:
			image = image2;
			break;
		default:
			image = image3;
			break;
		}
		stepTimer += FRESH;
	}
	
	//��Ծ
	public void jump(){
		if(!jumpStage){
			SoundServer.playJump();
		}
		jumpStage = true;
	}
	
	//�ƶ�����
	public void move(){
		step();
		if(jumpStage){	//������Ծ״̬
			if(y >= LOWEST_Y){
				jumpValue = -4;
			}
			if(y <= LOWEST_Y - JUMP_HIGHT){	//������ߵ�ʱ
				jumpValue = 4;
			}
			y += jumpValue;
			if(y >= LOWEST_Y){
				jumpStage = false;
			}
		}
	}
	
	//��ȡ����ͷ��λ��
	public Rectangle getHeadBounds(){
		return new Rectangle(x + 66, y + 25, 32, 22);
	}
	
	//��ȡ�����ŵ�λ��
	public Rectangle getFootBounds(){
		return new Rectangle(x + 30, y + 59, 30, 18);
	}
}
