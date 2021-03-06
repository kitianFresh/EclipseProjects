import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;


public class TankMoveMsg implements Msg{
	
	int msgType = Msg.TANK_MOVE_MSG;
	private int ID;
	private Direction dir;
	private TankClient tc;
	private int x, y;
	TankMoveMsg(int ID,int x, int y, Direction dir){
		this.ID = ID;
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public TankMoveMsg(TankClient tc) {
		this.tc = tc;
	}
	
	@Override
	public void send(DatagramSocket ds, String ip, int udpPort) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(ID);
			dos.writeInt(dir.ordinal());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] buf = baos.toByteArray();
		DatagramPacket dp;
		try {
			dp = new DatagramPacket(buf,buf.length,new InetSocketAddress(ip, udpPort));
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
			int id = dis.readInt();
			if(tc.mytank.getID() == id) {
				return;
			}
			
			Direction  dir = Direction.values()[dis.readInt()];
			
//System.out.println("id:" + id + "-x:" + x + "-y:" + y + "-dir:" + dir + "-good:" + good);
			boolean exist = false;
			for(int i=0; i<tc.etanks.size(); i++) {
				Tank t = tc.etanks.get(i);
				if(t.getID() == id) {
					t.setDir(dir);
					exist = true;
					break;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
