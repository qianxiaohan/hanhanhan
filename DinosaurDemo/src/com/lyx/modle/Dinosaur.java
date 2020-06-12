package com.lyx.modle;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.lyx.service.FreshThread;
import com.lyx.service.SoundServer;

public class Dinosaur {
	public int x, y;	//坐标
	public BufferedImage image;
	public boolean jumpStage = false;	//默认没有起跳
	private int jumpValue;
	private BufferedImage image1, image2, image3;
	private int stepTimer;
	private final int LOWEST_Y = 120;
	private final int JUMP_HIGHT = 100;
	private static final int FRESH = FreshThread.FRESH;
	
	public Dinosaur(){
		x = 50;//恐龙默认是横坐标在50像素
		y = LOWEST_Y;

		try {
			image1 = ImageIO.read(new File("image/恐龙1.png"));
			image2 = ImageIO.read(new File("image/恐龙2.png"));
			image3 = ImageIO.read(new File("image/恐龙3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void step(){
		int temp = (stepTimer / 250) % 3;	//3张恐龙图片来回切换
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
	
	//跳跃
	public void jump(){
		if(!jumpStage){
			SoundServer.playJump();
		}
		jumpStage = true;
	}
	
	//移动恐龙
	public void move(){
		step();
		if(jumpStage){	//处于跳跃状态
			if(y >= LOWEST_Y){
				jumpValue = -4;
			}
			if(y <= LOWEST_Y - JUMP_HIGHT){	//处于最高点时
				jumpValue = 4;
			}
			y += jumpValue;
			if(y >= LOWEST_Y){
				jumpStage = false;
			}
		}
	}
	
	//获取恐龙头的位置
	public Rectangle getHeadBounds(){
		return new Rectangle(x + 66, y + 25, 32, 22);
	}
	
	//获取恐龙脚的位置
	public Rectangle getFootBounds(){
		return new Rectangle(x + 30, y + 59, 30, 18);
	}
}
