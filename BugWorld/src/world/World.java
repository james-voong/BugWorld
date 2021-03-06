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

	public void drawBug() {
		for (int i = 0; i < bugs.size(); i++) {
			char sym = bugs.get(i).getSym();// get each bugs symbol
			int posy = bugs.get(i).getPosy();// get each bugs posY ie. height
			int posx = bugs.get(i).getPosx();// get each bugs posX ie. width
			if (posy <= 0) {
				posy = 1;
				bugs.get(i).setPosY(posy);
			}
			if (posy >= height - 2) {
				posy = height - 2;
				bugs.get(i).setPosY(posy);
			}
			if (posx <= 0) {
				posx = 1;
				bugs.get(i).setPosX(posx);
			}
			if (posx >= width - 2) {
				posx = width - 2;
				bugs.get(i).setPosX(posx);
			}
			grid[posy][posx] = sym; // assign the sym to posY and posX for each
									// bug
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
		checkOverlap();
	}

	public void generateBugs(int bugNum) {
		for (int i = 0; i < bugNum; i++) {
			int randomizer = rand.nextInt(120);
			if (randomizer <= 40) {
				Bug bug1 = new Hornet("Mosquito", height, width);
				bugs.add(bug1);

			} else if (randomizer >= 80) {
				Bug bug2 = new Mosquito("asd", height, width);
				bugs.add(bug2);

			} else if (randomizer > 40 && randomizer < 80) {
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
				bug.setPosX(IniX);
				bug.setPosY(IniY);
			}
		}
		int arraySize = plants.size();
		for (int x = 0; x < arraySize; x++) {
			if (plants.get(x).getPosX() == newX && plants.get(x).getPosY() == newY) {
				bug.setPosX(IniX);
				bug.setPosY(IniY);
				arraySize = eatPlant(plants.get(x), x, arraySize);
			}
		}
	}

	public int eatPlant(Plant p, int index, int arraySize) {
		int size = p.getSize();
		if (size > 0) {
			size--;
			p.setSize(size);
		}
		if (size <= 0) {
			plants.remove(index);
			arraySize--;
		}
		return arraySize;
	}

	public void checkOverlap() {
		// Cycle through bugs
		for (int i = 0; i < bugs.size(); i++) {
			int posX = bugs.get(i).getPosx();
			int posY = bugs.get(i).getPosy();
			// Compare bugs with obstacles
			for (int x = 0; x < obstacles.size(); x++) {
				if (obstacles.get(x).getPosX() == posX && obstacles.get(x).getPosY() == posY) {
					int chance = rand.nextInt(90);
					if (posX > 1) {
						bugs.get(i).setPosX(posX - 1);
						if (chance >= 45 && posY > 1) {
							bugs.get(i).setPosY(posY - 1);
						} else if (chance >= 45 && posY <= 1) {
							bugs.get(i).setPosY(posY + 1);
						}
					}
					if (posX <= 1) {
						bugs.get(i).setPosX(posX + 1);
						if (chance >= 45 && posY > 1) {
							bugs.get(i).setPosY(posY - 1);
						} else if (chance >= 45 && posY <= 1) {
							bugs.get(i).setPosY(posY + 1);
						}
					}
					if (posY > 1) {
						bugs.get(i).setPosX(posY - 1);
						if (chance >= 45 && posX > 1) {
							bugs.get(i).setPosY(posX - 1);
						} else if (chance >= 45 && posX <= 1) {
							bugs.get(i).setPosY(posX + 1);
						}
					}
					if (posY <= 1) {
						bugs.get(i).setPosX(posY + 1);
						if (chance >= 45 && posX > 1) {
							bugs.get(i).setPosY(posX - 1);
						} else if (chance >= 45 && posX <= 1) {
							bugs.get(i).setPosY(posX + 1);
						}
					}
				}
			}
			// compares bugs with plants
			for (int y = 0; y < plants.size(); y++) {
				if (plants.get(y).getPosX() == posX && plants.get(y).getPosY() == posY) {
					int chance = rand.nextInt(90);
					if (posX > 1) {
						bugs.get(i).setPosX(posX - 1);
						if (chance >= 45 && posY > 1) {
							bugs.get(i).setPosY(posY - 1);
						} else if (chance >= 45 && posY <= 1) {
							bugs.get(i).setPosY(posY + 1);
						}
					}
					if (posX <= 1) {
						bugs.get(i).setPosX(posX + 1);
						if (chance >= 45 && posY > 1) {
							bugs.get(i).setPosY(posY - 1);
						} else if (chance >= 45 && posY <= 1) {
							bugs.get(i).setPosY(posY + 1);
						}
					}
					if (posY > 1) {
						bugs.get(i).setPosX(posY - 1);
						if (chance >= 45 && posX > 1) {
							bugs.get(i).setPosY(posX - 1);
						} else if (chance >= 45 && posX <= 1) {
							bugs.get(i).setPosY(posX + 1);
						}
					}
					if (posY <= 1) {
						bugs.get(i).setPosX(posY + 1);
						if (chance >= 45 && posX > 1) {
							bugs.get(i).setPosY(posX - 1);
						} else if (chance >= 45 && posX <= 1) {
							bugs.get(i).setPosY(posX + 1);
						}
					}
				}
			}
		}
		// compares plants with obstacles
		for (int q = 0; q < plants.size(); q++) {
			int posX = plants.get(q).getPosX();
			int posY = plants.get(q).getPosY();
			for (int w = 0; w < obstacles.size(); w++) {
				if (plants.get(w).getPosX() == posX && plants.get(w).getPosY() == posY) {
					int chance = rand.nextInt(90);
					if (posX > 1) {
						plants.get(q).setPosX(posX - 1);
						if (chance >= 45 && posY > 1) {
							plants.get(q).setPosY(posY - 1);
						} else if (chance >= 45 && posY <= 1) {
							plants.get(q).setPosY(posY + 1);
						}
					}
					if (posX <= 1) {
						plants.get(q).setPosX(posX + 1);
						if (chance >= 45 && posY > 1) {
							plants.get(q).setPosY(posY - 1);
						} else if (chance >= 45 && posY <= 1) {
							plants.get(q).setPosY(posY + 1);
						}
					}
					if (posY > 1) {
						plants.get(q).setPosX(posY - 1);
						if (chance >= 45 && posX > 1) {
							plants.get(q).setPosY(posX - 1);
						} else if (chance >= 45 && posX <= 1) {
							plants.get(q).setPosY(posX + 1);
						}
					}
					if (posY <= 1) {
						plants.get(q).setPosX(posY + 1);
						if (chance >= 45 && posX > 1) {
							plants.get(q).setPosY(posX - 1);
						} else if (chance >= 45 && posX <= 1) {
							plants.get(q).setPosY(posX + 1);
						}
					}
				}
			}
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
