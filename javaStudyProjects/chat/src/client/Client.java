package client;
import java.awt.*;
import java.awt.event.*;
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

public class Client{
	private Frame f;//声明一个窗口
	Label l=new Label("connect");//声明一个标签
	TextArea t,x;//声明两个文本面板
	Button connect;
	Button send;//声明两个按钮
	Socket socket;
	PrintWriter out=null;
	BufferedReader in=null;
	String chatline;
	public Client(){
		f=new Frame("Client");//创建窗口并设置标题
		f.setSize(520,600);
		f.setBackground(Color.pink);//设置窗口背景色
		f.setLayout(null);//不用布局管理起来确定组建位置
		t=new TextArea(" ",5,20,TextArea.SCROLLBARS_VERTICAL_ONLY);
		x=new TextArea(" ",5,20,TextArea.SCROLLBARS_VERTICAL_ONLY);
		//f.setLayout(flow);
		f.add(t);//将t加到f中
		t.setSize(500,280);
		t.setLocation(8,30);
		f.add(x);//将x加到f中
		x.setSize(500,220);
		x.setLocation(8,310);
		//t.setEditable(false);
		//x.setEditable(true);
		connect=new Button("连接");
		send=new Button("发送");
		connect.setBounds(380,530,50,25);
		send.setBounds(440,530,50,25);
		f.add(connect);
		f.add(send);
		f.setVisible(true);
		 f.addWindowListener(new WindowCloser()); 
		 
	    connect.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e1) {
					try{
						 socket=new Socket("127.0.0.1",4445);
						  out=new PrintWriter(socket.getOutputStream(),true);
						  in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
						t.append("Waiting");
						 //File f=new File("E:\\chat.txt");
					}catch(Exception e){
						t.setText("error!");
					}
				}
				});
	    send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				    chatline=x.getText();
			        out.println(chatline);
			        out.flush();
			        t.append("\n"+"Client:"+chatline);
				    x.setText(null);			
				}
});		
	    try{
	    	String s="okok";
			//System.out.println("ok");
			
			 s=in.readLine();
			 //System.out.println("eeee");
			 System.out.println(s);
            while(chatline!=""){
	             t.append("\n"+"Server:"+s);
	           s= in.readLine();
            }
	}catch(Exception e){	
	}
		

}
	public static void main(String[] args){
		new Client();
		}
}
