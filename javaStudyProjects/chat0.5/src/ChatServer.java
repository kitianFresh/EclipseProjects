import java.io.IOException;
import java.net.ServerSocket;


public class ChatServer {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8888);
			while(true){
					ss.accept();
System.out.println("A client connected!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
