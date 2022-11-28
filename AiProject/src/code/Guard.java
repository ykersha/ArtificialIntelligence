package code;

import java.util.ArrayList;

public class Guard extends Cell {

	int currentCapacity = 0, maxCapacity, blackBoxesCollected;

	public Guard(int x, int y, int maxCapacity) {
		super(x, y);
		this.maxCapacity = maxCapacity;
		this.currentCapacity = 0;
		this.blackBoxesCollected = 0;
	}

	public int getCurrentCapacity() {
		return currentCapacity;
	}

	public void setCurrentCapacity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public boolean goUp(int width, int height) {
		if (this.y > 0) {
			super.setY(super.getY() - 1);
			return true;
		}
		return false;
	}

	public boolean goDown(int width, int height) {
		if (this.y < height - 1) {
			super.setY(super.getY() + 1);
			return true;
		}
		return false;
	}

	public boolean goLeft(int width, int height) {
		if (this.x > 0) {
			super.setX(super.getX() - 1);
			return true;
		}
		return false;
	}

	public boolean goRight(int width, int height) {
		if (this.x < width - 1) {
			super.setX(super.getX() + 1);
			return true;
		}
		return false;
	}

}
