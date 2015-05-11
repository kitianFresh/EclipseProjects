package ThreadTest;

class Apple {
	int id;

	Apple(int id) {
		this.id = id;
	}

	public String toString() {
		return "Apple: " + id;
	}
}

class SyncStack {
	int index = 0;
	Apple[] apples = new Apple[5];

	public synchronized void push(Apple a) {
		if (index == apples.length) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
		apples[index] = a;
		index++;
		System.out.println("生产了：" + a);
	}

	public synchronized Apple pop() {
		if (index == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
		index--;
		System.out.println("消费了：" + apples[index]);
		return apples[index];
	}
}

public class AppleWork {
	public static void main(String[] args) {
		SyncStack ss = new SyncStack();
		Producer p = new Producer(ss);
		Consumer c = new Consumer(ss);
		Thread tp = new Thread(p);
		Thread tc = new Thread(c);
		tp.start();
		tc.start();
	}
}

class Producer implements Runnable {
	SyncStack ss = null;

	Producer(SyncStack ss) {
		this.ss = ss;
	}

	public void run() {
		int i = -1;
		while(true) {
			i ++;
			Apple a = new Apple(i);
			ss.push(a);
			try {
				Thread.sleep((int) Math.random() * 10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer implements Runnable {
	SyncStack ss = null;

	Consumer(SyncStack ss) {
		this.ss = ss;
	}

	public void run() {
		while(true){
			Apple a = null;
			a = ss.pop();
			try {
				Thread.sleep((int) Math.random() * 10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
