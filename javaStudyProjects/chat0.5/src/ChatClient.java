import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.*;


public class ChatClient extends Frame {
	TextField tfInput = new TextField();
	TextArea taShow = new TextArea();
	Button btConnect = new Button();
	Socket s = null;
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
				System.exit(0);
			}
			
		});
		pack();
		this.setVisible(true);
		connect();
	}
	
	public void  connect(){
			try {
				s = new Socket("127.0.0.1",8888);
System.out.println("Connected!");				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	private class TFListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = tfInput.getText().trim();
			taShow.append(s);
			tfInput.setText("");
		}
		
	}
	
	private class BTListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}	
		
		
	}
}
