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
		if(!file.exists()){	//����ļ������ڣ��ʹ���һ�����ļ�
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
			fis = new FileInputStream(file);	//�ֽ���
			isr = new InputStreamReader(fis);	//�ֽ���ת�ַ���
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
			bw.flush();//ˢ����,ǿ�ƽ����ݷ��ͳ�ȥ
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
		temp[3] = newScore;	//���һ��λ�ô���·���
		Arrays.sort(temp);//������������
		scores = Arrays.copyOfRange(temp, 1, 4);//ȡ�±��1��3
	}
	
	public static int[] getScores(){
		return scores;
	}
	
}
