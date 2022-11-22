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
		int r = rand.nextInt(points.size() / 2);
		while (r-- > 0) {
			stations.add(points.remove(0));
		}
		r = rand.nextInt(points.size());
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

	public static void main(String[] args) {
		
		int i = 10;
		while(i-- > 0)
			System.out.println(genGrid());

	}

}
