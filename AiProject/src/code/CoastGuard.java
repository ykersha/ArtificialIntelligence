package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CoastGuard extends SearchProblem {

	public CoastGuard(ArrayList<String> operators, SearchTreeNode initialState) {
		super(operators, initialState);
	}

//	int x, y, currentCapacity = 0, maxCapacity, blackBoxesCollected;
//
//	public CoastGuard(int x, int y, int maxCapacity, ArrayList<String> operators, SearchTreeNode initialState) {
//		super(operators, initialState);
//		this.x = x;
//		this.y = y;
//		this.maxCapacity = maxCapacity;
//		this.currentCapacity = 0;
//		this.blackBoxesCollected = 0;
//	}
//
//	public int getCurrentCapacity() {
//		return currentCapacity;
//	}
//
//	public void setCurrentCapacity(int currentCapacity) {
//		this.currentCapacity = currentCapacity;
//	}
//
//	public int getMaxCapacity() {
//		return maxCapacity;
//	}
//
//	public void setMaxCapacity(int maxCapacity) {
//		this.maxCapacity = maxCapacity;
//	}

	public static String genGrid() {

		Random rand = new Random();
		int n = rand.nextInt(11) + 5;
		int m = rand.nextInt(11) + 5;

		int capacity = rand.nextInt(71) + 30;

		// create a list containing all possible locations on the grid
		ArrayList<Cell> points = new ArrayList<Cell>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				points.add(new Cell(i, j));
			}
		}
		Collections.shuffle(points);

		// Assign a random location for the coast guard
		Cell coastGuard = points.remove(0);
		ArrayList<Cell> ships = new ArrayList<Cell>();
		ArrayList<Cell> stations = new ArrayList<Cell>();

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
		for (Cell p : stations) {
			stationsEncoding += p.x + "," + p.y + ",";
		}
		encoding += stationsEncoding.substring(0, stationsEncoding.length() - 1) + ";";

		String shipsEncoding = "";
		for (Cell p : ships) {
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

	public static Guard getGuard(String grid) {
		String[] splitGrid = grid.split(";");
		int capacity = Integer.parseInt(splitGrid[1]);
		int cgX = Integer.parseInt(splitGrid[2].split(",")[0]);
		int cgY = Integer.parseInt(splitGrid[2].split(",")[1]);
		Guard cg = new Guard(cgX, cgY, capacity);
		return cg;
	}

	public static ArrayList<Station> getStations(String grid) {
		String[] splitGrid = grid.split(";");
		ArrayList<Station> stations = new ArrayList<Station>();
		String[] stationCoordinates = splitGrid[3].split(",");

		for (int i = 0; i < stationCoordinates.length - 1; i += 2) {
			System.out.println(stationCoordinates[0]);
			int x = Integer.parseInt(stationCoordinates[i]);
			int y = Integer.parseInt(stationCoordinates[i + 1]);
			Station st = new Station(x, y);
			stations.add(st);
		}
		return stations;
	}

	public static ArrayList<Ship> getShips(String grid) {
		String[] splitGrid = grid.split(";");
		ArrayList<Ship> ships = new ArrayList<Ship>();
		String[] shipCoordinates = splitGrid[4].split(",");

		for (int i = 0; i < shipCoordinates.length - 1; i += 3) {
			System.out.println(shipCoordinates[0]);
			int x = Integer.parseInt(shipCoordinates[i]);
			int y = Integer.parseInt(shipCoordinates[i + 1]);
			int passengerCount = Integer.parseInt(shipCoordinates[i + 2]);
			Ship ship = new Ship(x, y, passengerCount);
			ships.add(ship);
		}
		return ships;
	}

	public static Cell[][] getGridArray(String grid, ArrayList<Station> stations, ArrayList<Ship> ships) {
		String[] splitGrid = grid.split(";");
		int width = Integer.parseInt(splitGrid[0].split(",")[0]);
		int height = Integer.parseInt(splitGrid[0].split(",")[1]);
		Cell[][] gridArray = new Cell[height][width];

		for (Ship ship : ships) {
			int x = ship.getX();
			int y = ship.getY();
			gridArray[x][y] = ship;
		}

		for (Station station : stations) {
			int x = station.getX();
			int y = station.getY();
			gridArray[x][y] = station;
		}

		return gridArray;
	}

	public static String Solve(String grid, String strategy, boolean visualize) {

		ArrayList<Station> stations = getStations(grid);
		ArrayList<Ship> ships = getShips(grid);
		Cell[][] gridArray = getGridArray(grid, stations, ships);
		Guard guard = getGuard(grid);

		ArrayList<String> operators = new ArrayList<String>();
		operators.add("pickup"); // cost 1
		operators.add("retrieve");// cost 1
		operators.add("drop"); // cost 1
		operators.add("up"); // cost 2
		operators.add("down"); // cost 2
		operators.add("left"); // cost 2
		operators.add("right"); // cost 2
		SearchTreeNode initialState = new SearchTreeNode(gridArray, guard, ships, stations, null, "", 0, 0);

		QingFn queue;
		switch (strategy) {
		case "BF":
			queue = new BFQueue();
			break;
		case "DF":
			queue = new DFQueue();
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

	public SearchTreeNode generalSearch(SearchProblem problem, QingFn queue) {

		queue.enqueue(problem.initialState);

		while (!queue.isEmpty()) {
			SearchTreeNode node = queue.dequeue();

			if (problem.goalTest(node.guard, node.ships)) {
				return node;
			} else {

			}

		}

		return null;
	}

	public ArrayList<SearchTreeNode> expand(SearchTreeNode node, ArrayList<String> operators) {

		Guard guard = node.guard;
		Cell[][] grid = node.grid;
		ArrayList<Ship> ships = node.ships;
		ArrayList<Station> stations = node.stations;
		ArrayList<SearchTreeNode> res = new ArrayList<SearchTreeNode>();

		for (String operator : operators) {
			switch (operator) {
			case "pickup":
				if (grid[guard.x][guard.y] instanceof Ship && guard.currentCapacity > 0) {
					Ship ship = (Ship) grid[guard.x][guard.y];
					if (!ship.isWreck()) {
						int passengersICanTake = Math.min(ship.getCurrentPassengerCount(), guard.currentCapacity);
						ship.setCurrentPassengerCount(ship.getCurrentPassengerCount() - passengersICanTake);
						guard.setCurrentCapacity(guard.getCurrentCapacity() - passengersICanTake);
					}

				}
				break;
			case "retrieve":
				break;
			}
		}

		res.add(new SearchTreeNode(grid, guard, ships, stations, node, "right", guard.currentCapacity,
				guard.blackBoxesCollected));

		return null;
	}

}