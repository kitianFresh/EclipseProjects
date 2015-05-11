package ThreadTest;
public class Tux extends Thread{
	static String sName = "vandeleur";
	final static Object lock = new Object();
	public static void main(String argv[]){
		Tux t = new Tux();
		t.piggy(sName);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized(lock){
			System.out.println("mainThread: " + sName);
		}
	}
	public void piggy(String sName){
		sName = sName + " wiggy";  //该句执行之后其实并没有改变静态对象sName,这里的sName是栈空间的一个对象引用，而不是成员变量sNmae
		//this.sName = sName + " wiggy";//这句才能改变sNmae 
		start();
	}
	@Override
	public void run(){
		System.out.println("run invoked");
		//synchronized(lock){
			for(int i=0;i < 4; i++){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				sName = sName + " " + i;
			}
			System.out.println("ButtThread: " + sName);
		//}
		System.out.println("run finished");
	}
}
