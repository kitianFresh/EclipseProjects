import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class TankClient extends Frame {
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	Image offScreenImage = null;
	Tank mytank = new Tank(0,0,this);
	List<Tank> etanks = new ArrayList<Tank>();
	List<Missile> missiles = new ArrayList<Missile>();
	List<Explode> explodes = new ArrayList<Explode>();
	
	@Override
	public void paint(Graphics g) {
		/*
		if(missiles != null){
			Iterator<Missile> it = missiles.iterator();
			while(it.hasNext()){
				Missile m =  it.next();
				m.draw(g);
			}
		}
		 */
		g.drawString("missiles count:" + missiles.size(), 20, 50);
		g.drawString("explodes count:" + explodes.size(), 20, 60);
		g.drawString("tanks    count:" + etanks.size(), 20, 70);
		for(int i =0;i<missiles.size();i++){
			Missile m = missiles.get(i);
			m.hitTanks(etanks);
			m.hitTank(mytank);
			m.draw(g);
		}
		
		
		for(int i=0;i<explodes.size();i++){
			Explode e = explodes.get(i);
			e.draw(g);
		}
		
		for(int i =0;i<etanks.size();i++){
			Tank t = etanks.get(i);
			t.draw(g);
		}
		
		mytank.draw(g);
	}
	//双缓冲技术解决闪烁，使用内存中的虚拟图片
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null){
			offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.green);
		gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
		gOffScreen.setColor(c);
		
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0,null);
	}

	
	public static void main(String[] args) {
		new TankClient().launchFrame();
	}

	public void launchFrame(){
		
		for(int i =0;i<10;i++){
			etanks.add(new Tank(50 + 50*(i+1),50,this,Tank.Direction.D,true));
		}
		this.setTitle("TankWar");
		this.setBounds(100, 100, GAME_WIDTH, GAME_HEIGHT);
		this.setResizable(false);
		this.setBackground(Color.GREEN);
		this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		this.addKeyListener(new myListener());
		
		this.setVisible(true);
		new Thread(new PaintThread()).start();
	}
	
	private class PaintThread implements Runnable {

		@Override
		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	private class myListener extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			mytank.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			mytank.keyPressed(e);
		}
		
	}
	
}
