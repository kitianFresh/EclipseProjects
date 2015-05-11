import java.util.LinkedList;


public class ThreadPool extends ThreadGroup {
	private LinkedList<Runnable> workQueue;
	private static int threadPoolID;
	private static int threadID;
	private boolean isClosed = false;
	
	public ThreadPool(int poolSize) {
		super("ThreadPool-" + (++threadPoolID));
		setDaemon(true);
		workQueue = new LinkedList<Runnable>();
		for(int i=0;i<poolSize;i++){
			new WorkThread().start();
		}
	}

	public synchronized void execute(Runnable task){
		if(isClosed){
			throw new IllegalStateException();
		}
		if(task != null){
			workQueue.add(task);
			notify();
		}
	}
	
	public synchronized Runnable getTask() throws InterruptedException{
		 while(workQueue.size() == 0){
			 if(isClosed) return null;
			 wait();
		 }
		 return workQueue.removeFirst();
	}
	
	public synchronized void close(){
		if(!isClosed){
			isClosed = true;
			workQueue.clear();
			interrupt();
		}
	}
	
	public void join(){
		synchronized(this){
			isClosed = true;
			notifyAll();
		}
		
		Thread[] threads = new Thread[activeCount()];
		int count = enumerate(threads);
		for(int i=0;i<count;i++){
			try{
				threads[i].join();
			} catch(InterruptedException e){}
		}
	}
		
	private class WorkThread extends Thread {
		public WorkThread(){
			super(ThreadPool.this,"WorkThread-"+(threadID++));
		}
		public void run(){
			while(!isInterrupted()){
				Runnable task = null;
				try{
					task = getTask();
				} catch(InterruptedException e){}
				
				if(task == null) return;
				try{
					task.run();
				} catch(Throwable t){
					t.printStackTrace();
				}
			}
		}
	}
}

