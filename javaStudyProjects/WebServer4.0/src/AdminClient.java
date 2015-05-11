import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


public class AdminClient {
	Socket adminSocket = null;
	String ServerIP = "127.0.0.1";
	int shutdownServerPort = 8081;
	boolean connected = false;
	
	public static void main(String[] args){
		new AdminClient().connect();
	}
	
	public void connect(){
		BufferedReader br = null;
		try {
			adminSocket = new Socket(ServerIP,shutdownServerPort);
			br = new BufferedReader(new InputStreamReader(adminSocket.getInputStream()));
			adminSocket.getOutputStream().write("shutdown\r\n".getBytes());
			String msg = null;
			while((msg = br.readLine()) != null){
				System.out.println(msg);
			}
		} catch (SocketException e) {
			System.out.println("server closed");
		} catch (UnknownHostException e) {
			System.out.println("unknown Server Host");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(adminSocket != null)adminSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
