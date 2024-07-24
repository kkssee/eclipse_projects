package com.test.sku.serialization;

import java.io.*;

public class FileIO {
	static File file;
	static Byte[] fdata;
	static String downPath = "C:/test/downloads/";
	static String upPath = "C:/test/uploads/";
	
	public FileIO() { }
	
	public static byte[] attachFile(String fname) {
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
	
	public static void upload(String fname) {
		
		File file = new File(upPath+fname);

		try {
			FileInputStream fin = new FileInputStream(file);
			byte[] fdata = new byte[(int)file.length()+1];
			fin.read(fdata);
			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		file = new File(downPath + fname);
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
}
