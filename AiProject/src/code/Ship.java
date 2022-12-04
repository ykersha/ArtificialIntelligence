package code;

public class Ship extends Cell {

	private int currentPassengerCount;
	private int initialPassengerCount;
	private boolean isWreck;
	private int blackBoxCounter;
	private boolean blackBoxExpired;
	private boolean blackBoxRetrived;


	public Ship(int x, int y, int passengerCount) {
		super(x, y);
		this.currentPassengerCount = passengerCount;
		this.initialPassengerCount = passengerCount;
		isWreck = false;
		blackBoxCounter = 0;
		blackBoxExpired = false;
		blackBoxRetrived = false;
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

	public boolean timestep() { //returns true iff a person dies on this ship in this timestep
		if (isWreck) {
			// blackbox logic
			if (!blackBoxExpired && !blackBoxRetrived) {
				setBlackBoxCounter(blackBoxCounter + 1);
			}
			return false;
		} else {
			// decrement passengers
			setCurrentPassengerCount(currentPassengerCount - 1);
			return true;
		}

	}

	public int getInitialPassengerCount() {
		return initialPassengerCount;
	}
	
	public boolean isBlackBoxRetrived() {
		return blackBoxRetrived;
	}

	public void setBlackBoxRetrived(boolean blackBoxRetrived) {
		this.blackBoxRetrived = blackBoxRetrived;
	}

	public Ship copy() {
		Ship newShip = new Ship(x, y, currentPassengerCount);
		newShip.blackBoxCounter = blackBoxCounter;
		newShip.blackBoxExpired = blackBoxExpired;
		newShip.currentPassengerCount = currentPassengerCount;
		newShip.initialPassengerCount = initialPassengerCount;
		newShip.isWreck = isWreck;
		newShip.blackBoxRetrived = blackBoxRetrived;
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