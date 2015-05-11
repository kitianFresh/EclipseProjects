import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
public class TankServer {
	private static int ID = 100;//这个给客户端的UUID不必考虑异步，因为ss.accept()方法是同步的，只有一个处理完才处理下一个连接，故不会有多线程并发连接的情况
	public static int TCP_PORT = 8888;
	public static int UDP_PORT = 9999;
	ServerSocket ss = null;
	List<Client> clients = new ArrayList<Client>();
	
	public static void main(String[] args){
		new TankServer().start();
		
	}
	
	public void start(){
		new Thread(new UDPThread()).start();
		try {
			ss = new ServerSocket(TCP_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true){
			Socket clientS = null;
			try {
				clientS = ss.accept();
				//clients.add(new NetClient());
				DataInputStream dis = new DataInputStream(clientS.getInputStream());
				String ip = clientS.getInetAddress().getHostAddress();
				int udpPort = dis.readInt();
				Client c = new Client(ip,udpPort);
				clients.add(c);
				DataOutputStream dos = new DataOutputStream(clientS.getOutputStream());
				dos.writeInt(ID++);
				System.out.println("A tankClient connected! Addr--" + clientS.getInetAddress() + "/TCP_PORT:" + clientS.getPort() + "UDP_PORT:" + udpPort);
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					if(clientS != null)clientS.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private class Client {
		String ip;
		int udpPort;
		Client(String ip, int udpPort){
			this.ip = ip;
			this.udpPort = udpPort;
		}
	}
	
	private class UDPThread implements Runnable {
		
		byte[] buf = new byte[1024];
		@Override
		public void run() {
			DatagramSocket ds = null;
			try {
				ds = new DatagramSocket(UDP_PORT);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
System.out.println("UDP thread started at port: "+UDP_PORT);
			DatagramPacket dp = new DatagramPacket(buf,buf.length);
			while (ds != null) {
				try {
					ds.receive(dp);
System.out.println("A DatagramPacket received!");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
		}
		
	}
}
