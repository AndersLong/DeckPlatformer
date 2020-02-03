package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyWatcher implements KeyListener{
	static StringBuilder sb=new StringBuilder();
	
	public static boolean leftKeyDown,
		rightKeyDown,
		upKeyDown,
		downKeyDown,
		spaceKeyDown,
		zeroDown,
		oneDown,
		twoDown,
		threeDown,
		fourDown,
		fiveDown,
		sixDown,
		sevenDown,
		eightDown,
		nineDown,
		commaDown,
		gravityAlreadySwitched,
		enterDown;
	
	public KeyWatcher() {
		leftKeyDown=false;
		rightKeyDown=false;
		upKeyDown=false;
		downKeyDown=false;
		spaceKeyDown=false;
		zeroDown = false;
		oneDown=false;
		twoDown=false;
		threeDown=false;
		fourDown=false;
		fiveDown=false;
		sixDown=false;
		sevenDown=false;
		eightDown=false;
		nineDown=false;
		enterDown=false;
		commaDown=false;
		
	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==e.VK_UP) {
			upKeyDown=true;
		}
		if(e.getKeyCode()==e.VK_DOWN) {
			downKeyDown=true;
		}
		if(e.getKeyCode()==e.VK_LEFT) {
			leftKeyDown=true;
		}
		if(e.getKeyCode()==e.VK_RIGHT) {
			rightKeyDown=true;
		}
		if(e.getKeyCode()==e.VK_SPACE) {
			spaceKeyDown=true;
		}
		if(e.getKeyCode()==e.VK_1) {
			oneDown=true;
		}
		if(e.getKeyCode()==e.VK_2) {
			twoDown=true;
		}
		if(e.getKeyCode()==e.VK_3) {
			threeDown=true;
		}
		if(e.getKeyCode()==e.VK_4) {
			fourDown=true;
		}
		if(e.getKeyCode()==e.VK_5) {
			fiveDown=true;
		}
		if(e.getKeyCode()==e.VK_6) {
			sixDown=true;
		}
		if(e.getKeyCode()==e.VK_7) {
			sevenDown=true;
		}
		if(e.getKeyCode()==e.VK_8) {
			eightDown=true;
		}
		if(e.getKeyCode()==e.VK_9) {
			nineDown=true;
		}
		if(e.getKeyCode()==e.VK_0) {
			zeroDown=true;
		}
		if(e.getKeyCode()==e.VK_ENTER) {
			enterDown = true;
		}
		if(e.getKeyCode()==e.VK_COMMA) {
			commaDown = true;
		}
		
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==e.VK_UP) {
			upKeyDown=false;
		}
		if(e.getKeyCode()==e.VK_DOWN) {
			downKeyDown=false;
		}
		if(e.getKeyCode()==e.VK_LEFT) {
			leftKeyDown=false;
		}
		if(e.getKeyCode()==e.VK_RIGHT) {
			rightKeyDown=false;
		}	
		if(e.getKeyCode()==e.VK_SPACE) {
			spaceKeyDown=false;
			gravityAlreadySwitched=false;
		}
		if(e.getKeyCode()==e.VK_1) {
			oneDown=false;
		}
		if(e.getKeyCode()==e.VK_2) {
			twoDown=false;
		}
		if(e.getKeyCode()==e.VK_3) {
			threeDown=false;
		}
		if(e.getKeyCode()==e.VK_4) {
			fourDown=false;
		}
		if(e.getKeyCode()==e.VK_5) {
			fiveDown=false;
		}
		if(e.getKeyCode()==e.VK_6) {
			sixDown=false;
		}
		if(e.getKeyCode()==e.VK_7) {
			sevenDown=false;
		}
		if(e.getKeyCode()==e.VK_8) {
			eightDown=false;
		}
		if(e.getKeyCode()==e.VK_9) {
			nineDown=false;
		}
		if(e.getKeyCode()==e.VK_0) {
			zeroDown=false;
		}
		if(e.getKeyCode()==e.VK_ENTER) {
			enterDown=false;
		}
		if(e.getKeyCode()==e.VK_COMMA) {
			commaDown = false;
		}
	}
	public static void resetInput() {
		sb=new StringBuilder();
	}
	
}
