package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import file.LevelLoader;

/*
 * 
 * 
 * 
 * 
 */

public class Cycler {
	
	ArrayList<GObject> objs;
	LevelLoader levLod;

	public Cycler() {
		init();
	}
	
	public void init() {
		levLod=new LevelLoader(this);
		objs=new ArrayList<GObject>();
		levLod.loadLevel("level1");
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
	
	public void collision() {
		for(GObject obj:objs) {
			if(obj.getId()==ID.PLAYER) {
				for(GObject obj2:objs) {
					if(touching(obj,obj2)==1) {
						obj.setDcol(true);
						obj.setYv(0);
					}
				}
			}
		}
	}
	
	/*
	 * A function that takes two objects
	 * and checks for collision between them,
	 * it returns
	 * 0 if no collision
	 * 1 for left collision
	 * 2 for right collision
	 * 3 for bottom collision
	 * 4 for top collision
	 * 
	 */
	
	public int touching(GObject a,GObject b) {
		if((a.getX()+a.getW()/8<b.getX()+b.getW()&&(a.getX()+a.getW()/8>b.getX()))
				|| (a.getX()+a.getW()*7/8<b.getX()+b.getW()&&(a.getX()+a.getW()*7/8>b.getX()))){
			if(a.getY()+a.getYv()<b.getY()+b.getH()&&(a.getY()+a.getYv()>b.getY())) {
				return 1;
			}
		}
		return 0;
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
