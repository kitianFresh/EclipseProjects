import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;


public class TankNewMsg implements Msg{
	int msgType = Msg.TANK_NEW_MSG;
	Tank tank;
	TankClient tc;
	TankNewMsg(Tank tank){
		this.tank = tank;
	}
	
	TankNewMsg(TankClient tc){
		this.tc = tc;
	}
	
	public void send(DatagramSocket ds, String ip, int udpPort) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(tank.getID());
			dos.writeInt(tank.getX());
			dos.writeInt(tank.getY());
			dos.writeInt(tank.getDir().ordinal());
			dos.writeBoolean(tank.isbEnemy());
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
	
	public void parse(DataInputStream dis)  {
		try {
			int ID = dis.readInt();
			if(tc.mytank.getID() == ID){
				return;
			}
			int x = dis.readInt();
			int y = dis.readInt();
			Direction dir = Direction.values()[dis.readInt()];
			boolean bEnemy = dis.readBoolean();
			boolean exist = false;
			for(int i=0; i<tc.etanks.size(); i++) {
				Tank t = tc.etanks.get(i);
				if(t.getID() == ID) {
					exist = true;
					break;
				}
			}
			
			if(!exist) {
				TankNewMsg tnMsg = new TankNewMsg(tc.mytank);//收到一个新成员加入消息后，自己也发一个TankNewMsg
				tc.nc.send(tnMsg);					//告诉新成员把自己加入到新成员客户端
				Tank t = new Tank(x, y, tc, dir,bEnemy );
				t.setID(ID);
				tc.etanks.add(t);
			}
			
			
	System.out.println("Tank ID: " + ID);
	System.out.println("Tank x: " + x);
	System.out.println("Tank y: " + y);
	System.out.println("Tank dir: " + dir);
	System.out.println("Tank bEnemy: " + bEnemy);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}
