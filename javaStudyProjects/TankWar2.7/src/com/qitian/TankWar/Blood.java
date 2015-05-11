package com.qitian.TankWar;
import java.awt.*;
import java.util.*;
public class Blood {
	private int x,y,w = 30,h = 10;
	private int step = 0;
	private  boolean live = true;
	TankClient tc = null;
	private int[][] pos = {
			{350, 300}, {360, 300}, {375, 275}, {400, 200}, {360, 270}, {365, 290}, {340, 280}
					  };
	
	public void draw(Graphics g){
		if(!live) return;
		Color c = g.getColor();
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, w, h);
		g.setColor(c);
		move();
	}
	
	public Blood(){
		x = pos[0][0];
		y = pos[0][1];
	}
	
	public void move(){
		step ++;
		if(step >= pos.length){
			step = 0;
		}
		x = pos[step][0];
		y = pos[step][1];
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, w , h);
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
}
