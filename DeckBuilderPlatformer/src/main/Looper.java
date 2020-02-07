package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import game.Cycler;
import input.Button;
import input.KeyWatcher;
import input.MouseWatcher;

/*
 * The purpose of the Looper class is to initialize the core aspects of the game
 * -Art
 * -Display
 * -Cycler
 * -The game logic is not handled in this class, but it is updated by the constant
 * redrawing and updating in the classes loop function * 
 */

public class Looper implements Runnable{
	
	/*
	 * FIELDS, These are organized in three blocks
	 * -static constants
	 * -objects
	 * -private logic numbers
	 * -enumerated values
	 */
	
	public static int scale=1;
	
	private Button gameButton,
		helpButton,
		backButton,
		loadButton,
		storeButton;
					
	private MouseWatcher mw;
	private KeyWatcher kw;
	private Cycler cycler;
	private Canvas canvas;
	private JFrame frame;
	private Thread thread;
	private String title="Decker";
	
	private boolean running;
	public static int sWid=320,sHei=320;
	public static long pauseTime=1000/60;
	private int buttonWidth=sWid*scale/4;
	private int buttonHeight=sHei*scale/8;
	private int centeredButtonX=sWid*scale/2-buttonWidth/2;
	
	public static GAME_STATE state;
	
	/*
	 *  Below is the initializing logic of the class.
	 *  the construction calls an instance of the Display class, which initializes
	 *  the canvas and frame instances of this class, as well as creates the window for the game
	 *  
	 *  The Start method initializes and starts the main thread of the class
	 *  and the init method initializes most of the objects used by the rest of the game 
	 */
	
	public Looper() {
		frame=new JFrame(title);
		canvas=new Canvas();
		new Display(sWid*scale,sHei*scale,frame,canvas,title);
		start();
	}
	
	public void init() {
		state=GAME_STATE.MAIN_MENU;
		gameButton=new Button("START",
							centeredButtonX,
							20,
							buttonWidth,
							buttonHeight);
		helpButton=new Button("HELP",
							centeredButtonX,
							20+buttonHeight+10,
							buttonWidth,
							buttonHeight);
		backButton=new Button("BACK",
							centeredButtonX,
							20,
							buttonWidth,
							buttonHeight);
		loadButton=new Button("LOAD",
							centeredButtonX,
							20+2*buttonHeight+20,
							buttonWidth,
							buttonHeight);
		storeButton=new Button("store",
							centeredButtonX,
							20+3*buttonHeight+30,
							buttonWidth,
							buttonHeight);
		mw=new MouseWatcher(gameButton,
							helpButton,
							backButton,
							loadButton,
							storeButton);
		kw=new KeyWatcher();
		canvas.addMouseListener(mw);
		canvas.addKeyListener(kw);
		cycler=new Cycler();
		
	}
	
	public void start() {
		Thread thread=new Thread(this);
		running=true;
		thread.start();
	}
	
	/*
	 * Below is the main constrol structure of the game. Run loops until the program is killed, and it
	 * continuously calls the
	 * -update
	 * -draw
	 * -pause
	 * functions to simulate the world of the game
	 * 
	 */
	
	public void run() {
		init();
		while(running) {
			update();
			draw();
			pause();
		}
	}
	public void update() {
		switch(state) {
		case MAIN_MENU:
			break;
		case STORE:
			break;
		case LOAD:
			break;
		case HELP_MENU:
			break;
		case GAME:
			cycler.update();
			break;
		case CREDITS:
			break;
		}
	}
	public void draw() {
		if(canvas.getBufferStrategy()==null) {
			canvas.createBufferStrategy(3);
		}
		BufferStrategy bs=canvas.getBufferStrategy();
		Graphics graphics=bs.getDrawGraphics();
		drawManager(graphics);		
		bs.show();
		graphics.dispose();
	}
	public void drawManager(Graphics graphics) {
		switch(state) {
		case MAIN_MENU:
			mainMenuDraw(graphics);
			break;
		case STORE:
			loadDraw(graphics);
			break;
		case LOAD:
			loadDraw(graphics);
			break;
		case HELP_MENU:
			loadDraw(graphics);
			break;
		case GAME:
			gameDraw(graphics);
			break;
		case CREDITS:
			break;
		}
	}
	public void gameDraw(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, sWid*scale, sHei*scale);
		cycler.draw(graphics);
	}
	public void mainMenuDraw(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.fillRect(0, 0, sWid*scale, sHei*scale);
		gameButton.draw(graphics, Color.GREEN);
		helpButton.draw(graphics, Color.GREEN);
		loadButton.draw(graphics, Color.GREEN);
		storeButton.draw(graphics, Color.GREEN);
	}	
	public void loadDraw(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.fillRect(0, 0, sWid*scale, sHei*scale);
		backButton.draw(graphics, Color.GREEN);
	}
	
	public void pause() {
		try {
			thread.sleep(pauseTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
