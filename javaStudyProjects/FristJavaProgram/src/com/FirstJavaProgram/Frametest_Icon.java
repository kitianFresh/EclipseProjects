package com.FirstJavaProgram;
import java.awt.*;

import javax.swing.*;
public class Frametest_Icon extends JFrame{
	GridLayout grid=new GridLayout();
	//private static ImageIcon icon=new ImageIcon("image/images.jpg");
	//JButton button=new JButton("�����о�ϲ");
	//JButton button=new JButton(icon);//����ͼ��ĵ�һ��д��
	JButton button=new JButton();
    Frametest_Icon(String title) {
	super(title);
	this.setSize(300, 200);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	//button.setForeground(Color.RED);
	//button.setBackground(Color.GREEN);
	this.setLayout(grid);
	this.add(button);
	button.setIcon(new ImageIcon("image/images.jpg"));//����ͼ��ĵڶ���д��
	button.setBounds((this.getWidth()-100)/2,(this.getHeight()-50)/2,100,50);
	//this.setBackground(Color.BLUE);
	this.setVisible(true);
  }
	public static void main(String[] args) {
		new Frametest_Icon("a new window");
	}

}
