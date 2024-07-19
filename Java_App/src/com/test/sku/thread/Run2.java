package com.test.sku.thread;

import java.util.Date;

public class Run2 implements Runnable {
	@Override
	public void run() {
		Thread mt = Thread.currentThread();
		String tname = mt.getName();
		for(int i = 0; i < 5; i++) {
			System.out.println(tname + new Date());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(tname+" dead");
	}
	
}
