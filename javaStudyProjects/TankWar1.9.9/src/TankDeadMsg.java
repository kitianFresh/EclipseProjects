import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;


public class TankDeadMsg implements Msg {
	int msgType = Msg.TANK_DEAD_MSG;
	TankClient tc;
	int id;
	
	public TankDeadMsg(int id) {
		this.id = id;
	}
	
	public TankDeadMsg(TankClient tc) {
		this.tc = tc;
	}
	@Override
	public void send(DatagramSocket ds, String ip, int udpPort) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(id);

			byte[] buf = baos.toByteArray();
			DatagramPacket dp;
			dp = new DatagramPacket(buf, buf.length, new InetSocketAddress(ip,
					udpPort));
			ds.send(dp);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void parse(DataInputStream dis) {
		try {
			int ID = dis.readInt();
			if(tc.mytank.getID() == ID){
				return;
			}
			
			for(int i=0; i<tc.etanks.size(); i++) {
				Tank t = tc.etanks.get(i);
				if(t.getID() == ID) {
					t.setLive(false);
					break;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
