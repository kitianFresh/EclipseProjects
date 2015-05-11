import java.io.*;
import java.net.*;
import java.util.*;


public class ChatServer {
	boolean started = false;
	ServerSocket ss = null;
	List<Client> clients = new ArrayList<Client>();
	
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
				 clients.add(c);	
			}
		}catch(Exception e){
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
		private DataOutputStream dos = null;
		private boolean bconnected = false;
		
		Client(Socket s){
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				bconnected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void send(String str){
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try{
				dis = new DataInputStream(s.getInputStream());
				while(bconnected){
					String str = dis.readUTF();
					System.out.println(str);
					
					for(int i=0;i<clients.size();i++){
						Client c = clients.get(i);
						if(!c.bconnected) {
							clients.remove(i);
						}
						else{
							c.send(str);	
						}

					}
					
					/*
					Iterator<Client> it = clients.iterator();
					while(it.hasNext()){
						Client c = it.next();
						c.send(str);
					}
					*/
				}
		
			}catch(EOFException e){	
				System.out.println("Client closed !");
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					bconnected = false;
					if(dos != null) dos.close();
					if(dis != null)dis.close();
					if(s != null)s.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
	}
}
