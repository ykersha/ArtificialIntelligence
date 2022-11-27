package code;

import java.util.*;

public class SearchProblem {

	ArrayList<String> operators;
	SearchTreeNode initialState;

	public SearchProblem(ArrayList<String> operators, SearchTreeNode initialState) {
		super();
		this.operators = operators;
		this.initialState = initialState;
	}

	public boolean goalTest(CoastGuard cg, ArrayList<Ship> ships) {
		if (cg.getCurrentCapacity() > 0) {
			return false;
		}

		for (Ship ship : ships) {
			if (ship.getCurrentPassengerCount() > 0 || !ship.isBlackBoxExpired()) {
				return false;
			}
		}
		return true;
	}

}

// state ships[], stations[], coastguard
