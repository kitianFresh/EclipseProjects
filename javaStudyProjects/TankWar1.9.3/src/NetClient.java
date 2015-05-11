import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


public class NetClient {
	TankClient tc = null;
	private static int UDP_PORT_START = 2223;
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
			tc.mytank.ID = id;
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
		return true;
	}
	
	public void send(TankNewMsg msg){
		msg.send(ds,"127.0.0.1",TankServer.UDP_PORT);
	}
}
