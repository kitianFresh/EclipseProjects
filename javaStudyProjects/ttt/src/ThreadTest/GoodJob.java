package ThreadTest;

public class GoodJob extends Thread {
	  int x = 0;
	  public class Runner implements Runnable {
	    public void run() {
	      int current = 0;
	      for (int i = 0; i < 4; i++) {
	        current = x;
	        try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.print(current + ",");
	        x = current + 2;
	      }
	    }
	  }
	  public static void main(String[] args) {
	    new GoodJob().run();
	  }
	  public void run() {
	    Runnable r1 = new Runner();
	    new Thread(r1).start();
	   try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    new Thread(r1).start();
	  }
	}
