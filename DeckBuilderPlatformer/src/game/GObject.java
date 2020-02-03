package game;

import java.awt.Graphics;

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
	
	
}
