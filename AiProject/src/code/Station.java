package code;

public class Station extends Cell {


	int passengersSaved = 0;
	
	public Station(int x, int y) {
		super(x, y);
	}
	

	public int getPassengersSaved() {
		return passengersSaved;
	}

	public void setPassengersSaved(int passengersSaved) {
		this.passengersSaved = passengersSaved;
	}
	

}
