package game;

import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * THE GObject class has the purpose of representing all
 * objects in the game. This will include static objects such as blocks and
 * spikes, as well as power-ups (cards?) and mobile characters that will
 * for the rest of this project be referred to as Meeples
 * -Player
 * -Enemy
 * These GObjects will be distinguishable via their ID id
 * 
 */
public abstract class GObject {
	
	protected boolean lcol,rcol,ucol,dcol;
	protected int x,y,w,h,xv,yv;
	protected ID id;
		
	public GObject(int x, int y, int w, int h, ID id) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.id = id;
	}

	public GObject(int x,int y,ID id) {
		this(x,y,0,0,id);
	}
	
	public abstract void update();
	public abstract void draw(Graphics graphics);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getXv() {
		return xv;
	}

	public void setXv(int xv) {
		this.xv = xv;
	}

	public int getYv() {
		return yv;
	}

	public void setYv(int yv) {
		this.yv = yv;
	}

	public boolean isLcol() {
		return lcol;
	}

	public void setLcol(boolean lcol) {
		this.lcol = lcol;
	}

	public boolean isRcol() {
		return rcol;
	}

	public void setRcol(boolean rcol) {
		this.rcol = rcol;
	}

	public boolean isUcol() {
		return ucol;
	}

	public void setUcol(boolean ucol) {
		this.ucol = ucol;
	}

	public boolean isDcol() {
		return dcol;
	}

	public void setDcol(boolean dcol) {
		this.dcol = dcol;
	}
	
	
	
}
