package com.test.sku.thread;

public class Consumer extends Thread {
	
	private Counter counter;
	
	public Consumer(Counter counter) {
		super("consumer");
		this.counter = counter;
	}
	
	@Override
	public void run() {
		while(true) {
			counter.decrease();
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
