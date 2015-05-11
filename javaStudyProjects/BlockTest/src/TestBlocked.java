class Blockable extends Thread {
	private Peeker peeker;
	protected int i;
	public Blockable(){
		peeker = new Peeker(this);
	}
	
	public synchronized int read(){ return i;}
	
	public synchronized void update(){
		System.out.println(this.getClass().getName()+"state :i = " + i);
	}
	
	public void stopPeeker(){
		peeker.terminate();
	}
}

class Peeker extends Thread {
	private Blockable b;
	private int session;
	private boolean stop = false;
	
	public Peeker(Blockable b){
		this.b = b;
		start();
	}
	
	public void run(){
		while (!stop) {
			System.out.println(b.getClass().getName()
			+ " Peeker " + (++session)
			+ "; value = " + b.read());
			try {
				sleep(1000);
			} catch (InterruptedException e){}
		}
	}
	
	public void terminate() {stop = true;}
	
}
