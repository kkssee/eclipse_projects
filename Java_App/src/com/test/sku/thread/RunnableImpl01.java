package com.test.sku.thread;

public class RunnableImpl01 implements Runnable {

	@Override
	public void run() {
		Thread mt = Thread.currentThread();
		String tname = mt.getName();
		for(int i = 0; i < 5; i++) {
			System.out.println(tname + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(tname+" dead");
	}
	
}
