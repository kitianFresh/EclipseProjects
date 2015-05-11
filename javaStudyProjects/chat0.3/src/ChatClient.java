import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ChatClient extends Frame {
	TextField tfInput = new TextField();
	TextArea taShow = new TextArea();
	Button btSent = new Button();
	
	public static void main(String[] args) {
		new ChatClient().launchFrame();

	}
	
	public void launchFrame(){
		this.setBounds(400,300, 300, 300);
		taShow.setBackground(Color.PINK);
		tfInput.addActionListener(new TFListener());
		this.add(taShow,BorderLayout.NORTH);
		this.add(tfInput,BorderLayout.CENTER);
		this.add(btSent,BorderLayout.PAGE_END);
		this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		pack();
		this.setVisible(true);
	}
	
	private class TFListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = tfInput.getText().trim();
			taShow.append(s);
			tfInput.setText("");
		}
		
	}

}
