package just.x.lock;

public class LockTest{

	private Lock lock = new Lock();
	
	public static void main(String[] args) {
		
		LockTest lt = new LockTest();
		lt.test();
		
	}
	
	public void test(){
		
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				lock.lock();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("r1..");
				lock.unlock();
			}
		};
		
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				lock.lock();
				System.out.println("r2..");
				lock.unlock();
			}
		};
		
		Runnable r3 = new Runnable() {
			@Override
			public void run() {
				lock.lock();
				System.out.println("r3..");
				lock.unlock();
			}
		};
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		Thread t3 = new Thread(r3);
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	
}
