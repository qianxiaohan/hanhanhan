package com.lyx.struct;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.lyx.service.RecordScore;

public class ScoreDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	int scores[] = RecordScore.getScores();
	JLabel first;
	JLabel second;
	JLabel third;
	JLabel title;
	JButton button;
	
	public ScoreDialog(JFrame frame){
		super(frame, true);//阻塞父窗体，即出现对话框时，游戏结束
		Container c = getContentPane();
		setLayout(new GridLayout(5,1));
		title = new JLabel("得分排行榜", JLabel.CENTER);
		first = new JLabel("第一名："+scores[2], JLabel.CENTER);
		second = new JLabel("第二名："+scores[1], JLabel.CENTER);
		third = new JLabel("第三名："+scores[0], JLabel.CENTER);
		button = new JButton("重新开始");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		title.setFont(new Font("黑体",Font.BOLD,20));
		title.setForeground(Color.red);
		
		c.add(title);
		c.add(first);
		c.add(second);
		c.add(third);
		c.add(button);
		
		
		int width,height; 
		width = height = 250;
		int x = frame.getX() + (frame.getWidth() - width) / 2;
		int y = frame.getY() + (frame.getHeight() - height) / 2;
		setBounds(x, y, width, height);
		setVisible(true);
	}
}
