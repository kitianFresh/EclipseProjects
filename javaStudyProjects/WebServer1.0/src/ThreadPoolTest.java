
public class ThreadPoolTest {

	public static void main(String[] args) {
		/*if(args.length != 2){
			System.out.println("usage: java ThreadPoolTest numTasks poolSize");
			System.out.println("numTasks - integer: 任务数目");
			System.out.println("numThreads = integer: 线程池中工作线程的数目");
			return;
		}
		
		int numTasks = Integer.parseInt(args[0]);
		int poolSize = Integer.parseInt(args[1]);
		*/
		int numTasks = 20;
		int poolSize = 4;
		ThreadPool tp = new ThreadPool(poolSize);
		for(int i=0;i<numTasks;i++){
			tp.execute(createTask(i));
		}
		//tp.close();
		tp.join();
		System.out.println("main thread end");
	}

	private static  Runnable createTask(final int taskID){
		return new Runnable(){

			@Override
			public void run() {
				System.out.println("Task"+taskID+": start");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Task"+taskID+": end");
			}
			
		};
	}
}
