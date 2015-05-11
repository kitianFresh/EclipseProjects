public class SynchTest implements Runnable {  
    private int c = 0;

    public static void main(String[] args) {
        new SynchTest().test();
    }

    public void test() {
        // Create the object with the run() method
        Runnable runnable = new SynchTest();
        Runnable runnable2 = new SynchTest();
        // Create the thread supplying it with the runnable object
        Thread thread = new Thread(runnable,"thread-1");
        Thread thread2 = new Thread(runnable,"thread-2");
//      Here the key point is passing same object, if you pass runnable2 for thread2,
//      then its not applicable for synchronization test and that wont give expected
//      output Synchronization method means "it is not possible for two invocations
//      of synchronized methods on the same object to interleave"

        // Start the thread
        thread2.start();
        thread.start();
        
    }

    public   void increment() {
        System.out.println("Begin thread " + Thread.currentThread().getName());
        System.out.println(this.hashCode() + "Value of C = " + c);
        try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//      If we uncomment this for synchronized block, then the result would be different
      synchronized(this) {
            for (int i = 0; i < 10; i++) {
                c += i;
            }
      }
            System.out.println(this.hashCode() + "Value of C = " + c);
        System.out.println("End thread " + Thread.currentThread().getName());
    }

//    public synchronized void decrement() {
//        System.out.println("Decrement " + Thread.currentThread().getName());
//    }

    public int value() {
        return c;
    }

    @Override
    public void run() {
        this.increment();
    }
}