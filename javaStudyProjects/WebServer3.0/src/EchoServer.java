import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class EchoServer {

	private ServerSocket ss = null;
	private static final int ServerPort = 8080;
	private ExecutorService executorService = null;
	private final int POOL_SIZE = 4;
	
	public static void main(String[] args) {
		new EchoServer().service();
	}
	
	public EchoServer(){
		try {
			ss = new ServerSocket(ServerPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors());
		System.out.println("cpu num: "+Runtime.getRuntime().availableProcessors());
		System.out.println("·þÎñÆ÷Æô¶¯");
	}
	public void service(){
		try {
			Socket s = null;
			while(true){
				 s = ss.accept();
				 executorService.execute(new Handler(s));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 

	
	
	private class Handler implements Runnable {
		Socket s = null;
		boolean bconnected = false;
		Handler(Socket s){
			this.s = s;
			bconnected = true;
		}
		@Override
		public void run() {
			System.out.println("New client Connected-" 
					+s.getInetAddress().getHostAddress()+":"+s.getPort());
			PrintWriter pw = this.getWriter(s);
			BufferedReader br = this.getReader(s);
			String msg = null;
			while(bconnected){
				try {
					while((msg = br.readLine())!=null){
						printMsg(msg);
						pw.println(echo(msg));
						pw.flush();
						if(msg.equals("bye"))
							break;
					}
					
				}catch(EOFException e){	
					System.out.println("Client closed1 !");
				}catch(SocketException e){
					System.out.println("Client closed2 !");
				}catch (IOException e) {
					e.printStackTrace();
				}finally{
					if(s!=null)
						try {
							bconnected = false;
							s.close();
							br.close();
							pw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
			}
			
		}
		
		private String echo(String msg) {
			return "hello: "+msg;
		}
		
		private void printMsg(String msg) {
			System.out.println("Client-" 
					+s.getInetAddress().getHostAddress()+":"+s.getPort() + "[" + msg + "]");
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
		
	}
}
