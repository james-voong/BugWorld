package plant;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Plant extends Circle {
	private int size;
	private int posX;
	private int posY;

	/**
	 * @param size
	 */
	public Plant(int worldHeight, int worldWidth) {
		super(0, Color.GREEN);
		size = (int) (Math.random() * 9);
		posX = (int) (Math.random() * worldWidth);
		posY = (int) (Math.random() * worldHeight);
		checkPos(worldHeight, worldWidth);
		setRadius(2 * size + 1);
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void growPlant() {
		double roll = Math.random() * 100;
		if (roll <= 35 && size < 9) {
			size++;
			setRadius(2 * size + 1);
		}
	}

	public void checkPos(int height, int width) {
		if (posY <= 0) {
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

}
