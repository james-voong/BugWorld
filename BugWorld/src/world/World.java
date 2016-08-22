package world;

import java.util.ArrayList;
import java.util.Random;

import bug.Bug;
import bug.Fly;
import bug.Hornet;
import bug.Mosquito;
import obstacle.Obstacle;
import plant.Plant;

public class World {
	private int height;
	private int width;
	private ArrayList<Bug> bugs = new ArrayList<Bug>();
	private ArrayList<Plant> plants = new ArrayList<Plant>();
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private char[][] grid;
	private Random rand = new Random();

	public World(int height, int width) {
		this.height = height;
		this.width = width;
		grid = new char[height][width];
	}

	public void drawWorld() {
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
		drawPlant();
		drawObstacle();
		for (int i = 0; i < height; i++) {
			for (int x = 0; x < width; x++) {
				System.out.print(grid[i][x]);
			}
			System.out.print("\n");
		}

	}

	public void drawPlant() {
		for (int i = 0; i < plants.size(); i++) {
			int posx = plants.get(i).getPosX();
			int posy = plants.get(i).getPosY();
			plants.get(i).growPlant();
			int size = plants.get(i).getSize();
			grid[posy][posx] = String.valueOf(size).charAt(0);
		}
	}

	public void drawBug() {
		for (int i = 0; i < bugs.size(); i++) {
			char sym = bugs.get(i).getSym();// get each bugs symbol
			int posy = bugs.get(i).getPosy();// get each bugs posy ie. height
			int posx = bugs.get(i).getPosx();// get each bugs posx ie. width
			if (posy <= 0) {
				posy = 1;
				bugs.get(i).setPosy(posy);
			}
			if (posy >= height - 2) {
				posy = height - 2;
				bugs.get(i).setPosy(posy);
			}
			if (posx <= 0) {
				posx = 1;
				bugs.get(i).setPosx(posx);
			}
			if (posx >= width - 2) {
				posx = width - 2;
				bugs.get(i).setPosx(posx);
			}
			grid[posy][posx] = sym; // assign the sym to posy and posx for each
									// bug
		}
	}

	public void drawObstacle() {
		for (int i = 0; i < obstacles.size(); i++) {
			int posx = obstacles.get(i).getPosX();
			int posy = obstacles.get(i).getPosY();
			char sym = obstacles.get(i).getObstacle();
			grid[posy][posx] = sym;
		}

	}

	public void updateWorld() {
		for (int i = 0; i < bugs.size(); i++) {
			int IniX = bugs.get(i).getPosx();
			int IniY = bugs.get(i).getPosy();
			bugs.get(i).moveRandom();
			obstacleDetect(bugs.get(i), IniX, IniY);
			if (IniX != bugs.get(i).getPosx() || IniY != bugs.get(i).getPosy()) {
				grid[IniY][IniX] = ' ';
			}
		}
		drawBug();
		drawPlant();
		drawObstacle();
		for (int i = 0; i < height; i++) {
			for (int x = 0; x < width; x++) {
				System.out.print(grid[i][x]);
			}
			System.out.print("\n");
		}
	}

	public void fillWorld(int bugNum, int plantNum, int obsNum) {
		generateBugs(bugNum);
		generatePlants(plantNum);
		generateObstacles(obsNum);

	}

	public void generateBugs(int bugNum) {
		int randomizer = rand.nextInt(120);
		for (int i = 0; i < bugNum; i++) {
			if (randomizer <= 40) {
				Bug bug1 = new Hornet("Mosquito", height, width);
				bugs.add(bug1);
			}
			if (randomizer >= 80) {
				Bug bug2 = new Mosquito("asd", height, width);
				bugs.add(bug2);
			} else {
				Bug bug3 = new Fly("zxc", height, width);
				bugs.add(bug3);
			}
		}
	}

	public void generatePlants(int plantNum) {
		for (int i = 0; i < plantNum; i++) {
			Plant p1 = new Plant(height, width);
			plants.add(p1);
		}
	}

	public void generateObstacles(int obsNum) {
		for (int i = 0; i < obsNum; i++) {
			Obstacle O1 = new Obstacle(height, width);
			obstacles.add(O1);
		}
	}

	public void obstacleDetect(Bug bug, int IniX, int IniY) {
		int newX = bug.getPosx();
		int newY = bug.getPosy();
		for (int i = 0; i < obstacles.size(); i++) {
			if (obstacles.get(i).getPosX() == newX && obstacles.get(i).getPosY() == newY) {
				bug.setPosx(IniX);
				bug.setPosy(IniY);
			}
		}
		for (int x = 0; x < plants.size(); x++) {
			if (plants.get(x).getPosX() == newX && plants.get(x).getPosY() == newY) {
				bug.setPosx(IniX);
				bug.setPosy(IniY);
				eatPlant(plants.get(x), x);
			}
		}
	}

	public void eatPlant(Plant p, int index) {
		int size = p.getSize();
		if (size > 0) {
			size--;
			p.setSize(size);
		}
		if (size == 0) {
			plants.remove(index);
		}
	}

	public ArrayList<Bug> getBugs() {
		return bugs;
	}

	public ArrayList<Plant> getPlants() {
		return plants;
	}

	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}

}
