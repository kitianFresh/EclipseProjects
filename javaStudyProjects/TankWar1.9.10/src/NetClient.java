import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


public class NetClient {
	TankClient tc = null;
	static String IP = "127.0.0.1";
	public int getUdpPort() {
		return udpPort;
	}

	public void setUdpPort(int udpPort) {
		this.udpPort = udpPort;
	}

	private Socket s = null;
	private DatagramSocket ds = null;
	private DataOutputStream dos = null;
	private int udpPort ;
	NetClient(TankClient tc){
		this.tc = tc;
	}
	
	boolean connect(String ip, int port){
		IP = ip;
		try {
			try {
				ds = new DatagramSocket(udpPort);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s = new Socket(ip,port);
			if(!s.getTcpNoDelay()){s.setTcpNoDelay(true);}
			dos = new DataOutputStream(s.getOutputStream());
			dos.writeInt(udpPort);
			DataInputStream dis = new DataInputStream(s.getInputStream());
			int id = dis.readInt();
			if(id%2 == 0){tc.mytank.setbEnemy(true);}
			else {tc.mytank.setbEnemy(false);}
			tc.mytank.setID(id);
System.out.println("Got an ID from Server!");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}finally{
				try {
					if(s != null) s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		System.out.println("Connected to Server!");
		
		TankNewMsg msg = new TankNewMsg(tc.mytank);
		send(msg);
		new Thread(new UDPRecvThread()).start();
		return true;
	}
	
	public void send(Msg msg){
		msg.send(ds,IP,TankServer.UDP_PORT);
	}
	
	private class UDPRecvThread implements Runnable {
		
		byte[] buf = new byte[1024];
		@Override
		public void run() {
			
System.out.println("UDP RecvThread started at client udpPort: "+udpPort);
			DatagramPacket dp = new DatagramPacket(buf,buf.length);
			while (ds != null) {
				try {
					ds.receive(dp);
					parse(dp);
					
System.out.println("A DatagramPacket received!");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		private void parse(DatagramPacket dp) throws IOException {
			ByteArrayInputStream bais = new ByteArrayInputStream(dp.getData(), 0, dp.getLength());
			DataInputStream dis = new DataInputStream(bais);
			
			Msg msg = null;
			switch(dis.readInt()){
			case Msg.TANK_NEW_MSG:
				msg = new TankNewMsg(NetClient.this.tc );
				break;
			case Msg.TANK_MOVE_MSG:
				msg = new TankMoveMsg(NetClient.this.tc );
				break;
			case Msg.MISSILE_NEW_MSG:
			    msg = new MissileNewMsg(NetClient.this.tc );
			    break;
			case Msg.TANK_DEAD_MSG:
				msg = new TankDeadMsg(NetClient.this.tc);
				break;
			case Msg.MISSILE_DEAD_MSG:
			    msg = new MissileDeadMsg(NetClient.this.tc );
			    break;
			}
			msg.parse(dis);
		}
		
	}
}
