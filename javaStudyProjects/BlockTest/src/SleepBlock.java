class Sleeper1 extends Blockable {

	public Sleeper1() {
		super();
	}
	
	public synchronized void run() {
		while(true) {
			i++;
			update();
			try {
				sleep(1000);
			} catch (InterruptedException e){}
		}
	}
}

class Sleeper2 extends Blockable {
	public Sleeper2() {
		super();
	}
	
	public void run() {
		while(true) {
			change();
			try {
				sleep(1000);
			} catch (InterruptedException e){}
		}
	}
	
	private synchronized void change(){
		i++;
		update();
	}
}
public class SleepBlock {

	public static void main(String[] args) {
		new Sleeper1().start();
		new Sleeper2().start();
	}

}