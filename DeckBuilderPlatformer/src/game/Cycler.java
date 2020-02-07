package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import file.LevelLoader;
import file.Music;
import input.KeyWatcher;
import main.Looper;

/*
 * This class is responsible for handling all the game logic, below are several key concepts:
 * 1. the entirety of the game objects are contained in an arraylist, which is updated and redrawn
 * 60 fps.
 * 2. There is also a collision method, which checks the interactions between objects
 */

public class Cycler {

	Music music;
	ArrayList<GObject> objs;
	LevelLoader levLod;
	public static GRAVITY gravity;
	String level;
	String hint;
	int gravityTimer;

	public Cycler() {
		init();
	}

	public void init() {
		
		gravity=GRAVITY.DOWN;
		levLod=new LevelLoader(this);
		objs=new ArrayList<GObject>();
		level="level1";
		hint="arrow keys to move";
		levLod.loadLevel(level);
//		music=new Music();
//		music.setFile("megamanTheme.mp4");
//		music.start();
	}

	public void reset() {

	}

	public void loadNextLevel(String level) {
		objs.clear();
		gravity=GRAVITY.DOWN;
		levLod.loadLevel(level);
		
	}

	/*
	 * This function updates all the objects the array, and calls the collision function 
	 */

	public void update() {
		for(GObject obj:objs) {
			obj.update();
		}
		collision();
		gravity();
	}
	
	public void gravity() {
		if(KeyWatcher.wKeyDown&&!KeyWatcher.gravityAlreadySwitched) {
			Cycler.gravity=GRAVITY.UP;
			Looper.pauseTime=5000/60;
			gravityTimer=4;
		}
		if(KeyWatcher.aKeyDown&&!KeyWatcher.gravityAlreadySwitched) {
			Cycler.gravity=GRAVITY.LEFT;
			Looper.pauseTime=5000/60;
			gravityTimer=4;
		}
		if(KeyWatcher.sKeyDown&&!KeyWatcher.gravityAlreadySwitched) {
			Cycler.gravity=GRAVITY.DOWN;
			Looper.pauseTime=5000/60;
			gravityTimer=4;
		}
		if(KeyWatcher.dKeyDown&&!KeyWatcher.gravityAlreadySwitched) {
			Cycler.gravity=GRAVITY.RIGHT;
			Looper.pauseTime=5000/60;
			gravityTimer=4;
		}
		if(gravityTimer==0) {
			Looper.pauseTime=1000/60;
		}else {
			gravityTimer--;
		}

	}
	public void countDown() {
		
	}

	public void draw(Graphics graphics) {
		for(GObject obj:objs) {
			obj.draw(graphics);
		}
		graphics.setColor(Color.WHITE);
		graphics.drawString(hint, 0, 32);
	}

	/**
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
				if(touchingInGeneral(obj,ID.PORTAL)) {
					updateLevel();
					loadNextLevel(level);
					break;
				}
				if(RectsIntersect(obj,ID.SPIKES)) {
					loadNextLevel(level);
					break;
				}				
				if(outsideScrn(obj)) {
					loadNextLevel(level);
					break;
				}

			}
		}
	}
	
	public boolean RectsIntersect(GObject obj, ID id) {
		for(GObject obj2: objs) {
			if(obj2.getId()==id) {
				if(obj.getOtherCol().intersects(obj2.getOtherCol())) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean touchingInGeneral(GObject obj,ID id) {
		if(touchingInstanceOf(obj,id,"left")
				|| touchingInstanceOf(obj,id,"right")
				|| touchingInstanceOf(obj,id,"up")
				|| touchingInstanceOf(obj,id,"down")) {
			return true;
		}
		return false;
			
	}
	
	/**
	 * This function is responsible for updating the levels
	 * using one main switch statement, it checks to see the current level, updates the
	 * level variable, and updates the hint string
	 * 
	 */

	public void updateLevel() {
		switch(level) {
		case "level1":
			level="level2";
			hint="up arrow can make you jump! That's pretty nifty";
			break;
		case "level2":
			level="level3";
			hint="hmm, falling into that pit is probably pretty nasty";
			break;
		case "level3":
			level="level4";
			hint="that's two pits! This game sure has a good difficulty curve";
			break;
		case "level4":
			level="level5";
			hint="well. That's a game changer. I wonder what the 'W' key does?";
			break;
		case "level5":
			level="level6";
			hint="hmmmm. maybe 'D' changes gravity too...";
		case "level6":
			level="level7";
			hint="I bet WASD set gravity up,left,down,and right";
			break;
		}
	}
	public boolean outsideScrn(GObject obj) {
		if(obj.getX()+obj.getW()<0||obj.getX()>Looper.sWid*Looper.scale) {
			return true;
		}
		if(obj.getY()+obj.getH()<0||obj.getY()>Looper.sHei*Looper.scale) {
			return true;
		}
		return false;
	}

	/**
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
				if(touching(a,b,side)) {
					return true;
				}
			}
		}
		return false;
	}
	

	public boolean touching(GObject a,GObject b,String collider) {
		switch(collider) {
		case "down":
			if((a.getX()+a.getW()/8>b.getX()&&a.getX()+a.getW()/8<b.getX()+b.getW()) ||  
					(a.getX()+a.getW()*7/8>b.getX()&&a.getX()+a.getW()*7/8<b.getX()+b.getW())){
				if(a.getY()+a.getH()+a.getYv()>b.getY()&&a.getY()+a.getH()+a.getYv()<b.getY()+b.getH()) {
					return true;
				}
			}
			break;
		case "up":
			if((a.getX()+a.getW()/8>b.getX()&&a.getX()+a.getW()/8<b.getX()+b.getW()) ||  
					(a.getX()+a.getW()*7/8>b.getX()&&a.getX()+a.getW()*7/8<b.getX()+b.getW())){
				if(a.getY()+a.getYv()>b.getY()&&a.getY()+a.getYv()<b.getY()+b.getH()) {
					return true;
				}
			}
			break;
		case "left":
			if((a.getY()+a.getH()/8>b.getY() && a.getY()+a.getH()/8<b.getY()+b.getH())
					|| (a.getY()+a.getH()*7/8>b.getY() && a.getY()+a.getH()*7/8<b.getY()+b.getH())) {
				if(a.getX()+a.getXv()>b.getX() && a.getX()+a.getXv()<b.getX()+b.getW()) {
					return true;
				}
			}
			break;
		case "right":
			if(((a.getY()+a.getH()/8>b.getY()) && (a.getY()+a.getH()/8<b.getY()+b.getH()))
					|| (a.getY()+a.getH()*7/8>b.getY()) && (a.getY()+a.getH()*7/8<b.getY()+b.getH())) {
				if((a.getX()+a.getW()+a.getXv()>b.getX()) && (a.getX()+a.getW()+a.getXv()<b.getX()+b.getW())) {
					return true;
				}
			}
			break;
		}		
		return false;
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
		}else if(id==ID.SPIKES) {
			GObject spikes=new Spike(x,y,id);
			objs.add(spikes);
		}
	}
	public void addObject(ID id,int x,int y) {
		addObject(id,x,y,0,0);
	}
	public LevelLoader getLevLod() {
		return levLod;
	}
}

