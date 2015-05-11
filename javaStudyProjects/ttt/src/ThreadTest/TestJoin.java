package ThreadTest;

public class TestJoin {
	public static void main(String args[]) {
		myThread th1 = new myThread("Thread I");
		myThread th2 = new myThread("Thread II");
		th1.start();
		th2.start();
		try {
			//th1.join(); // ��main�̷߳ŵ�th1�̵߳ĺ��棬ֻ�е��߳�th1�������Ժ�main�ſ�ʼ����
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 10; i >= 0; i--) {
			System.out.println("main is running!");
		}
	}
}

class myThread extends Thread {
	public myThread(String str) {
		super(str);
	}

	public void run() {
		for (int I = 0; I < 3; I++) {
			System.out.println(getName() + "��������!");
			try {
				sleep((int) (Math.random() * 1000));
			} catch (InterruptedException e) {
			}
		}
		System.out.println(getName() + "�Ѿ�����!");
	}
}
