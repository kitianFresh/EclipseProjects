package chatserver;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ChatServer extends Thread {
	ChatServerUI ui;  //用于指明本线程所邦定的UI
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
            clientSocket = serverSocket.accept();  //程序将在此等候客户端的连接
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
		msg=msg.trim();//去除信息中多余的ENTER键
		ui.textarea_out.append(packMessageServer(msg));
		out.println(msg);//注意readlne()函数以换行\n符为结束标志一行读取,所以次数必须用println()而不是print()
		out.flush();
	}
	//数据打包是为了在文本框里按格式显示
	private String packMessageServer(String content) {
        String result = null;
        Date now=new Date();
      SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");    //设置日期格式
           //String message = content.substring(8);        //获取用户发送的真实的信息
            result = "Server " + df.format(now) + "\n" + content+"\n\n" ;
        return result;
}
	private String packMessageClient(String content) {
        String result = null;
        Date now=new Date();
      SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");    //设置日期格式
           //String message = content.substring(8);        //获取用户发送的真实的信息
            result = "Mr Tian " + df.format(now) + "\n" + content+"\n\n";
        return result;
}
}
