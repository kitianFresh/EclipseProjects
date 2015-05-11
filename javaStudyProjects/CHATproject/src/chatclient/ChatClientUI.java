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
	JButton button_sent=new JButton("sent   ����ݼ�enter��");
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
				    chatclient.sendMessage(textarea_in.getText());//������ENTER��ݼ�ʱ���ı�����ͺ��л��У�����in.readline()ֱ�ӿ��Զ�ȡ��
					 textarea_in.setText(null);   
					}//������ˢ������ʱ��������println(),������������һ��\n,�ᵼ�·��������������
					}*/
				
			}

			public void keyPressed(KeyEvent e) {
				// TODO �Զ����ɵķ������
				
			}

			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){     
					if(!textarea_in.getText().equals("\r\n")&&!textarea_in.getText().matches("^\\s*$")){
			    chatclient.sendMessage(textarea_in.getText());//������ENTER��ݼ�ʱ���ı�����ͺ��л��У�����in.readline()ֱ�ӿ��Զ�ȡ��
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
		button_connect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(!connect){
				chatclient=new ChatClient("127.0.0.1", 4444,ChatClientUI.this);
				JOptionPane.showMessageDialog(button_connect,"��������˳�����ӣ�");
				ChatClientUI.this.setChatClient(chatclient);
				connect=true;
				}
				else
					JOptionPane.showMessageDialog(button_connect,"�Բ��𣡲�Ҫ�ظ����ӣ�");
			}
			
		});
		button_sent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(!textarea_in.getText().equals("\r\n")&&!textarea_in.getText().matches("^\\s*$")){
				    chatclient.sendMessage(textarea_in.getText());//������ENTER��ݼ�ʱ���ı�����ͺ��л��У�����in.readline()ֱ�ӿ��Զ�ȡ��
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
