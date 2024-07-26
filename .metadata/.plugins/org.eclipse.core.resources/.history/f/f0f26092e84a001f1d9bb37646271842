package com.test.sku.serialization;

import java.io.*;
import java.net.*;

public class DocServer {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(4854);
			while(true) {
				System.out.println("서버 대기중");
				Socket s = ss.accept();
				System.out.println("클라이언트 접속");
				
				new UserWorkThread(s).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("서버 종료");
	}	// end of main

}
