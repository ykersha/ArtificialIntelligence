package code;

import java.io.Serializable;

public class Ship extends Cell {

	private int currentPassengerCount;
	private int initialPassengerCount;
	private boolean isWreck;
	private int blackBoxCounter;
	private boolean blackBoxExpired;

	public Ship(int x, int y, int passengerCount) {
		super(x, y);
		this.currentPassengerCount = passengerCount;
		this.initialPassengerCount = passengerCount;
		isWreck = false;
		blackBoxCounter = 0;
		blackBoxExpired = false;
	}

	public int getCurrentPassengerCount() {
		return currentPassengerCount;
	}

	public void setCurrentPassengerCount(int passengerCount) {
		this.currentPassengerCount = passengerCount;
		if (this.currentPassengerCount == 0) {
			isWreck = true;

		} else {
			isWreck = false;
		}
	}

	public boolean isWreck() {
		return isWreck;
	}

	public boolean isBlackBoxExpired() {
		return blackBoxExpired;
	}

	public void setBlackBoxExpired(boolean blackBoxExpired) {
		this.blackBoxExpired = blackBoxExpired;
	}

	public int getBlackBoxCounter() {
		return blackBoxCounter;
	}

	public void setBlackBoxCounter(int blackBoxCounter) {
		this.blackBoxCounter = blackBoxCounter;
		if (this.blackBoxCounter == 20) {
			blackBoxExpired = true;
		}
	}

	public void timestep() {
		if (isWreck) {
			// blackbox logic
			if (!blackBoxExpired) {
				setBlackBoxCounter(blackBoxCounter + 1);
			}
		} else {
			// decrement passengers
			setCurrentPassengerCount(currentPassengerCount - 1);
		}

	}

	public int getInitialPassengerCount() {
		return initialPassengerCount;
	}

	public Ship copy() {
		Ship newShip = new Ship(x, y, currentPassengerCount);
		newShip.blackBoxCounter = blackBoxCounter;
		newShip.blackBoxExpired = blackBoxExpired;
		newShip.currentPassengerCount = currentPassengerCount;
		newShip.initialPassengerCount = initialPassengerCount;
		newShip.isWreck = isWreck;
		return newShip;
	}

	public String toString() {
		return currentPassengerCount + "," + blackBoxCounter + ",";
	}

	public void appendStringBuilder(StringBuilder sb) {
		sb.append(currentPassengerCount);
		sb.append(",");
		sb.append(blackBoxCounter);
		sb.append(",");
	}
}