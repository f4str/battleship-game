package objects;

public class Carrier extends Ship {
	
	private Part[] parts;
	
	public Carrier() {
		parts = new Part[5];
	}
	
	public boolean isDestroyed() {
		for (Part p : parts) {
			if (!p.isSunk())
				return false;
		}
		return true;
	}

}
