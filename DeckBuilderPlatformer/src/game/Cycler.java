package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import file.LevelLoader;

/*
 * This class is responsible for handling all the game logic, below are several key concepts:
 * 1. the entirety of the game objects are contained in an arraylist, which is updated and redrawn
 * 60 fps.
 * 2. There is also a collision method, which checks the interactions between objects
 */

public class Cycler {

	ArrayList<GObject> objs;
	LevelLoader levLod;
	public static GRAVITY gravity;

	public Cycler() {
		init();
	}

	public void init() {
		gravity=GRAVITY.DOWN;
		levLod=new LevelLoader(this);
		objs=new ArrayList<GObject>();
		levLod.loadLevel("level1");
	}
	
	public void reset() {
		
	}
	
	public void loadNextLevel(String level) {
		objs.clear();
		levLod.loadLevel(level);
		gravity=GRAVITY.DOWN;
	}
	
	/*
	 * This function updates all the objects the array, and calls the collision function 
	 */

	public void update() {
		for(GObject obj:objs) {
			obj.update();
		}
		collision();
	}

	public void draw(Graphics graphics) {
		for(GObject obj:objs) {
			obj.draw(graphics);
		}
	}
	
	/*
	 * This function cycles through each object, and in turn checks all other objects
	 * to check collisions. It uses (touching instance of) to check if a certain side is touching
	 * another object, and touching to actually check connections 
	 */

	public void collision() {
		for(GObject obj:objs) {
			if(obj.getId()==ID.PLAYER) {
				if(touchingInstanceOf(obj,ID.BLOCK,"down")) {
					obj.setDcol(true);
					obj.setYv(0);
				}else {
					obj.setDcol(false);
				}
				if(touchingInstanceOf(obj,ID.BLOCK,"up")) {
					obj.setUcol(true);
					obj.setYv(0);
				}else {
					obj.setUcol(false);
				}
				if(touchingInstanceOf(obj,ID.BLOCK,"left")) {
					obj.setLcol(true);
					obj.setXv(0);
				}else {
					obj.setLcol(false);
				}
				if(touchingInstanceOf(obj,ID.BLOCK,"right")) {
					obj.setRcol(true);
					obj.setXv(0);
				}else {
					obj.setRcol(false);
				}
				
				if(touchingInstanceOf(obj,ID.PORTAL,"left")
					|| touchingInstanceOf(obj,ID.PORTAL,"right")
					|| touchingInstanceOf(obj,ID.PORTAL,"up")
					|| touchingInstanceOf(obj,ID.PORTAL,"down")) {
					loadNextLevel("level2");
					break;
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

	public boolean touchingInstanceOf(GObject a,ID otherId,String side) {
		for(GObject b:objs) {
			if(b.getId()==otherId) {
				switch(side) {
				case "down":
					if(touching(a,b)==1) {
						return true;
					}
					break;
				case "up":
					if(touching(a,b)==2) {
						return true;
					}
					break;
				case "left":
					if(touching(a,b)==3) {
						return true;
					}
					break;
				case "right":
					if(touching(a,b)==4) {
						return true;
					}
					break;
				}
			
				
			}
		}
		return false;
	}

	public int touching(GObject a,GObject b) {
		if((a.getX()+a.getW()/8>b.getX()&&a.getX()+a.getW()/8<b.getX()+b.getW()) ||  
			(a.getX()+a.getW()*7/8>b.getX()&&a.getX()+a.getW()*7/8<b.getX()+b.getW())){
			if(a.getY()+a.getH()+a.getYv()>b.getY()&&a.getY()+a.getH()+a.getYv()<b.getY()+b.getH()) {
				return 1;
			}
		}
		if((a.getX()+a.getW()/8>b.getX()&&a.getX()+a.getW()/8<b.getX()+b.getW()) ||  
				(a.getX()+a.getW()*7/8>b.getX()&&a.getX()+a.getW()*7/8<b.getX()+b.getW())){
				if(a.getY()+a.getYv()>b.getY()&&a.getY()+a.getYv()<b.getY()+b.getH()) {
					return 2;
				}
			}
		if(((a.getY()+a.getH()/8>b.getY()) && (a.getY()+a.getH()/8<b.getY()+b.getH()))
			|| (a.getY()+a.getH()*7/8>b.getY()) && (a.getY()+a.getH()*7/8<b.getY()+b.getH())) {
			if(a.getX()+a.getXv()>b.getX() && a.getX()+a.getXv()<b.getX()+b.getW()) {
				return 3;
			}
		}
		if(((a.getY()+a.getH()/8>b.getY()) && (a.getY()+a.getH()/8<b.getY()+b.getH()))
				|| (a.getY()+a.getH()*7/8>b.getY()) && (a.getY()+a.getH()*7/8<b.getY()+b.getH())) {
				if((a.getX()+a.getW()+a.getXv()>b.getX()) && (a.getX()+a.getW()+a.getXv()<b.getX()+b.getW())) {
					return 4;
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
		}else if(id==ID.PORTAL) {
			GObject portal=new Portal(x,y,id);
			objs.add(portal);
		}
	}
	public void addObject(ID id,int x,int y) {
		addObject(id,x,y,0,0);
	}
}
