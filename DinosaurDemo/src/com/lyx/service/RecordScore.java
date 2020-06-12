package com.lyx.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class RecordScore {
	
	public static int[] scores = new int[3];
	private static final String PATH ="resource/score/score.txt";
	
	public static void init(){
		File file = new File(PATH);
		if(!file.exists()){	//如果文件不存在，就创建一个新文件
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		String values = "";
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(file);	//字节流
			isr = new InputStreamReader(fis);	//字节流转字符流
			br = new BufferedReader(isr);
			values = br.readLine();
			if(!(values == null || "".equals(values))){
				String vs[] = values.split(",");
				for(int i=0; i<3; i++){
					scores[i] = Integer.parseInt(vs[i]);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void scoreRecord(){
		String values = scores[0] + "," + scores[1] + "," + scores[2];
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		
		try {
			fos = new FileOutputStream(PATH);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);
			bw.write(values);
			bw.flush();//刷新流,强制将数据发送出去
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				osw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void addNewScore(int newScore){
		int[] temp = Arrays.copyOf(scores, 4);
		temp[3] = newScore;	//最后一个位置存放新分数
		Arrays.sort(temp);//进行升序排序
		scores = Arrays.copyOfRange(temp, 1, 4);//取下标从1到3
	}
	
	public static int[] getScores(){
		return scores;
	}
	
}
