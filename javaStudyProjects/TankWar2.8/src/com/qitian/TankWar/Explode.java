package com.qitian.TankWar;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
public class Explode {
	private boolean live = true;
	private int x;
	private int y;
	private TankClient tc = null;
	private int step = 0;
	private static boolean init = false;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] images = {
		tk.getImage(Explode.class.getClassLoader().getResource("images/0.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images/1.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images/2.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images/3.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images/4.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images/5.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images/6.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images/7.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images/8.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images/9.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images/10.gif")),
	};//这里最好设置成static，否则每次生成Explode对象就会加载一次图片到内存
	
	public Explode(int x, int y, TankClient tc){
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	
	public void draw(Graphics g){
		if(!init){	
			for (int i = 0; i < images.length; i++) {
				g.drawImage(images[step], -100, -100, null);
			}
			init = true;
		}
		
		if(!live) {
			tc.explodes.remove(this);
			return;
		}
		if(step>=images.length){
			live = false;
			step = 0;
			return;
		}
		g.drawImage(images[step], x, y, null);
		step ++;
	}
}
