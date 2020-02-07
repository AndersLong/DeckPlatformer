package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import input.KeyWatcher;
import main.Looper;

public class Player extends GObject{
	int speed = 3;
	int jumpSpeed=10;
	public Player(int x, int y, ID id) {
		super(x, y, id);
		w=32;
		h=32;
		super.x*=Looper.scale;
		super.y*=Looper.scale;
		w*=Looper.scale;
		h*=Looper.scale;
		otherCol=new Rectangle(x+w/8,y+w/8,w/2,h/2);
		speed*=Looper.scale;
		jumpSpeed*=Looper.scale;

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
				yv=-jumpSpeed;
			}
			if(!lcol&&KeyWatcher.leftKeyDown) {
				xv=-speed;
			}
			if(!rcol&&KeyWatcher.rightKeyDown) {
				xv=speed;
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
				yv=jumpSpeed;
			}
			if(!lcol&&KeyWatcher.leftKeyDown) {
				xv=-speed;
			}
			if(!rcol&&KeyWatcher.rightKeyDown) {
				xv=speed;
			}
			if(!KeyWatcher.rightKeyDown&&!KeyWatcher.leftKeyDown) {
				xv=0;
			}
			break;
		case LEFT:
			if(!lcol) {
				xv--;
			}
			if(lcol&&KeyWatcher.rightKeyDown) {
				xv=jumpSpeed;
			}
			if(!ucol&&KeyWatcher.upKeyDown) {
				yv=-speed;
			}
			if(!dcol&&KeyWatcher.downKeyDown) {
				yv=speed;
			}
			if(!KeyWatcher.upKeyDown&&!KeyWatcher.downKeyDown) {
				yv=0;
			}
			break;
		case RIGHT:
			if(!rcol) {
				xv++;
			}
			if(rcol&&KeyWatcher.leftKeyDown) {
				xv=-jumpSpeed;
			}
			if(!ucol&&KeyWatcher.upKeyDown) {
				yv=-speed;
			}
			if(!dcol&&KeyWatcher.downKeyDown) {
				yv=speed;
			}
			if(!KeyWatcher.upKeyDown&&!KeyWatcher.downKeyDown) {
				yv=0;
			}
			break;

		}

		x+=xv;
		y+=yv;
		otherCol.x=x;
		otherCol.y=y;

	}
	public void draw(Graphics graphics) {
		graphics.setColor(Color.BLUE);
		graphics.fillRect(x, y, w, h);

	}

}
