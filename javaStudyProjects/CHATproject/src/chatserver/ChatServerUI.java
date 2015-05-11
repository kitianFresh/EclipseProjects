package chatserver;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import javax.swing.JOptionPane;
public class ChatServerUI extends JFrame{
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
    ChatServer chatserver=null;
    public ChatServerUI(){
    	super("Server");
		textarea_out.setEditable(false);
		panel1.setLayout(border1);
		panel1.add(label1,BorderLayout.NORTH);
		panel1.add(textarea_out,BorderLayout.CENTER);
		
		panel2.setLayout(border2);
		panel2.add(label2,BorderLayout.NORTH);
		panel2.add(textarea_in,BorderLayout.CENTER);
		panel2.add(button_sent,BorderLayout.SOUTH);
		textarea_in.addKeyListener(new KeyListener(){

		
			public void keyTyped(KeyEvent e) {
				// TODO 自动生成的方法存根
				
			}

			public void keyPressed(KeyEvent e) {
				// TODO 自动生成的方法存根
				
			}

			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){      //先检查是否未输入内容或输入内容全是空格等
					if(!textarea_in.getText().equals("\r\n")&&!textarea_in.getText().matches("^\\s*$")){
			    chatserver.sendMessage(textarea_in.getText());//由于用ENTER快捷键时，文本本身就含有换行，所以in.readline()直接可以读取，
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
		button_sent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(!textarea_in.getText().equals("\r\n")&&!textarea_in.getText().matches("^\\s*$")){
				    chatserver.sendMessage(textarea_in.getText());//由于用ENTER快捷键时，文本本身就含有换行，所以in.readline()直接可以读取，
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
    //用于设定本UI对应的数据监听服务线程
	public void setChatServer(ChatServer chatserver){   
		this.chatserver=chatserver;              
	}
	public static void main(String[] args) {
		
      ChatServerUI serverui=new ChatServerUI();
      serverui.setChatServer(new ChatServer(4444,serverui));
	}

}
