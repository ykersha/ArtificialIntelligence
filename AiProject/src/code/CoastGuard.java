package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class CoastGuard extends SearchProblem {

	public CoastGuard(ArrayList<String> operators, SearchTreeNode initialState) {
		super(operators, initialState);
	}

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

	public static void visualize(Cell[][] gridArray, Guard guard, ArrayList<Ship> ships, ArrayList<Station> stations) {

		int n = gridArray.length;
		int m = gridArray[0].length;

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < m; j++) {
				System.out.printf("%-16s", "+----------------");
			}
			System.out.printf("+%n");

			for (int j = 0; j < m; j++) {
				if (gridArray[i][j] instanceof Ship && !(i == guard.getX() && j == guard.getY())) {
					Ship ship = (Ship) gridArray[i][j];
					if (ship.isWreck()) {
						if (ship.isBlackBoxRetrived()) {
							System.out.printf("|%-16s", "BB Retrieved");
						} else if (ship.isBlackBoxExpired()) {
							System.out.printf("|%-16s", "BB Expired");
						} else {
							System.out.printf("|%-16s", "Wreck(" + (20 - ship.getBlackBoxCounter()) + ")");
						}
					} else {
						System.out.printf("|%-16s", "Ship(" + ship.getCurrentPassengerCount() + ")");
					}

				} else if (gridArray[i][j] instanceof Station && !(i == guard.getX() && j == guard.getY())) {
					System.out.printf("|%-16s", "Station");

				} else if (gridArray[i][j] instanceof Ship && i == guard.getX() && j == guard.getY()) {
					Ship ship = (Ship) gridArray[i][j];
					if (ship.isWreck()) {
						if (ship.isBlackBoxRetrived()) {
							System.out.printf("|%-16s", "BB Retrieved*");
						} else if (ship.isBlackBoxExpired()) {
							System.out.printf("|%-16s", "BB Expired*");
						} else {
							System.out.printf("|%-16s", "Wreck(" + (20 - ship.getBlackBoxCounter()) + ")*");
						}
					} else {
						System.out.printf("|%-16s", "Ship(" + ship.getCurrentPassengerCount() + ")*");
					}
				} else if (gridArray[i][j] instanceof Station && (i == guard.getX() && j == guard.getY())) {
					System.out.printf("|%-16s", "Station*");
				} else if (i == guard.getX() && j == guard.getY()) {
					System.out.printf("|%-16s",
							"CGuard(" + guard.getCurrentCapacity() + ")" + "(" + guard.blackBoxesCollected + ")*");
				} else {
					System.out.printf("|%-16s", "");
				}
			}
			System.out.printf("|%n");
		}
		for (int j = 0; j < m; j++) {
			System.out.printf("%-16s", "+----------------");
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

	public static String solve(String grid, String strategy, boolean visualize) {

		ArrayList<Station> stations = getStations(grid);
		ArrayList<Ship> ships = getShips(grid);
		Cell[][] gridArray = getGridArray(grid, stations, ships);
		Guard guard = getGuard(grid);

		ArrayList<String> operators = new ArrayList<String>();
		operators.add("retrieve");// cost 1
		operators.add("drop"); // cost 1
		operators.add("pickup"); // cost 1
		operators.add("up"); // cost 2
		operators.add("down"); // cost 2
		operators.add("right"); // cost 2
		operators.add("left");
		SearchTreeNode initialState = new SearchTreeNode(gridArray, guard, ships, stations, null, "", 0,
				new int[] { 0, 0 });
		SearchProblem problem = new SearchProblem(operators, initialState);

		QingFn queue = null;
		switch (strategy) {
		case "BF":
			queue = new BFQueue();
			break;
		case "DF":
			queue = new DFQueue();
			break;
		case "ID":
			String res = IDSearch(problem, visualize);
			System.out.println(strategy + " " + res);
			return res;
		case "UC":
			queue = new UCQueue();
			break;
		case "GR1":
			queue = new GR1Queue();
			break;
		case "GR2":
			queue = new GR2Queue();
			break;
		case "AS1":
			queue = new AS1Queue();
			break;
		case "AS2":
			queue = new AS2Queue();
			break;
		default:
			System.out.println("Invalid Input");
			break;
		}

		String res = generalSearch(problem, queue, visualize);
		System.out.println(strategy + " " + res);

		return res;
	}

	private static Cell[][] getGridCopy(Cell[][] oldGrid, ArrayList<Ship> ships, ArrayList<Station> stations) {
		Cell[][] cellCopy = new Cell[oldGrid.length][oldGrid[0].length];
		for (Ship ship : ships) {
			int x = ship.getX();
			int y = ship.getY();
			cellCopy[x][y] = ship;
		}

		for (Station station : stations) {
			int x = station.getX();
			int y = station.getY();
			cellCopy[x][y] = station;
		}
		return cellCopy;
	}

	public static String generalSearch(SearchProblem problem, QingFn queue, boolean visualize) {

		int numExpanded = 0;
		queue.enqueue(problem.initialState);

		while (!queue.isEmpty()) {
			SearchTreeNode node = queue.dequeue();

			if (problem.goalTest(node.guard, node.ships)) {

				System.out.println("depth: " + node.depth + " ");
				System.out.println("Coast Guard Capacity: " + node.guard.maxCapacity + " ");

				String s = getPlan(node, visualize) + ";" + getDeaths(node) + ";" + node.guard.blackBoxesCollected + ";"
						+ numExpanded;
				return s;
			} else {
				ArrayList<SearchTreeNode> expandedNodes = expand(node, problem.operators);
				numExpanded++;
				for (SearchTreeNode expandedNode : expandedNodes) {
					queue.enqueue(expandedNode);
//					if (expandedNode.guard.getCurrentCapacity() > 0)
//						System.out.println("-------------------->x:" + expandedNode.guard.x + " y:"
//								+ expandedNode.guard.y + " " + expandedNode.operator + " ship:"
//								+ expandedNode.ships.get(0).getCurrentPassengerCount() + " box:"
//								+ expandedNode.ships.get(0).getBlackBoxCounter() + " Gcap:"
//								+ expandedNode.guard.getCurrentCapacity());
				}
			}
		}
		return numExpanded + "";
	}

	public static String IDSearch(SearchProblem problem, boolean visualize) {

		int numExpanded = 0;
		int depth = 0;
		while (true) {
			QingFn queue = new IDQueue(depth);
			String dlsResult = generalSearch(problem, queue, visualize);
			if (!dlsResult.contains(";")) {
				numExpanded += Integer.parseInt(dlsResult);
				depth++;
			} else {
				String[] resultComponents = dlsResult.split(";");
				numExpanded += Integer.parseInt(resultComponents[resultComponents.length - 1]);
				resultComponents[resultComponents.length - 1] = numExpanded + "";
				return String.join(";", resultComponents);
			}
		}
	}

	public static ArrayList<SearchTreeNode> expand(SearchTreeNode node, ArrayList<String> operators) {

		Guard guard = node.guard;
		Cell[][] grid = node.grid;
		ArrayList<Ship> ships = node.ships;
		ArrayList<Station> stations = node.stations;
		ArrayList<SearchTreeNode> res = new ArrayList<SearchTreeNode>();

		for (String operator : operators) {

			boolean actionDone = false;
			Guard guardCopy = guard.copy();
			ArrayList<Ship> shipsCopy = new ArrayList<Ship>();
			ArrayList<Station> stationsCopy = new ArrayList<Station>();
			for (Ship ship : ships) {
				shipsCopy.add(ship.copy());
			}
			for (Station station : stations) {
				stationsCopy.add(station.copy());
			}

			Cell[][] gridCopy = getGridCopy(grid, shipsCopy, stationsCopy);

			switch (operator) {
			case "pickup":
				if (grid[guard.x][guard.y] instanceof Ship && guard.currentCapacity < guard.maxCapacity) {
					Ship shipCopy = (Ship) gridCopy[guard.x][guard.y];
					if (!shipCopy.isWreck()) {
						int passengersICanTake = Math.min(shipCopy.getCurrentPassengerCount(),
								guardCopy.maxCapacity - guardCopy.currentCapacity);
						shipCopy.setCurrentPassengerCount(shipCopy.getCurrentPassengerCount() - passengersICanTake);
						guardCopy.setCurrentCapacity(guardCopy.getCurrentCapacity() + passengersICanTake);

						if (shipCopy.getCurrentPassengerCount() == 0) // bbCount starts 0 and timestep makes it 1
							shipCopy.setBlackBoxCounter(0);

						actionDone = true;
					}

				}
				break;
			case "retrieve":
				if (grid[guard.x][guard.y] instanceof Ship) {
					Ship shipCopy = (Ship) gridCopy[guard.x][guard.y];
					if (shipCopy.isWreck() && !shipCopy.isBlackBoxExpired() && !shipCopy.isBlackBoxRetrived()) {
						shipCopy.setBlackBoxCounter(0);
//						shipCopy.setBlackBoxExpired(false);
						shipCopy.setBlackBoxRetrived(true);
						guardCopy.blackBoxesCollected++;
						actionDone = true;
					}
				}
				break;
			case "drop":
				if (grid[guard.x][guard.y] instanceof Station) { // Do we check if the guard has passengers?
					Station stationCopy = (Station) gridCopy[guard.x][guard.y];
					if (guardCopy.getCurrentCapacity() > 0) {
						stationCopy
								.setPassengersSaved(stationCopy.getPassengersSaved() + guardCopy.getCurrentCapacity());
						guardCopy.setCurrentCapacity(0);
						actionDone = true;
					}
				}
				break;
			case "up":
				actionDone = guardCopy.goUp(grid[0].length, grid.length);
				break;
			case "down":
				actionDone = guardCopy.goDown(grid[0].length, grid.length);
				break;
			case "left":
				actionDone = guardCopy.goLeft(grid[0].length, grid.length);
				break;
			case "right":
				actionDone = guardCopy.goRight(grid[0].length, grid.length);
				break;
			}

			if (actionDone) {
				int deaths = 0;
				int bbExpired = 0;

				for (Ship ship : shipsCopy) {

					boolean bbBefore = ship.isBlackBoxExpired();
					boolean hasDied = ship.timestep();
					boolean bbAfter = ship.isBlackBoxExpired();

					if (hasDied)
						deaths++;

					if (!bbBefore && bbAfter)
						bbExpired++;
				}
				
				int[] pathCostCopy = {node.pathCost[0] + deaths, node.pathCost[1] + bbExpired};

				res.add(new SearchTreeNode(gridCopy, guardCopy, shipsCopy, stationsCopy, node, operator, node.depth + 1,
						pathCostCopy));
			}
		}

		return res;
	}

	public static String getPlan(SearchTreeNode node, boolean visualize) {
		String s = "";

		Stack<SearchTreeNode> st = new Stack<SearchTreeNode>();

		while (node != null) {
			s = "," + node.operator + s;
			st.push(node);
			node = node.parent;
		}

		if (visualize) {
			while (!st.isEmpty()) {
				SearchTreeNode cu = st.pop();

				if (cu.operator.length() > 0) {
					String num = cu.operator.equals("pickup")
							? (cu.guard.currentCapacity - cu.parent.guard.currentCapacity) + ""
							: cu.operator.equals("drop") ? cu.parent.guard.currentCapacity + "" : "";

					System.out.println("Action: " + cu.operator + " " + num);
					
				}

				visualize(cu.grid, cu.guard, cu.ships, cu.stations);
				System.out.println();
				System.out.println();
			}
		}

		return s.substring(2);
	}

	public static int getRetrieved(SearchTreeNode node) {
		int retrieved = 0;

		for (Station station : node.stations) {
			retrieved += station.getPassengersSaved();
		}
		return retrieved;
	}

	public static int getDeaths(SearchTreeNode node) {
		int retrieved = getRetrieved(node);
		int total = 0;
		for (Ship ship : node.ships) {
			total += ship.getInitialPassengerCount();
		}

		return total - retrieved;
	}


	public static void main(String[] args) {

		String grid0 = "5,6;50;0,1;0,4,3,3;1,1,90;";
		String grid1 = "6,6;52;2,0;2,4,4,0,5,4;2,1,19,4,2,6,5,0,8;";
		String grid2 = "7,5;40;2,3;3,6;1,1,10,4,5,90;";
		String grid3 = "8,5;60;4,6;2,7;3,4,37,3,5,93,4,0,40;";
		String grid4 = "5,7;63;4,2;6,2,6,3;0,0,17,0,2,73,3,0,30;";
		String grid5 = "5,5;69;3,3;0,0,0,1,1,0;0,3,78,1,2,2,1,3,14,4,4,9;";
		String grid6 = "7,5;86;0,0;1,3,1,5,4,2;1,1,42,2,5,99,3,5,89;";
		String grid7 = "6,7;82;1,4;2,3;1,1,58,3,0,58,4,2,72;";
		String grid8 = "6,6;74;1,1;0,3,1,0,2,0,2,4,4,0,4,2,5,0;0,0,78,3,3,5,4,3,40;";
		String grid9 = "7,5;100;3,4;2,6,3,5;0,0,4,0,1,8,1,4,77,1,5,1,3,2,94,4,3,46;";
		String grid10 = "10,6;59;1,7;0,0,2,2,3,0,5,3;1,3,69,3,4,80,4,7,94,4,9,14,5,2,39;";

		System.out.println();
		System.out.println();
		
		CoastGuard.solve(grid10, "GR1", true);
		CoastGuard.solve(grid10, "GR2", false);
		CoastGuard.solve(grid10, "AS1", false);
		CoastGuard.solve(grid10, "AS2", false);

	}

}