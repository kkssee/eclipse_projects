package com.test.sku.thread;

public class Producer extends Thread {
	
	private Counter counter;
	
	public Producer(Counter counter) {
		super("producer");
		this.counter = counter;
		this.setPriority(MAX_PRIORITY);
	}
	
	@Override
	public void run() {
		while(true) {
			counter.increase();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
