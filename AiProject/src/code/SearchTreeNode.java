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
		String s = guard.toString() + ";";

		for (Ship ship : ships) {
			s += ship.toString();
		}
		s += ";";
		for (Station station : stations) {
			s += station.toString();
		}
		
//		s += ";" + operator + ";";
//		s += ";" + operator + ";" + depth + ";";
		return s+";";
	}

}
