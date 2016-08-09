package bugs;

public class Bug {
	protected String name;
	private String species;
	private char sym;
	private static int uidt = 0;
	protected int uid;
	protected int energy = 1;
	protected int posx;
	protected int posy;

	/**
	 * @param name
	 * @param species
	 * @param sym
	 */
	public Bug(String name, String species, char sym) {
		this.name = name;
		this.species = species;
		this.sym = sym;
		uid = uidt++;
		posx = (int) (Math.random() * 30);
		posy = (int) (Math.random() * 12);
	}

	/**
	 * @param energy
	 * @param posx
	 * @param posy
	 */
	public Bug() {
		name = "Default Name";
		species = "Default Species";
		sym = 'a';
		uid = uidt++;
		posx = (int) (Math.random() * 30);
		posy = (int) (Math.random() * 12);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public char getSym() {
		return sym;
	}

	public void setSym(char sym) {
		this.sym = sym;
	}

	public int getUid() {
		return uid;
	}

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public int getPosy() {
		return posy;
	}

	public String toString() {
		return "Name = " + name + "\nSpecies = " + species + "\nSymbol = " + sym;
	}

	public String toText() {
		return "Name = " + name + "\nSpecies = " + species + "\nSymbol = " + sym + "\nUniqueID = " + uid + "\nEnergy = "
				+ energy;
	}

	public void move(char a) {
		char b = Character.toUpperCase(a);
		System.out.println("X = " + posx + "\nY = " + posy);
		if (b == 'N' && energy > 0) {
			while (energy > 0) {
				posy = posy - 1;
				energy--;
			}
		} else if (b == 'S' && energy > 0) {
			while (energy > 0) {
				posy = posy + 1;
				energy--;
			}
		} else if (b == 'W' && energy > 0) {
			while (energy > 0) {
				posx = posx - 1;
				energy--;
			}
		} else if (b == 'E' && energy > 0) {
			while (energy > 0) {
				posx = posx + 1;
				energy--;
			}
		}
		System.out.println("X = " + posx + "\nY = " + posy);
	}

	public void moveRandom() {
		// int tracker = 0;
		// System.out.println("Initial X = " + posx + "\nInitial Y = " + posy);
		while (energy > 0) {
			double f = Math.random();
			if (f <= 0.2) {
				posy--;
				// System.out.println(name + " moves N");
			} else if (f > 0.2 && f <= 0.4) {
				posy++;
				// System.out.println(name + " moves S");
			} else if (f > 0.4 && f <= 0.6) {
				posx--;
				// System.out.println(name + " moves W");
			} else if (f > 0.6 && f <= 0.8) {
				posx++;
				// System.out.println(name + " moves E");
			}

			energy--;
			// tracker++;
		}
		// System.out.print(tracker);
		// System.out.println("Final X = " + posx + "\nFinal Y = " + posy);
	}
}
