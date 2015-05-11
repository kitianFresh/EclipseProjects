package gui;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class chat extends JFrame{
	JPanel panel1=new JPanel();
	JPanel panel2=new JPanel();
	GridLayout grid=new GridLayout(2,1);
	
	BorderLayout border1 =new BorderLayout();
	JLabel label1=new JLabel("history");
public 	TextArea textarea_out=new TextArea("",5,20,TextArea.SCROLLBARS_VERTICAL_ONLY);
	/*class mybutton extends JButton{
		mybutton(String s){
			super(s);
			enableEvents(AWTEvent.ACTION_EVENT_MASK);
		}
		protected void processActionEvent(ActionEvent e){
			System.out.println("sented!");
		}
	}*/
	
	BorderLayout border2 =new BorderLayout();
	TextArea textarea_in= new TextArea("",5,20,TextArea.SCROLLBARS_VERTICAL_ONLY);
	JLabel label2=new JLabel("input your message:");
	JButton button=new JButton("sent");
	void panel1(){
		textarea_out.setEditable(false);
		panel1.setLayout(border1);
		panel1.add(label1,BorderLayout.NORTH);
		panel1.add(textarea_out,BorderLayout.CENTER);
		
	
	}
	void panel2(){
		
		panel2.setLayout(border2);
		panel2.add(label2,BorderLayout.NORTH);
		panel2.add(textarea_in,BorderLayout.CENTER);
		panel2.add(button,BorderLayout.SOUTH);
		

	}
	
	chat(String title){
		super(title);
		this.setSize(500, 500);
		this.setLayout(grid);
		panel1();
		panel2();
		this.add(panel1);
		this.add(panel2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//label.setBounds(0,80,120,20);
		//label.setBounds(0, 20,label.getWidth(),label.getHeight());//²»¿ÉÐÐ
	    //textarea.setBounds(0,this.getHeight()/2,this.getWidth(),this.getHeight()/2);
	    //button.setBounds(120, 120, 100, 20);
	    //label.setForeground(Color.blue);
		this.setVisible(true);
	}
	
	
	
	public static void main(String arg[]){
		new chat("a chat window");
		
	}
}
