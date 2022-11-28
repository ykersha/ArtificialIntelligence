package code;

import java.io.Serializable;

public class Cell implements Serializable{

	int x;
	int y;
	
	public Cell(int x, int y) {
		super();
		this.x = x;
		this.y = y;
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
	public Cell() {
		// TODO Auto-generated constructor stub
	}

}
