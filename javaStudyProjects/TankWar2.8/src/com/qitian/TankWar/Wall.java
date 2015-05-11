package com.qitian.TankWar;
import java.util.*;
import java.awt.*;
public class Wall {
	
	private int x = 400;
	private int y = 200;
	private int width = 20;
	private int height = 300;
	private Color wallColor = Color.BLACK;
	private TankClient tc = null;
	
	public Wall(int x, int y, int width, int height, TankClient tc) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tc = tc;
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(wallColor);
		g.fillRect(x, y, width, height);
		g.setColor(c);
	}
	
	public Rectangle getRect(){
		return new Rectangle(x,y,width,height);
	}
}
