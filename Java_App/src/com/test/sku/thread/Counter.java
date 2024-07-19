package com.test.sku.thread;

public class Counter {
	int cnt;
	/* cnt 값이 항상 5-10을 유지하도록 스레드 제어
	 * cnt가 5면 소비 멈추고 생산되어야함
	 * cnt가 10이되면 생산 멈추고 소비
	 * */
	
	public synchronized void increase() {
		System.out.println("before increase: " + cnt);
		cnt++;
		System.out.println("after increase: " + cnt);
		if(cnt>=10) {
			try {
				this.notify();
				this.wait();
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
	public synchronized void decrease() {
		if(cnt<=5) {
			try {
				this.notify();
				this.wait();
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		System.out.println("before decrease: " + cnt);
		cnt--;
		System.out.println("after decrease: " + cnt);
	}
}
