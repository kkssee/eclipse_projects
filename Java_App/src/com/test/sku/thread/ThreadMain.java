package com.test.sku.thread;

public class ThreadMain {
	public static void main(String[] args) {
		/* Thread: Virtual CPU(VCPU)
		 * MultiTasking: 
		 * Chat: 이용자가 메시지를 네트워크로 출력, 다른 컴에서 메시지를 입력해열
		 * Thread는 VCPU이므로 지정된 코드를 할당하여 실행할 수 있어열
		 * Thread는 VCPU이므로 자신만의 Stack을 가지고 지역변수를 생성해열
		 * Thread에게는 Runnable한 코드를 부여하여 실행하게 해열
		 * Thread가 Runnable한 코드는 Runnable 인터페이스 구현체여야 해열
		 */
		//threadTest01();
		//threadTest02();
		
		Counter cnt = new Counter();
		Producer prod = new Producer(cnt);
		Consumer cons = new Consumer(cnt);
		prod.start();
		cons.start();
		
		Thread mt = Thread.currentThread();
		String tname = mt.getName();
		System.out.println(tname + " end");
	} // end of main()
	
	private static void threadTest01() {	// 병행처리
		Thread t1 = new Thread(new RunnableImpl01(), "add num thread");
		t1.start();
		
		Thread t2 = new Thread(new Run2(), "date thread");
		t2.start();
	}
	
	private static void threadTest02() {
		NumThread nt = new NumThread("num thread");
		DateThread dt = new DateThread("date thread");
		
		nt.start();
		dt.start();
	}
	
	
}
