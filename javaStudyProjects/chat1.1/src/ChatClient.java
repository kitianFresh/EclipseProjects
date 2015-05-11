import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;


public class ChatClient extends Frame {
	private TextField tfInput = new TextField();
	private TextArea taShow = new TextArea();
	private Button btConnect = new Button();
	private Socket s = null;
	private DataOutputStream dos = null;
	private DataInputStream dis = null;
	private boolean connected = false;
	private Thread tRecv = new Thread(new ReceiveThread());
	
	public static void main(String[] args) {
		new ChatClient().launchFrame();

	}
	
	public void launchFrame(){
		this.setBounds(400,300, 300, 300);
		taShow.setBackground(Color.PINK);
		tfInput.addActionListener(new TFListener());
		this.add(taShow,BorderLayout.NORTH);
		this.add(tfInput,BorderLayout.CENTER);
		this.add(btConnect,BorderLayout.PAGE_END);
		this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				disconnect();
				System.exit(0);
			}
			
		});
		pack();
		this.setVisible(true);
		connect();
		//new Thread(new ReceiveThread()).start();
		tRecv.start();
	}
	
	
	
	public void disconnect(){	
		
		try {
			s.close();
			dos.close();
			dis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		try {
			connected = false;
			tRecv.join();
		} catch (InterruptedException  e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			try {
				s.close();
				dos.close();
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		*/
	}
	
	public void  connect(){
			try {
				s = new Socket("127.0.0.1",8888);
				dos = new DataOutputStream(s.getOutputStream());
				dis = new DataInputStream(s.getInputStream());
				connected = true;
System.out.println("Connected!");				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	private class TFListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			String str = tfInput.getText().trim();
			tfInput.setText("");
			try{
				
				dos.writeUTF(str);
				dos.flush();
				//dos.close();
			}catch(IOException e){	
				e.printStackTrace();
			}
		}
		
	}
	
	private class BTListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}	
		
		
	}

	private class ReceiveThread implements Runnable {

		@Override
		public void run() {
				
					try{
						while(connected){
							String str  = dis.readUTF();//阻塞方法,不给读就一直阻塞，因此在关闭主线程的时候必须先关闭子线程
							taShow.append(str+"\n");
						}
					} catch (EOFException e) {
						// TODO Auto-generated catch block
						System.out.println("Client closed !");
					}catch(SocketException e){
						System.out.println("Client closed !");
					}catch(IOException e){
						e.printStackTrace();
					}
			
			} 
	}
}
