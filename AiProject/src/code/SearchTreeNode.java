package code;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchTreeNode {

	Cell[][] grid;
	Guard guard;
	ArrayList<Ship> ships;
	ArrayList<Station> stations;

	SearchTreeNode parent;
	String operator;
	int depth;
	int[] pathCost; // [deaths, expired boxes]

	public SearchTreeNode(Cell[][] grid, Guard guard, ArrayList<Ship> ships, ArrayList<Station> stations,
			SearchTreeNode parent, String operator, int depth, int[] pathCost) {
		super();
		this.grid = grid;
		this.guard = guard;
		this.ships = ships;
		this.stations = stations;
		this.parent = parent;
		this.operator = operator;
		this.depth = depth;
		this.pathCost = pathCost;
	}

	public int expiredHeuristic() {
		int expired = 0;

		for (Ship ship : ships) {
			if (ship.isWreck() && !ship.isBlackBoxRetrived() && !ship.isBlackBoxExpired()) {
				int distance = Math.abs(ship.x - guard.x) + Math.abs(ship.y - guard.y);
				if (20 - ship.getBlackBoxCounter() <= distance) {
					expired++;
				}
			}
		}

		return expired;
	}
	
	public int deathHeuristic1() {
		int deaths = 0;
		for (Ship ship : ships) {
			int distance = Math.abs(ship.x - guard.x) + Math.abs(ship.y - guard.y);
			deaths += Math.min(distance, ship.getCurrentPassengerCount());
		}

		return deaths;
	}

	public int deathHeuristic2() {
		int deaths = 0;
		int[] passengersLeft = new int[ships.size()];

		boolean guardOnAShip = false;
		for (int i = 0; i < ships.size(); i++) {
			passengersLeft[i] = ships.get(i).getCurrentPassengerCount();
			if (guard.x == ships.get(i).x && guard.y == ships.get(i).y) {
				guardOnAShip = true;
			}
		}
		Arrays.sort(passengersLeft);

		int remCapacity = guard.maxCapacity - guard.currentCapacity;

		int i = 0;
		while (i < passengersLeft.length && passengersLeft[i] == 0) {
			i++;
		}

		while (i < passengersLeft.length) {
//			System.out.print("[");
//			for (int x : passengersLeft)
//				System.out.print(x + " ");
//			System.out.println("]");

			// teleport to ship and pickup passengers if possible
			if (passengersLeft[i] > 0 && remCapacity > 0) {

				// teleport
				if (!guardOnAShip)
					deaths += updateAndReturnNonZeroShips(passengersLeft);

				int passengersICanTake = Math.min(passengersLeft[i], remCapacity);
				passengersLeft[i] -= passengersICanTake;
				remCapacity -= passengersICanTake;

				// pickup
				deaths += updateAndReturnNonZeroShips(passengersLeft);
			}

			if (passengersLeft[i] == 0)
				i++;

			if (remCapacity == 0) {
				// teleport to station
				if (!(grid[guard.x][guard.y] instanceof Station))
					deaths += updateAndReturnNonZeroShips(passengersLeft);
				
				// drop to station
				deaths += updateAndReturnNonZeroShips(passengersLeft);
				remCapacity = guard.getMaxCapacity();
			}
		}
		return deaths;
	}

	public static int updateAndReturnNonZeroShips(int[] pass) {
		int c = 0;
		for (int i = 0; i < pass.length; i++) {
			if (pass[i] > 0) {
				pass[i]--;
				c++;
			}
		}
		return c;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		guard.appendStringBuilder(sb);
		sb.append(";");

		int rem = 0;
		int bb = 0;

		int bbExp = 0;
		int bbRet = 0;
		for (Ship ship : ships) {
//			ship.appendStringBuilder(sb);
			rem += ship.getCurrentPassengerCount();
			bb += ship.getBlackBoxCounter();

			if (ship.isBlackBoxExpired())
				bbExp++;
			if (ship.isBlackBoxRetrived())
				bbRet++;
		}

		sb.append(rem);
		sb.append(";");
		sb.append(bb);
		sb.append(";");
		sb.append(bbExp);
		sb.append(";");
		sb.append(bbRet);

		sb.append(";");
		sb.append(depth);

//		StringBuilder sb = new StringBuilder(guard.toString() + ";");
//		
//		for (Ship ship : ships) {
//			sb.append(ship.toString());
//		}
//		sb.append(";");
////		for (Station station : stations) {
////			sb.append(station.toString());
////		}
//		
//		sb.append(depth);
//		sb.append(";");
////		s += ";" + operator + ";";
////		s += ";" + operator + ";" + depth + ";";
		return sb.toString();
	}

}
