package com.test.sku.thread;

import java.util.Date;

public class DateThread extends Thread {
	public DateThread(String name) {
		super(name);
	}
	@Override
	public void run() {
		String tn = Thread.currentThread().getName();
		for (int i = 0; i < 5; i++) {
			System.out.println(tn + new Date());
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
