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
	JButton button_sent=new JButton("sent   ����ݼ�enter��");
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
				// TODO �Զ����ɵķ������
				
			}

			public void keyPressed(KeyEvent e) {
				// TODO �Զ����ɵķ������
				
			}

			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){      //�ȼ���Ƿ�δ�������ݻ���������ȫ�ǿո��
					if(!textarea_in.getText().equals("\r\n")&&!textarea_in.getText().matches("^\\s*$")){
			    chatserver.sendMessage(textarea_in.getText());//������ENTER��ݼ�ʱ���ı�����ͺ��л��У�����in.readline()ֱ�ӿ��Զ�ȡ��
				 textarea_in.setText(null);                    
					}
					else{
						JOptionPane.showMessageDialog(textarea_in,"�Բ����������ݲ���Ϊ��");
						textarea_in.setText(null);
					}
				}                                              /* �ڴ˻�Ҫע��TextArea��TextField����ENTER���Ĵ���ͬ��TextArea����������ǻ��з�"\r\n"�����ı����ݣ�
				                                                                                                                                                                             ��TextField�����ֱ�����ε�����Ϊ������Ҫ���У�*/
			}
			
		});	
		button_sent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(!textarea_in.getText().equals("\r\n")&&!textarea_in.getText().matches("^\\s*$")){
				    chatserver.sendMessage(textarea_in.getText());//������ENTER��ݼ�ʱ���ı�����ͺ��л��У�����in.readline()ֱ�ӿ��Զ�ȡ��
					 textarea_in.setText(null);                    
						}
						else{
							JOptionPane.showMessageDialog(textarea_in,"�Բ����������ݲ���Ϊ��");
							textarea_in.setText(null);
						}
					}                                              /* �ڴ˻�Ҫע��TextArea��TextField����ENTER���Ĵ���ͬ��TextArea����������ǻ��з�"\r\n"�����ı����ݣ�
					                                                                                                                                                                             ��TextField�����ֱ�����ε�����Ϊ������Ҫ���У�*/
		});
	
			this.setSize(300, 500);
			this.setLayout(grid);
			this.add(panel1);
			this.add(panel2);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);	
	}
    //�����趨��UI��Ӧ�����ݼ��������߳�
	public void setChatServer(ChatServer chatserver){   
		this.chatserver=chatserver;              
	}
	public static void main(String[] args) {
		
      ChatServerUI serverui=new ChatServerUI();
      serverui.setChatServer(new ChatServer(4444,serverui));
	}

}
