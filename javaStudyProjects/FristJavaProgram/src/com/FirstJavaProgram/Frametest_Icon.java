package com.FirstJavaProgram;
import java.awt.*;

import javax.swing.*;
public class Frametest_Icon extends JFrame{
	GridLayout grid=new GridLayout();
	//private static ImageIcon icon=new ImageIcon("image/images.jpg");
	//JButton button=new JButton("点我有惊喜");
	//JButton button=new JButton(icon);//载入图标的第一种写法
	JButton button=new JButton();
    Frametest_Icon(String title) {
	super(title);
	this.setSize(300, 200);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	//button.setForeground(Color.RED);
	//button.setBackground(Color.GREEN);
	this.setLayout(grid);
	this.add(button);
	button.setIcon(new ImageIcon("image/images.jpg"));//载入图标的第二中写法
	button.setBounds((this.getWidth()-100)/2,(this.getHeight()-50)/2,100,50);
	//this.setBackground(Color.BLUE);
	this.setVisible(true);
  }
	public static void main(String[] args) {
		new Frametest_Icon("a new window");
	}

}
