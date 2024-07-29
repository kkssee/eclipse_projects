package com.test.sku.cmon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class FileIO {
	public static String fpath = "C:/test/";
	private static String savePath = "C:/test/download/";
	public static String path = "list_fileinfo.ser";
	
		public static boolean OkObject(String fname) {
			File f = new File(fpath + fname);      //있는가 없는가 확인 가능. 파일 오브젝트
			if(!f.exists()) {
				System.err.println("지정된 파일이 없습니다");
				return false;
			}
				return true;
		}
				
		public static byte[] upload(String fname) {					
			try {
				FileInputStream fin = new FileInputStream(fpath + fname);
				byte[] imgData = fin.readAllBytes();
				
				fin.close();

				return imgData;
			} catch (Exception e) {
				e.printStackTrace();
		    }
			   return null;						
		}
		
		public static long size(String fname) {
			File f = new File(fpath + fname);
			long len = f.length();			
			return len;
			
		}
		
		public static void serialiaze(List<FileInfo> list) {
			//직열화 
			ObjectOutputStream oos;
			try {
				oos = new ObjectOutputStream(new FileOutputStream(savePath + path));
				oos.writeObject(list);
				oos.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
			
			}
			
		public	static List<FileInfo> deserializalize() {
				//역지열화
				ObjectInputStream ois;
				try {
					ois = new ObjectInputStream(new FileInputStream(savePath + path));
					
					List<FileInfo> list = (List<FileInfo>)ois.readObject();
						
					ois.close();
					return list;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} 
				
			}
		
		
	
}
