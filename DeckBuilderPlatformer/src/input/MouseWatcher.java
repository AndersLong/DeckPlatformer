package input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import main.GAME_STATE;
import main.Looper;



public class MouseWatcher implements MouseListener{
	Button gameButton,
	helpButton,
	backButton,
	loadButton,
	storeButton;
	public static boolean allowed=true;




	public MouseWatcher(Button gameButton, 
			Button helpButton, 
			Button backButton, 
			Button loadButton,
			Button storeButton) {

		this.gameButton = gameButton;
		this.helpButton = helpButton;
		this.backButton = backButton;
		this.loadButton = loadButton;
		this.storeButton = storeButton;
	}

	public void mousePressed(MouseEvent e) {
		switch(Looper.state) {
		case MAIN_MENU:
			if(mouseClickInsideButton(e,gameButton)) {
				Looper.state=GAME_STATE.GAME;
			}
			if(mouseClickInsideButton(e,helpButton)) {
				Looper.state=GAME_STATE.HELP_MENU;
			}
			if(mouseClickInsideButton(e,loadButton)) {
				Looper.state=GAME_STATE.LOAD;
			}
			if(mouseClickInsideButton(e,storeButton)) {
				Looper.state=GAME_STATE.STORE;
			}
			break;
		case LOAD:
			if(mouseClickInsideButton(e,backButton)) {
				Looper.state=GAME_STATE.MAIN_MENU;
			}
			break;
		case HELP_MENU:
			if(mouseClickInsideButton(e,backButton)) {
				Looper.state=GAME_STATE.MAIN_MENU;
			}
			break;
		case STORE:
			if(mouseClickInsideButton(e,backButton)) {
				Looper.state=GAME_STATE.MAIN_MENU;
			}
			break;
		case GAME:

		case CREDITS:

		}
	}
	public boolean mouseClickInsideButton(MouseEvent e,Button b) {
		if(allowed) {
			if(e.getX()>b.getX()&&e.getX()<b.getX()+b.getW()) {
				if(e.getY()>b.getY()&&e.getY()<b.getY()+b.getH()) {
					allowed=false;
					return true;
				}
			}
		}
		return false;
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {
		allowed=true;
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
