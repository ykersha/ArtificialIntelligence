package code;

import java.util.ArrayList;

public class SearchTreeNode {
	
	Cell[][] grid;
//	CoastGuard cg;
	ArrayList<Ship> ships;
	ArrayList<Station> stations;
	
	SearchTreeNode parent;
	String operator;
	int depth;
	int pathCost;


	public SearchTreeNode(Cell[][] grid, ArrayList<Ship> ships, ArrayList<Station> stations, SearchTreeNode parent,
			String operator, int depth, int pathCost) {
		super();
		this.grid = grid;
//		this.cg = cg;
		this.ships = ships;
		this.stations = stations;
		this.parent = parent;
		this.operator = operator;
		this.depth = depth;
		this.pathCost = pathCost;
	}

}
