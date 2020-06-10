package lyx.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class TypePanel extends JPanel implements Runnable,KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	private final static int len=10;
	
	private char[] ch = new char[10];
	private int[] x = new int[10];
	private int[] y = new int[10];
	private int score=0;
	//字体颜色数组
	private Color[] color = new Color[10];
	
	TypePanel(){
		init();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.setFont(new Font("黑体.ttf",Font.BOLD,20));
		g.setColor(Color.red);
		Integer I = new Integer(score);
		g.drawString("Score:"+I.toString(), 10, 30);
		for(int i=0;i<10;i++){
			g.setColor(color[i]);
			g.drawString((new Character(ch[i]).toString()),x[i],y[i]);
		}
	}

	@Override
	public void run() {
		while(true){
			for(int i=0;i<len;i++){
				y[i]++;
				if(y[i]>470){
					ch[i] = (char)(Math.random()*26+65);
					x[i] = (int)(Math.random()*300);
					y[i] = 0;
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
			}
		}
	}
	
	private static Color getColorCode(){
		int r = (int)(Math.random()*255);
		int g = (int)(Math.random()*255);
		int b = (int)(Math.random()*255);
		Color c = new Color(r, g, b);
		return c;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		for(int i=0;i<len;i++){
			if(key == (int)ch[i]){
				score += 100;
				ch[i] = initChar();
				y[i] = 0;
				x[i] = initX();
				repaint();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private char initChar(){
		return (char)(Math.random()*26+65);
	}
	
	private int initX(){
		return (int)(Math.random()*270+100);
	}
	
	private int initY(){
		return (int)(Math.random()*400);
	}
	
	private void init(){
		for(int i=0;i<len;i++){
			ch[i] = initChar();
			x[i] = initX();
			y[i] = initY();
			color[i] = getColorCode();
		}
	}
}
