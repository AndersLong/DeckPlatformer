package game;

import java.awt.Graphics;
import java.util.ArrayList;

/*
 * 
 * 
 * 
 * 
 */

public class Cycler {
	
	ArrayList<GObject> objs;

	public Cycler() {
		init();
	}
	
	public void init() {
		objs=new ArrayList<GObject>();
		addObject(ID.BLOCK,32,32);
		addObject(ID.PLAYER,32,64);
		addObject(ID.BLOCK,64,64);
	}
	
	public void update() {
		for(GObject obj:objs) {
			obj.update();
		}
	}
	
	public void draw(Graphics graphics) {
		for(GObject obj:objs) {
			obj.draw(graphics);
		}
	}
	
	public void addObject(ID id,int x,int y,int xv,int yv) {
		if(id==ID.BLOCK) {
			GObject block=new Block(x,y,id);
			objs.add(block);
		}else if(id==ID.PLAYER) {
			GObject player=new Player(x,y,id);
			objs.add(player);
		}
	}
	public void addObject(ID id,int x,int y) {
		addObject(id,x,y,0,0);
	}
}
