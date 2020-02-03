package main;

import java.awt.Canvas;

import javax.swing.JFrame;

/*
 * 
 * 
 * 
 * 
 */
public class Display {
	
	
	/*
	 * 
	 * 
	 */
	public Display(int startingWidth,int startingHeight,JFrame frame,Canvas canvas,String title) {
		frame.setSize(startingWidth,startingHeight);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		canvas.setSize(startingWidth,startingHeight);
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
		canvas.setVisible(true);
	}	
}
