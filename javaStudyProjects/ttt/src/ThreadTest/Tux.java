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
		sName = sName + " wiggy";  //�þ�ִ��֮����ʵ��û�иı侲̬����sName,�����sName��ջ�ռ��һ���������ã������ǳ�Ա����sNmae
		//this.sName = sName + " wiggy";//�����ܸı�sNmae 
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
