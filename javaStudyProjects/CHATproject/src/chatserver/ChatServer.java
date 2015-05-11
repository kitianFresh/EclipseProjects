package chatserver;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ChatServer extends Thread {
	ChatServerUI ui;  //����ָ�����߳������UI
	ServerSocket serverSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
	public ChatServer(int port,ChatServerUI ui){
		this.ui=ui;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();  //�����ڴ˵Ⱥ�ͻ��˵�����
            out=new PrintWriter(clientSocket.getOutputStream(), true); //auto flush
        	in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
	System.out.println("Accept OK!");
	
		start();
	}
	public void run(){
		String msg=null;
			try{
				msg=in.readLine();
				while(msg!=null){
					if(!msg.equals("")){
						ui.textarea_out.append(packMessageClient(msg));
					}
				msg=in.readLine();
				}
			}catch (SocketException e){
				System.out.println(e);
			}catch (Exception e){
				System.out.println(e);
			}
			
	
		}
	public void sendMessage(String msg){
		msg=msg.trim();//ȥ����Ϣ�ж����ENTER��
		ui.textarea_out.append(packMessageServer(msg));
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
