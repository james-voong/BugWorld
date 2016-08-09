package world;

import java.util.ArrayList;

import bugs.Bug;
import bugs.Fly;
import bugs.Hornet;
import bugs.Mosquito;
import obstacle.Obstacle;
import plant.Plant;

public class World {
	private static int height = 12;
	private static int width = 30;
	private static ArrayList<Bug> bugs = new ArrayList<Bug>();
	private static ArrayList<Plant> plants = new ArrayList<Plant>();
	private static ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private static char[][] grid = new char[height][width];

	public static void drawWorld() {
		for (int i = 0; i < height; i++) {
			for (int x = 0; x < width; x++) {
				grid[i][x] = ' ';
			}
		}
		for (int i = 0; i < height; i++) {
			grid[i][0] = '|';
			grid[i][width - 1] = '|';
		}
		for (int i = 0; i < width; i++) {
			grid[0][i] = '-';
			grid[height - 1][i] = '-';
		}
		grid[0][0] = '*';
		grid[height - 1][0] = '*';
		grid[0][width - 1] = '*';
		grid[height - 1][width - 1] = '*';

		drawBug();
		// drawPlant();
		drawObstacle();
		for (int i = 0; i < height; i++) {
			for (int x = 0; x < width; x++) {
				System.out.print(grid[i][x]);
			}
			System.out.print("\n");
		}

	}

	public static void drawPlant() {
		for (int i = 0; i < plants.size(); i++) {
			int posx = plants.get(i).getPosX();
			int posy = plants.get(i).getPosY();
			plants.get(i).growPlant();
			int size = plants.get(i).getSize();
			grid[posy][posx] = String.valueOf(size).charAt(0);
		}
	}

	public static void drawBug() {
		for (int i = 0; i < bugs.size(); i++) {
			char sym = bugs.get(i).getSym();// get each bugs symbol
			int posy = bugs.get(i).getPosy();// get each bugs posy ie. height
			int posx = bugs.get(i).getPosx();// get each bugs posx ie. width
			if (posy == 0) {
				posy++;
				bugs.get(i).setPosy(posy);
			} else if (posy == height - 1) {
				posy--;
				bugs.get(i).setPosy(posy);
			} else if (posx == 0) {
				posx++;
				bugs.get(i).setPosx(posx);
			} else if (posx == width - 1) {
				posx--;
				bugs.get(i).setPosx(posx);
			}
			grid[posy][posx] = sym; // assign the sym to posy and posx for each
									// bug
		}
	}

	public static void drawObstacle() {
		for (int i = 0; i < obstacles.size(); i++) {
			int posx = obstacles.get(i).getPosX();
			int posy = obstacles.get(i).getPosY();
			char sym = obstacles.get(i).getObstacle();
			grid[posy][posx] = sym;
		}

	}

	public static void updateWorld() {
		for (int i = 0; i < bugs.size(); i++) {
			int IniX = bugs.get(i).getPosx();
			int IniY = bugs.get(i).getPosy();
			bugs.get(i).moveRandom();
			if (IniX != bugs.get(i).getPosx() || IniY != bugs.get(i).getPosy()) {
				grid[IniY][IniX] = ' ';
			}
		}
		drawBug();
		// drawPlant();
		drawObstacle();
		for (int i = 0; i < height; i++) {
			for (int x = 0; x < width; x++) {
				System.out.print(grid[i][x]);
			}
			System.out.print("\n");
		}
	}

	public static void main(String[] args) {
		Bug bug1 = new Hornet("Mosquito");
		Bug bug2 = new Mosquito("asd");
		Bug bug3 = new Fly("zxc");
		bugs.add(bug1);
		bugs.add(bug2);
		bugs.add(bug3);
		Plant p1 = new Plant(height, width);
		Plant p2 = new Plant(height, width);
		Plant p3 = new Plant(height, width);
		plants.add(p1);
		plants.add(p2);
		plants.add(p3);
		Obstacle O1 = new Obstacle(height, width);
		Obstacle O2 = new Obstacle(height, width);
		obstacles.add(O1);
		obstacles.add(O2);
		drawWorld();
		updateWorld();

	}

}
