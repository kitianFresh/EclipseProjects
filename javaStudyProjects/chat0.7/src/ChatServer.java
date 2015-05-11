import java.io.*;
import java.net.*;


public class ChatServer {

	public static void main(String[] args) {
		try {
			boolean started = false;
			ServerSocket ss = new ServerSocket(8888);
			started = true;
			while(started){
					boolean bconnected = false;
					Socket s = ss.accept();
System.out.println("A client connected!");
					bconnected = true;
					DataInputStream dis = new DataInputStream(s.getInputStream());
					while(bconnected){
						System.out.println(dis.readUTF());
					}
					dis.close();		
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
