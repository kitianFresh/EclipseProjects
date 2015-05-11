import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


public class EchoClient {
	Socket s = null;
	String ServerIP = "127.0.0.1";
	int ServerPort = 8080;
	boolean connected = false;
	public static void main(String[] args) {
		EchoClient c = null;
			 c = new EchoClient();
			 c.connect();
	}
	
	public void connect(){
		try {
			s = new Socket(ServerIP,ServerPort);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connected = true;
		new Thread(new RecvThread()).start();
		
		PrintWriter pw = this.getWriter(s);
		BufferedReader stdbr = new BufferedReader(new InputStreamReader(System.in));
		String msg = null;
		
		try {
			while((msg = stdbr.readLine()) != null){
				pw.println(msg);
				pw.flush();
				if(msg.equals("bye"))
					break;
			}
		}catch(EOFException e){	
			System.out.println("Server closed1 !");
		} catch (SocketException e) {
			System.out.println("Server closed2 !");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(s!=null)
				try {
					connected = false;
					s.close();
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	private PrintWriter getWriter(Socket s){
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return pw;
	}
	
	private BufferedReader getReader(Socket s){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return br;
	}
	
	private class RecvThread implements Runnable {

		@Override
		public void run() {
				
					try{
						while(connected){
							BufferedReader br = EchoClient.this.getReader(s);
							String recvMsg = null;
							while((recvMsg = br.readLine())!=null){
								System.out.println(recvMsg);
							}
							
						}
					} catch (EOFException e) {
						System.out.println("Server closed1 !");
					}catch(SocketException e){
						System.out.println("Server closed2 !");
					}catch(IOException e){
						e.printStackTrace();
					}
			
			} 
	}
	
}
