import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
public class TankServer {
	public static int TCP_PORT = 8888;
	ServerSocket ss = null;
	List<Client> clients = new ArrayList<Client>();
	
	public static void main(String[] args){
		new TankServer().start();
	}
	
	public void start(){
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
				clientS.close();
				
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
}
