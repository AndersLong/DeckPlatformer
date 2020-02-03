package game;

import java.awt.Color;
import java.awt.Graphics;

import input.KeyWatcher;
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
		
	}
	public void move() {
		switch(Cycler.gravity) {
		case DOWN:
			if(!dcol) {
				yv++;
			}
			if(dcol&&KeyWatcher.upKeyDown) {
				yv=-10;
			}
			if(!lcol&&KeyWatcher.leftKeyDown) {
				xv=-5;
			}
			if(!rcol&&KeyWatcher.rightKeyDown) {
				xv=5;
			}
			if(!KeyWatcher.rightKeyDown&&!KeyWatcher.leftKeyDown) {
				xv=0;
			}
			break;
		case UP:
			if(!ucol) {
				yv--;
			}
			if(ucol&&KeyWatcher.downKeyDown) {
				yv=10;
			}
			if(!lcol&&KeyWatcher.leftKeyDown) {
				xv=-5;
			}
			if(!rcol&&KeyWatcher.rightKeyDown) {
				xv=5;
			}
			if(!KeyWatcher.rightKeyDown&&!KeyWatcher.leftKeyDown) {
				xv=0;
			}
			break;
			
		}
		
		if(KeyWatcher.spaceKeyDown&&!KeyWatcher.gravityAlreadySwitched) {
			switch(Cycler.gravity) {
			case DOWN:
				Cycler.gravity=GRAVITY.UP;
				KeyWatcher.gravityAlreadySwitched=true;
				break;
			case UP:
				Cycler.gravity=GRAVITY.DOWN;
				KeyWatcher.gravityAlreadySwitched=true;
				break;
			}
			
		}
		
		x+=xv;
		y+=yv;
		
	}
	public void draw(Graphics graphics) {
		graphics.setColor(Color.BLUE);
		graphics.fillRect(x, y, w, h);
		
	}

}
