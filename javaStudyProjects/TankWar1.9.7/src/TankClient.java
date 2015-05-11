import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankClient extends Frame {
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	
	ConnDialog dialog = new ConnDialog();
	NetClient nc = new NetClient(this);
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
		
		//nc.connect("127.0.0.1",TankServer.TCP_PORT);
		/*for(int i =0;i<10;i++){
			etanks.add(new Tank(50 + 50*(i+1),50,this,Direction.D,true));
		}*/
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
		dialog.setVisible(true);
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
	
	class ConnDialog extends Dialog {
		Button b = new Button("确定");
		TextField tfIP = new TextField("127.0.0.1", 12);
		TextField tfPort = new TextField("" + TankServer.TCP_PORT, 4);
		TextField tfMyUDPPort = new TextField("2223", 4);
		public ConnDialog() {
			super(TankClient.this, true);
		
			this.setLayout(new FlowLayout());
			this.add(new Label("IP:"));
			this.add(tfIP);
			this.add(new Label("Port:"));
			this.add(tfPort);
			this.add(new Label("My UDP Port:"));
			this.add(tfMyUDPPort);
			this.add(b);
			this.setLocation(300, 300);
			this.pack();
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					setVisible(false);
				}
			});
			b.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String IP = tfIP.getText().trim();
					int port = Integer.parseInt(tfPort.getText().trim());
					int myUDPPort = Integer.parseInt(tfMyUDPPort.getText().trim());
					nc.setUdpPort(myUDPPort);
					nc.connect(IP, port);
					setVisible(false);
				}
				
			});
		}

	}
	
}
