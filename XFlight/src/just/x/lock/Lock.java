package just.x.lock;

import java.util.concurrent.atomic.AtomicReference;

public class Lock {

	private AtomicReference<Thread> sign = new AtomicReference<Thread>();

	public void lock() {

		Thread current = Thread.currentThread();

		while (!sign.compareAndSet(null, current)) {

		}

	}

	public void unlock() {

		Thread current = Thread.currentThread();

		sign.compareAndSet(current, null);

	}
	
}
