package obstacle;

public class Obstacle {
	private int posY;
	private int posX;
	private char obstacle;

	/**
	 * @param posY
	 * @param posX
	 */
	public Obstacle(int height, int width) {
		this.posY = (int) (Math.random() * height);
		this.posX = (int) (Math.random() * width);
		obstacle = '@';
		checkBorder(height, width);
	}

	public void checkBorder(int height, int width) {
		if (posY < 1) {
			posY = 1;
		} else if (posY > height - 1) {
			posY = height - 1;
		} else if (posX == 0) {
			posX++;
		} else if (posX == width - 1) {
			posX--;
		}

	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public char getObstacle() {
		return obstacle;
	}

}