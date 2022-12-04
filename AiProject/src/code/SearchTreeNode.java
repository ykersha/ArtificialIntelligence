package code;

import java.util.ArrayList;

public class SearchTreeNode {

	Cell[][] grid;
	Guard guard;
	ArrayList<Ship> ships;
	ArrayList<Station> stations;

	SearchTreeNode parent;
	String operator;
	int depth;
	int pathCost;

	public SearchTreeNode(Cell[][] grid, Guard guard, ArrayList<Ship> ships, ArrayList<Station> stations,
			SearchTreeNode parent, String operator, int depth, int pathCost) {
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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		guard.appendStringBuilder(sb);
		sb.append(";");
	
		int rem = 0;
		int bb = 0;
		for (Ship ship : ships) {
			//ship.appendStringBuilder(sb);
			rem += ship.getCurrentPassengerCount();
			bb += ship.getBlackBoxCounter();
		}
		
		sb.append(rem);
		sb.append(";");
		sb.append(bb);
		
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
