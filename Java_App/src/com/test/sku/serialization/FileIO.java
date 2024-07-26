package com.test.sku.serialization;

import java.io.*;
import java.util.*;

public class FileIO {
	static File file;
	static Byte[] fdata;
	static String downPath = "C:/test/downloads/";
	static String upPath = "C:/test/uploads/";
	static String sPath = "C:/test/server/";
	
	static List<FileVO> list = new ArrayList<>();
	
	public FileIO() { }
	
	public static byte[] read(String fname) {
		File file = new File(upPath+fname);
		try {
			FileInputStream fin = new FileInputStream(file);
			byte[] fdata = new byte[(int)file.length()+1];
			fin.read(fdata);
			fin.close();
			return fdata;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void download(String path, String fname, byte[] fdata) {
		File file = new File(downPath + fname);
        FileOutputStream fout;
		try {
			fout = new FileOutputStream(file);
			 fout.write(fdata);
			 fout.flush();
		     System.out.println("다운로드 완료");
		     fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void upload(FileVO fv) {
		download(sPath, fv.fname, fv.fdata);
		
	}
	
	
	private static void Serialize() { //직렬화 메소드
		List<String> names = new ArrayList<>();
		names.add("jane");
		names.add("john");
		names.add("smith");
		names.add("julie");
		names.add("sean");
		names.add("robert");
		
		try {
			FileOutputStream fout = new FileOutputStream(sPath);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(names);
			oos.close();
			System.out.println("저장 완료");
		} catch (Exception e) {
			System.err.println("저장 실패");
			e.printStackTrace();
		} 
	}
	
}
