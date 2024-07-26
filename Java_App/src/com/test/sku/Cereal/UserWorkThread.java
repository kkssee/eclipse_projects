package com.test.sku.Cereal;

import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.util.*;

public class UserWorkThread extends Thread {
	Socket s;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	FileInfo fi = new FileInfo();
	List<FileInfo> list = new ArrayList<>();
	
	public UserWorkThread() { }
	public UserWorkThread(Socket s) { 
		this.s = s;
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				DocVO dv = new DocVO("업로드(a) 목록(s) 검색(f)\n"
						+ "수정(u) 삭제(d) 종료(x)");
				oos.writeObject(dv);
				oos.flush();
				
				dv = (DocVO)ois.readObject();
				
				if(dv.upload) {
					fi.fname = dv.fname;
					fi.fdata = dv.fdata;
					fi.fauth = dv.fauth;
					fi.fdesc = dv.fdesc;
					DocIO.download(fi);
					fi.fdate = LocalDate.now();
					list.add(fi);
					DocIO.serialize(list);
				} else if(dv.list) {
					oos.writeObject(DocIO.deserialize());
					oos.flush();
				} else if(dv.find) {
					list = DocIO.deserialize();
					
				} else if(dv.update) {
					
				} else if(dv.delete) {
					
				} else if(dv.exit) {
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // end of run()
}
