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


}
