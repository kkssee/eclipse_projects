package com.test.sku.stream;

import java.io.*;
import java.util.Scanner;

public class BinaryStreamMain 
{
	static Scanner kbd = new Scanner(System.in);
	public static void main(String[] args)
	{
		// Text, None-Text
		/* Character Stream
		 * 	+ Reader, Writer
		 * Binary Stream(Byte Stream)
		 *  + InputStream, OutputStream
		 * 변환 스트림
		 * 	+ InputStreamReader: 바이너리 데이터를 문자 데이터로 변환
		 *  + OutputStreamWriter: 문자 데이터를 바이너리 데이터로 변환
		 *  
		 * 네트워크 통신
		 *  + 문자메시지 --> 바이트 데이터 --> 문자 데이터
		 */
		
		//binaryTest05();
		//conversionTest();
		//conversionTest02();
		conversionTest03();
	}
	
	private static void binaryTest01() {
		String imgPath = "C:/test/data/img/sample.jpg";
		// byte(1), short(2), int(4), long(8)       
		try {
			FileInputStream fin = new FileInputStream(imgPath);
			byte[] imgData = fin.readAllBytes();
			fin.close(); 
			
			String imgTest = "C:/test/data/img/sample_cpy.jpg";
			FileOutputStream fout = new FileOutputStream(imgTest);
			fout.write(imgData);
			fout.close();
			
			System.out.println("Image copied");
		} catch (FileNotFoundException ef) {
			ef.printStackTrace();
		} catch (IOException ei) {
			ei.printStackTrace();
		}
	}
	private static void binaryTest04() {
		String imgPath = "C:/test/data/img/sample.jpg";
		String imgTest = "C:/test/data/img/sample_cpy2.jpg";
		try {
			FileInputStream fin = new FileInputStream(imgPath);
			FileOutputStream fout = new FileOutputStream(imgTest);
			byte[] buf = new byte[256];
			while(true) {
				int cnt = fin.read(buf);	// cnt = 읽어온 개수
				if(cnt == -1) break;
				fout.write(buf, 0, cnt);
			}
			fin.close(); 
			fout.close();
			System.out.println("Image copied");
		} catch (FileNotFoundException ef) {
			ef.printStackTrace();
		} catch (IOException ei) {
			ei.printStackTrace();
		}
	}
	private static void binaryTest05() {
		String imgPath = "C:/test/data/img/sample.jpg";
		String imgTest = "C:/test/data/img/sample_cpy3.jpg";
		try {
			FileInputStream fin = new FileInputStream(imgPath);
			FileOutputStream fout = new FileOutputStream(imgTest);
			// 이미지를 반복하여 읽어서 메모리에 데이터를 누적한다
			// ByteArray
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			byte[] buf = new byte[256];
			//ByteArrayInputStream bin = new ByteArrayInputStream(buf);
			while(true) {
				int cnt = fin.read(buf);	// cnt = 읽어온 개수
				if(cnt == -1) break;
				bout.write(buf, 0, cnt);				
			}
			byte[] img = bout.toByteArray();
			fout.write(img);

			fin.close(); 
			fout.close();
			System.out.println("Image copied");
		} catch (FileNotFoundException ef) {
			ef.printStackTrace();
		} catch (IOException ei) {
			ei.printStackTrace();
		}
	}
	
	private static void conversionTest() {
		// 키보드에서 문자열로 받아서 파일에 저장할 대 바이트 스트림 사용
		// 반복하여 키보드에서 입력을 받고 그 데이터를 파일에 누적
		// 이용자가 그냥 엔터를 치면 입력 완료로 간주하고 그간 누적된 텍파 표시
		String f = "C://test/data/conv.txt";
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(f));
			System.out.println("입력시작");
			while(true) {
				String line  = kbd.nextLine().trim();
				if(line.equals("")) break;
				pw.println(line);
			}
			pw.close();

			BufferedReader br = new BufferedReader(new FileReader(f));
			
			String line = null;
			while(true) {
				line = br.readLine();
				if(line == null) break;
				System.out.println(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void conversionTest02() {
		String fpath = "C:/test/data/members.txt";
		
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(fpath));
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while(true) {
				line = br.readLine();
				if(line == null) break;
				System.out.println(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void conversionTest03() {
		String fpath = "C:/test/data/conv.txt";
		
		try {
			OutputStreamWriter osr = new OutputStreamWriter(new FileOutputStream(fpath));
			PrintWriter pw = new PrintWriter(osr);
			
			System.out.println("입력시작");
			while(true) {
				String line  = kbd.nextLine().trim();
				if(line.equals("")) break;
				pw.println(line);
			}
			pw.close();

			BufferedReader br = new BufferedReader(new FileReader(fpath));
			
			String line = null;
			while(true) {
				line = br.readLine();
				if(line == null) break;
				System.out.println(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
