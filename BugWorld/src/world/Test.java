package world;

public class Test {

	public static void main(String[] args) {
		int height = 12;
		int width = 30;
		World w = new World(height, width);
		w.fillWorld(6, 5, 3);
		w.drawWorld();
		w.updateWorld();
	}
}
