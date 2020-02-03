package game;

import java.awt.Color;
import java.awt.Graphics;

import main.Looper;

public class Block extends GObject{
	
	public Block(int x, int y, ID id) {
		super(x, y, id);
		w=32;
		h=32;
		super.x*=Looper.scale;
		super.y*=Looper.scale;
		w*=Looper.scale;
		h*=Looper.scale;
	}

	public void update() {
		System.out.println("x" + x);
	}

	public void draw(Graphics graphics) {
		graphics.setColor(Color.DARK_GRAY);
		graphics.fillRect(x, y, w, h);
	}
	
}
