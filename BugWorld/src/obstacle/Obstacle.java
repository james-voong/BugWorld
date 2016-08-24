package obstacle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle extends Rectangle {
	private int posY;
	private int posX;
	private char obstacle;

	/**
	 * @param posY
	 * @param posX
	 */
	public Obstacle(int height, int width) {
		super(20, 20);
		setFill(Color.CYAN);
		this.posY = (int) (Math.random() * height);
		this.posX = (int) (Math.random() * width);
		obstacle = '@';
		checkBorder(height, width);
	}

	public void checkBorder(int height, int width) {
		if (posY <= 1) {
			posY = 1;
		}
		if (posY >= height - 2) {
			posY = height - 2;
		}
		if (posX <= 0) {
			posX = 1;
		}
		if (posX >= width - 2) {
			posX = width - 2;
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
