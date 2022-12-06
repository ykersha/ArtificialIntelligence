package code;

public class Guard extends Cell {

	int currentCapacity, maxCapacity, blackBoxesCollected;

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
		if (this.x > 0) {
			super.setX(super.getX() - 1);
			return true;
		}
		return false;
	}

	public boolean goDown(int width, int height) {
		if (this.x < height - 1) {
			super.setX(super.getX() + 1);
			return true;
		}
		return false;
	}

	public boolean goLeft(int width, int height) {
		if (this.y > 0) {
			super.setY(super.getY() - 1);
			return true;
		}
		return false;
	}

	public boolean goRight(int width, int height) {
		if (this.y < width - 1) {
			super.setY(super.getY() + 1);
			return true;
		}
		return false;
	}

	public Guard copy() {
		Guard newG = new Guard(x, y, maxCapacity);
		newG.currentCapacity = currentCapacity;
		newG.blackBoxesCollected = blackBoxesCollected;
		return newG;
	}

	public String toString() {
		return x + "," + y + "," + currentCapacity + "," + blackBoxesCollected + ",";
	}
	
	public void appendStringBuilder(StringBuilder sb) {
		sb.append(x);
		sb.append(',');
		sb.append(y);
		sb.append(',');
		sb.append(currentCapacity);
		sb.append(',');
		sb.append(blackBoxesCollected);
		sb.append(',');
	}
}
