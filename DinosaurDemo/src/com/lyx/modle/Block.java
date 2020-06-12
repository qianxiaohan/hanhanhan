package com.lyx.modle;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.lyx.src.BackgroundImage;

public class Block {
	public BufferedImage image;
	private BufferedImage stone,cactus;
	public int x,y;
	public int speed;
	
	public Block(){
		try {
			stone = ImageIO.read(new File("image/石头.png"));
			cactus = ImageIO.read(new File("image/仙人掌.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Random rand = new Random();
		if(rand.nextInt(2) == 0){
			image = stone;
		}else{
			image = cactus;
		}
		x = 800;
		y = 200 - image.getHeight();
		speed = BackgroundImage.SPEED;
	}
	
	public void move(){
		x -= speed;
	}
	
	public boolean isLive(){	//如果图片的横坐标小于0，则返回false
		if(x <= -image.getWidth()){
			return false;
		}else
			return true;
	}
	
	public Rectangle getBounds(){
		if(image == stone)
			return new Rectangle(x + 5, y + 4, 23, 21);
		else
			return new Rectangle(x + 7, y, 16, image.getHeight());
	}
}
