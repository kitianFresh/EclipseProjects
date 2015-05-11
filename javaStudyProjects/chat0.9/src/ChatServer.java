import java.io.*;
import java.net.*;


public class ChatServer {

	public static void main(String[] args) {
		boolean started = false;
		ServerSocket ss = null;
		DataInputStream dis = null;
		Socket s = null;

		try {
			ss = new ServerSocket(8888);
		} catch(BindException e){
			System.out.println("Port is occupied....");
			System.out.println("请关闭服务器并重新启动");
			System.exit(-1);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			started = true;	
			while(started){
				boolean bconnected = false;
				 s = ss.accept();
				 System.out.println("A client connected!");
				bconnected = true;
				dis = new DataInputStream(s.getInputStream());
				while(bconnected){
					System.out.println(dis.readUTF());
				}
				//dis.close();		
			}
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
