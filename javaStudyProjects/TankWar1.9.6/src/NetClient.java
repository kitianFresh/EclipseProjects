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
	private static int UDP_PORT_START = 2222;
	private Socket s = null;
	private DatagramSocket ds = null;
	private DataOutputStream dos = null;
	private int udpPort;
	NetClient(TankClient tc){
		this.tc = tc;
		udpPort = UDP_PORT_START++;
		try {
			ds = new DatagramSocket(udpPort);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	boolean connect(String ip, int port){
		try {
			s = new Socket(ip,port);
			dos = new DataOutputStream(s.getOutputStream());
			dos.writeInt(udpPort);
			DataInputStream dis = new DataInputStream(s.getInputStream());
			int id = dis.readInt();
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
		msg.send(ds,"127.0.0.1",TankServer.UDP_PORT);
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
			}
			msg.parse(dis);
		}
		
	}
}
