package com.test.sku.thread;

public class NumThread extends Thread {
	public NumThread(String name) {
		super(name);
	}
	@Override
	public void run() {
		String tn = Thread.currentThread().getName();
		for (int i = 0; i < 5; i++) {
			System.out.println(tn + i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
