package chatclient;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ChatClient extends Thread {
	ChatClientUI ui;    //����ָ���������߳������UI
    Socket clientSC;
    PrintWriter out = null;
    BufferedReader in = null;
	public ChatClient(String ip,int port,ChatClientUI ui) {
		this.ui=ui;
		try{
			clientSC=new Socket(ip,port);
			System.out.println("��������˳�����ӣ�");
			out=new PrintWriter(clientSC.getOutputStream(), true); //auto flush
			in=new BufferedReader(new InputStreamReader(clientSC.getInputStream()));
		}catch (Exception e){
			System.out.println(e);
		}
		start();
	}
	public void run(){
		String msg=null;
		{
			try{
				msg=in.readLine();
				while(msg!=null){
					if(!msg.equals("")){
						ui.textarea_out.append(packMessageServer(msg));
					}
				msg=in.readLine();
				}
			}catch (SocketException e){
				System.out.println(e);
			}catch (Exception e){
				System.out.println(e);
			}
			
		}

	}
	public void sendMessage(String msg){
		msg=msg.trim();//ȥ����Ϣ�ж����ENTER��	
		ui.textarea_out.append(packMessageClient(msg));
		out.println(msg);//ע��readlne()�����Ի���\n��Ϊ������־һ�ж�ȡ,���Դ���������println()������print()
		out.flush();
	}
	//���ݴ����Ϊ�����ı����ﰴ��ʽ��ʾ
	private String packMessageServer(String content) {
        String result = null;
        Date now=new Date();
      SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");    //�������ڸ�ʽ
           //String message = content.substring(8);        //��ȡ�û����͵���ʵ����Ϣ
            result = "Server " + df.format(now) + "\n" + content+"\n\n" ;
        return result;
}
	private String packMessageClient(String content) {
        String result = null;
        Date now=new Date();
      SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");    //�������ڸ�ʽ
           //String message = content.substring(8);        //��ȡ�û����͵���ʵ����Ϣ
            result = "Mr Tian " + df.format(now) + "\n" + content+"\n\n";
        return result;
}
}
