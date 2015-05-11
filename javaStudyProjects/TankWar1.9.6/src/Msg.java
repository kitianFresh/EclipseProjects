import java.io.DataInputStream;
import java.net.DatagramSocket;


public interface Msg {
	public static final int TANK_NEW_MSG = 1;
	public static final int TANK_MOVE_MSG = 2;
	
	void send(DatagramSocket ds, String ip, int udpPort);
	void parse(DataInputStream dis);
}