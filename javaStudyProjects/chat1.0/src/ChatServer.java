import java.io.*;
import java.net.*;


public class ChatServer {
	boolean started = false;
	ServerSocket ss = null;
	public static void main(String[] args) {
		new ChatServer().start();
	}
	
	public void start(){
		try {
			ss = new ServerSocket(8888);
		} catch(BindException e){
			System.out.println("Port is occupied....");
			System.out.println("请关闭服务器并重新启动");
			System.exit(-1);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try{
			started = true;	
			while(started){
				 Socket s = ss.accept();
				 System.out.println("A client connected!");
				 Client c = new Client(s);
				 new Thread(c).start();
				//dis.close();		
			}
		}catch(EOFException e){	
			System.out.println("Client closed !");
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				ss.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	class Client implements Runnable {
		private Socket s = null;
		private DataInputStream dis = null;
		private boolean bconnected = false;
		Client(Socket s){
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				bconnected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			try{
				dis = new DataInputStream(s.getInputStream());
				while(bconnected){
					System.out.println(dis.readUTF());
				}
				//dis.close();		
			}catch(EOFException e){	
				System.out.println("Client closed !");
			}
			catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(dis != null)dis.close();
					if(s != null)s.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
	}
}
