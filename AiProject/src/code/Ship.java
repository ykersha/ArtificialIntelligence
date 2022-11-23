package code;
public class Ship {

	int x;
	int y;
	int passengerCount;

	public Ship(int x, int y, int passengerCount) {
		super();
		this.x = x;
		this.y = y;
		this.passengerCount = passengerCount;
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

	public int getPassengerCount() {
		return passengerCount;
	}

	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}

	public Ship() {
	}

}