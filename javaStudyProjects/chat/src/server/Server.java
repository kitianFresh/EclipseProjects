package server;

//import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//import java.io.IOException;

import java.io.*;

import java.net.*;

class WindowCloser implements WindowListener{
	public void windowClosing(WindowEvent e){
		System.out.println("我退出了哦~不要想我哦");
		e.getWindow().setVisible(false);
		((Window)e.getComponent()).dispose();
		System.exit(0);
	}
	public void windowActivated(WindowEvent e){}
	public void windowClosed(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowOpened(WindowEvent e){}	 
}
public class Server {
     Frame f;//声明一个窗口
	Label l=new Label("connect");//声明一个标签
	TextArea t,x;//声明两个文本面板
	//Button connect;
	Button send;//声明两个按钮
	Socket socket;
	String chatline;
	PrintWriter out=null;
	BufferedReader in=null;
	public Server(){
		f=new Frame("Server");//创建窗口并设置标题
		f.setSize(520,600);
		f.setBackground(Color.pink);//设置窗口背景色
		f.setLayout(null);//不用布局管理起来确定组建位置
		l=new Label("connect");//声明一个标签
		t=new TextArea(" ",5,20,TextArea.SCROLLBARS_VERTICAL_ONLY);
		x=new TextArea(" ",5,20,TextArea.SCROLLBARS_VERTICAL_ONLY);
		f.add(t);//将t加到f中
		t.setSize(500,280);
		t.setLocation(8,30);
		f.add(x);//将x加到f中
		x.setSize(500,220);
		x.setLocation(8,310);
		//t.setEditable(false);
		//x.setEditable(true);
		//connect=new Button("连接");
		send=new Button("发送");
		//connect.setBounds(380,510,50,25);
		send.setBounds(440,530,50,25);
		//f.add(connect);
		f.add(send);
		f.setVisible(true);
		
		f.addWindowListener(new WindowCloser()); 
		send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent s){
				chatline=x.getText();
				t.append("\n"+"Server:"+chatline);
				x.setText(null);
				out.println(chatline);
				//System.out.println(chatline);
				out.flush();
				//System.out.println(chatline);
			}
		});
				try{
					ServerSocket server=null;
					try{
						server=new ServerSocket(4445);
					}catch(Exception e){	
						 System.err.println("Could not listen on port: 4445.");
				            System.exit(1);
					}
					
				        socket = server.accept();  //程序将在此等候客户端的连接
					System.out.println("Accept OK!");
					 //打开输入/输出流
					 out = new PrintWriter(socket.getOutputStream(), true);  //auto flush
			         in = new BufferedReader(
							new InputStreamReader(
							socket.getInputStream()));
			         chatline=in.readLine();
				       while(chatline!=""){
					t.append("\n"+"Client:"+chatline);
					chatline = in.readLine();
				       }
				}catch(Exception e){
				}	
	
	}

	public static void main(String[] args) {
		new Server();

	}

}
