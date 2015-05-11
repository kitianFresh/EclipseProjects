package gui;
import java.awt.*;
public class login {

	public static void main(String[] args) {
		Frame f=new Frame("user login");
		f.setSize(280,150);
		f.setLayout(null);
		Button b=new Button("login in");
		b.setBounds((f.getWidth()-100)/2,(f.getHeight()-50)/2,100,50);
		f.add(b);
		f.setBackground(Color.blue);
		f.setVisible(true);

	}

}
