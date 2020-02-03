package game;

import java.awt.Color;
import java.awt.Graphics;

import main.Looper;

public class Player extends GObject{
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		w=32;
		h=32;
		super.x*=Looper.scale;
		super.y*=Looper.scale;
		w*=Looper.scale;
		h*=Looper.scale;
				
	}
	public void update() {
		move();
		System.out.println(dcol);
		
	}
	public void move() {
		if(!dcol) {
			yv++;
		}
		x+=xv;
		y+=yv;
	}
	public void draw(Graphics graphics) {
		graphics.setColor(Color.BLUE);
		graphics.fillRect(x, y, w, h);
		
	}

}
