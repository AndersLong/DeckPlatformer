package input;

import java.awt.Color;
import java.awt.Graphics;

public class Button {
	int x,y,w,h;
	String text;
	public Button(String text,int x,int y,int w,int h) {
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.text=text;
	}
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void draw(Graphics g, Color c) {
		g.setColor(c);
		g.fillRect(x, y, w, h);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, w, h);
		g.drawString(text, x+(w/2)-g.getFontMetrics().stringWidth(text)/2, y+h/2);	
	}

}
