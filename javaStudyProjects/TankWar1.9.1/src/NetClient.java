import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class NetClient {
	
	private static int UDP_PORT_START = 2223;
	private Socket s = null;
	private DataOutputStream dos = null;
	private int udpPort;
	NetClient(){
		udpPort = UDP_PORT_START++;
	}
	
	boolean connect(String ip, int port){
		try {
			s = new Socket(ip,port);
			dos = new DataOutputStream(s.getOutputStream());
			dos.writeInt(udpPort);
			
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
		return true;
	}
}
