package ThreadTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Cooperation {
	
	public static void main(String[] args){
		SyncBuffer sb = new SyncBuffer();
		Reader r = new Reader(sb);
		Reader r1 = new Reader(sb);
		Writer w = new Writer(sb);
		new Thread(r,"r").start();
		new Thread(r1,"r1").start();
		Thread tw = new Thread(w,"w");
		tw.setPriority(1);
		tw.start();
	}
}
class SyncBuffer {
	byte[] buffer = new byte[64];
	ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
	int rindex = 0;
	int windex = 0;
	public void read() throws IOException{
			synchronized(this){
				while(rindex >= windex){
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.print(Thread.currentThread().getName()+"-");
				System.out.println("Reader:" + bais.read());
				rindex ++;
				if(rindex%64 == 0) bais.reset();
				notify();
			}		
	}
	
	public void write() throws IOException{
			synchronized(this){
				while(windex >= 64+rindex){
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				buffer[windex%64] = (byte) (windex%128);
				System.out.print(Thread.currentThread().getName()+"-");
				System.out.println("Writer: "+ buffer[windex%64]);
				windex ++;	
				notify();
			}
				
	}
}
class Reader implements Runnable {
	SyncBuffer sb = null;
	Reader(SyncBuffer sb){
		this.sb = sb;
	}
	@Override
	public void run() {
		while(true){
			try {
				sb.read();
				try {
					Thread.sleep((int) Math.random() * 10000000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class Writer implements Runnable {
	SyncBuffer sb = null;
	Writer(SyncBuffer sb){
		this.sb = sb;
	}
	@Override
	public void run() {
		while(true){
			try {
				sb.write();	
				try {
					Thread.sleep((int) Math.random() * 10000000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
