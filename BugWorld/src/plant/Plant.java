package plant;

public class Plant {
	private int size;
	private int posX;
	private int posY;

	/**
	 * @param size
	 */
	public Plant(int height, int width) {
		size = (int) (Math.random() * 9);
		posX = (int) (Math.random() * width);
		posY = (int) (Math.random() * height);
		checkPos(height, width);
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
		}
	}

	public void checkPos(int height, int width) {
		if (posY < 0) {
			posY = 1;
		}
		if (posY > height - 1) {
			posY = height - 1;
		}
		if (posX < 0) {
			posX = 1;
		}
		if (posX > width - 1) {
			posX = width - 1;
		}
	}

}
