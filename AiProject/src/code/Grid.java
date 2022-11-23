package code;

import java.util.*;

public class Grid {

	public Grid() {
		// TODO Auto-generated constructor stub
	}

	public static String genGrid() {

		Random rand = new Random();
		int n = rand.nextInt(11) + 5;
		int m = rand.nextInt(11) + 5;

		int capacity = rand.nextInt(71) + 30;

		// create a list containing all possible locations on the grid
		ArrayList<Point> points = new ArrayList<Point>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				points.add(new Point(i, j));
			}
		}
		Collections.shuffle(points);

		// Assign a random location for the coast guard
		Point coastGuard = points.remove(0);
		ArrayList<Point> ships = new ArrayList<Point>();
		ArrayList<Point> stations = new ArrayList<Point>();

		// place a ship and station since their number has to be >0
		ships.add(points.remove(0));
		stations.add(points.remove(0));

		// add additional stations and ships
		int r = rand.nextInt(points.size() / 3);
		while (r-- > 0) {
			stations.add(points.remove(0));
		}
		r = rand.nextInt(points.size() / 2);
		while (r-- > 0) {
			ships.add(points.remove(0));
		}

		// encode the grid as string
		String encoding = m + "," + n + ";" + capacity + ";" + coastGuard.x + "," + coastGuard.y + ";";

		String stationsEncoding = "";
		for (Point p : stations) {
			stationsEncoding += p.x + "," + p.y + ",";
		}
		encoding += stationsEncoding.substring(0, stationsEncoding.length() - 1) + ";";

		String shipsEncoding = "";
		for (Point p : ships) {
			shipsEncoding += p.x + "," + p.y + "," + (rand.nextInt(100) + 1) + ",";
		}
		encoding += shipsEncoding.substring(0, shipsEncoding.length() - 1) + ";";

		return encoding;
	}

	public static void visualize(String encoding) {

		String[] split = encoding.split(";");

		String[] dims = split[0].split(",");
		int m = Integer.parseInt(dims[0]);
		int n = Integer.parseInt(dims[1]);

		String[][] grid = new String[n][m];

		int capacity = Integer.parseInt(split[1]);

		String[] cgPos = split[2].split(",");
		int cgX = Integer.parseInt(cgPos[0]);
		int cgY = Integer.parseInt(cgPos[1]);

		grid[cgX][cgY] = "CGuard(" + capacity + ")";

		String[] stations = split[3].split(",");
		for (int i = 0; i < stations.length; i += 2) {
			int x = Integer.parseInt(stations[i]);
			int y = Integer.parseInt(stations[i + 1]);
			grid[x][y] = "Station";
		}

		String[] ships = split[4].split(",");
		for (int i = 0; i < ships.length; i += 3) {
			int x = Integer.parseInt(ships[i]);
			int y = Integer.parseInt(ships[i + 1]);
			int c = Integer.parseInt(ships[i + 2]);
			grid[x][y] = "Ship(" + c + ")";
		}

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < m; j++) {
				System.out.printf("%-11s", "+-----------");
			}

			System.out.printf("+%n");

			for (int j = 0; j < m; j++) {
				if (grid[i][j] != null) {
					System.out.printf("|%-11s", grid[i][j]);
				} else {
					System.out.printf("|%-11s", "");
				}
			}
			System.out.printf("|%n");
		}
		for (int j = 0; j < m; j++) {
			System.out.printf("%-11s", "+-----------");
		}
		System.out.printf("+%n");
	}

	public static String Solve(String grid, String strategy, boolean visualize) {
		String[] splitGrid = grid.split(";");
		int width = 0;
		int height = 0;
		int capacity = 0;
		int cgX = 0;
		int cgY = 0;
		ArrayList<Point> stationCoordinatesArray = new ArrayList<Point>();
		ArrayList<Ship> shipCoordinatesAndPassengersArray = new ArrayList<Ship>();
		String[] stationCoordinates = splitGrid[3].split(",");
		String[] shipCoordinatesAndPassengers = splitGrid[4].split(",");

		width = Integer.parseInt(splitGrid[0].split(",")[0]);
		height = Integer.parseInt(splitGrid[0].split(",")[1]);
		capacity = Integer.parseInt(splitGrid[1]);
		cgX = Integer.parseInt(splitGrid[2].split(",")[0]);
		cgY = Integer.parseInt(splitGrid[2].split(",")[1]);

		for (int i = 0; i < stationCoordinates.length - 1; i += 2) {
			System.out.println(stationCoordinates[0]);
			int x = Integer.parseInt(stationCoordinates[i]);
			int y = Integer.parseInt(stationCoordinates[i + 1]);
			stationCoordinatesArray.add(new Point(x, y));
		}

		for (int i = 0; i < shipCoordinatesAndPassengers.length - 1; i += 3) {
			System.out.println(shipCoordinatesAndPassengers[0]);
			int x = Integer.parseInt(shipCoordinatesAndPassengers[i]);
			int y = Integer.parseInt(shipCoordinatesAndPassengers[i + 1]);
			int passengerCount = Integer.parseInt(shipCoordinatesAndPassengers[i + 2]);
			shipCoordinatesAndPassengersArray.add(new Ship(x, y, passengerCount));
		}

		switch (strategy) {
		case "BF":
			System.out.print("BF");
			break;
		case "DF":
			System.out.print("DF");
			break;
		case "ID":
			System.out.print("ID");
			break;
		case "GR1":
			System.out.print("GR1");
			break;
		case "GR2":
			System.out.print("GR2");
			break;
		case "AS1":
			System.out.print("AS1");
			break;
		case "AS2":
			System.out.print("AS2");
			break;
		default:
			System.out.print("Invalid Input");
			break;
		}

//		System.out.println(width + " " + height + " "+ capacity + " " + cgX + " " + cgY + " " +
//				shipCoordinatesAndPassengersArray.get(0).getX() + " " + shipCoordinatesAndPassengersArray.get(0).getY() +
//				" " + shipCoordinatesAndPassengersArray.get(0).getPassengerCount()
//				+ " " + shipCoordinatesAndPassengersArray.get(1).getX() + " " 
//				+ shipCoordinatesAndPassengersArray.get(1).getY() + " " + shipCoordinatesAndPassengersArray.get(1).getPassengerCount());
//		
		return "";
	}

	public static void main(String[] args) {

		int i = 10;
		while (i-- > 0) {
			visualize(genGrid());
			System.out.println();
			System.out.println();
			System.out.println();
		}

	}

}
