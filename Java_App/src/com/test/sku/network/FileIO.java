package com.test.sku.network;

import java.io.*;

public class FileIO {
	static File file;
	static Byte[] fdata;
	static String savePath = "C:/test/downloads/";
	static String aPath = "C:/test/uploads/";
	
	public FileIO() { }
	
	public static byte[] attachFile(String fname) {
		File file = new File(aPath+fname);

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
	public static void download(String fname, byte[] fdata) {
		File file = new File(savePath + fname);
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
