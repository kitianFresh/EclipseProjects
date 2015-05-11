package chatclient;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ChatClientUI extends JFrame{
	JPanel panel1=new JPanel();
	JPanel panel2=new JPanel();
	GridLayout grid=new GridLayout(2,1);
	
	BorderLayout border1 =new BorderLayout();
	JLabel label1=new JLabel("history");
 	TextArea textarea_out=new TextArea("",5,20,TextArea.SCROLLBARS_VERTICAL_ONLY);
	
	BorderLayout border2 =new BorderLayout();
	TextArea textarea_in= new TextArea("",5,20,TextArea.SCROLLBARS_VERTICAL_ONLY);
	JLabel label2=new JLabel("input your message:");
	JButton button_sent=new JButton("sent   （快捷键enter）");
    JButton button_connect=new JButton("construct connect");
    ChatClient chatclient=null;
    boolean connect=false;
    public ChatClientUI(){
    	super("Client");
		textarea_out.setEditable(false);
		panel1.setLayout(border1);
		panel1.add(label1,BorderLayout.NORTH);
		panel1.add(textarea_out,BorderLayout.CENTER);
		
		panel2.setLayout(border2);
		panel2.add(label2,BorderLayout.NORTH);
		panel2.add(textarea_in,BorderLayout.CENTER);
		panel2.add(button_sent,BorderLayout.SOUTH);
		panel2.add(button_connect,BorderLayout.EAST);
		textarea_in.addKeyListener(new KeyListener(){

		
			public void keyTyped(KeyEvent e) {
				/*if(e.getKeyChar()==KeyEvent.VK_ENTER){
					if(!textarea_in.getText().equals("")){
				    chatclient.sendMessage(textarea_in.getText());//由于用ENTER快捷键时，文本本身就含有换行，所以in.readline()直接可以读取，
					 textarea_in.setText(null);   
					}//所以在刷出数据时，不必用println(),这样反而多了一个\n,会导致服务端连续读两次
					}*/
				
			}

			public void keyPressed(KeyEvent e) {
				// TODO 自动生成的方法存根
				
			}

			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){     
					if(!textarea_in.getText().equals("\r\n")&&!textarea_in.getText().matches("^\\s*$")){
			    chatclient.sendMessage(textarea_in.getText());//由于用ENTER快捷键时，文本本身就含有换行，所以in.readline()直接可以读取，
				 textarea_in.setText(null);                    
					}
					else{
						JOptionPane.showMessageDialog(textarea_in,"对不起！输入内容不能为空");
						textarea_in.setText(null);
					}
				}                                              /* 在此还要注意TextArea和TextField对于ENTER键的处理不同，TextArea会把它当成是换行符"\r\n"存入文本内容，
				                                                                                                                                                                             而TextField会把它直接屏蔽掉（因为它不需要换行）*/
			}
			
		});
		button_connect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(!connect){
				chatclient=new ChatClient("127.0.0.1", 4444,ChatClientUI.this);
				JOptionPane.showMessageDialog(button_connect,"服务器已顺利连接！");
				ChatClientUI.this.setChatClient(chatclient);
				connect=true;
				}
				else
					JOptionPane.showMessageDialog(button_connect,"对不起！不要重复连接！");
			}
			
		});
		button_sent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(!textarea_in.getText().equals("\r\n")&&!textarea_in.getText().matches("^\\s*$")){
				    chatclient.sendMessage(textarea_in.getText());//由于用ENTER快捷键时，文本本身就含有换行，所以in.readline()直接可以读取，
					 textarea_in.setText(null);                    
						}
						else{
							JOptionPane.showMessageDialog(textarea_in,"对不起！输入内容不能为空");
							textarea_in.setText(null);
						}
					}                                              /* 在此还要注意TextArea和TextField对于ENTER键的处理不同，TextArea会把它当成是换行符"\r\n"存入文本内容，
					                                                                                                                                                                             而TextField会把它直接屏蔽掉（因为它不需要换行）*/
		});
	
			this.setSize(300, 500);
			this.setLayout(grid);
			this.add(panel1);
			this.add(panel2);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);	
	}
	public void setChatClient(ChatClient chatclient){
		this.chatclient=chatclient;
	}
	/*private class buttonaction implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.getSource()==button_connect){
				chatclient=new ChatClient("127.0.0.1", 4444,ChatUI.this);
				ChatUI.this.setChatClient(chatclient);
			}
			if(arg0.getSource()==button_sent){
			
			}
	}
	}
	private class keyaction implements KeyListener{
        public void keyTyped(KeyEvent e) {
	
         }
        public void keyPressed(KeyEvent e) {
		
         }
        public void keyReleased(KeyEvent e) {
	            if(e.getKeyCode()==KeyEvent.VK_ENTER){
		    		  
			   }
	         }
         }*/
	public static void main(String[] args) {
		
      new ChatClientUI(); 
	}

}
