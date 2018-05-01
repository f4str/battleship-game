package players;

public class AI {
	
	private boolean[][] self;
	private boolean[][] opponent;
	private boolean carrier;
	private boolean battleship;
	private boolean cruiser;
	private boolean submarine;
	private boolean destroyer;
	private boolean hit;
	private boolean complete;
	
	public AI() {
		self = new boolean[10][10];
		opponent = new boolean[10][10];
		carrier = false;
		battleship = false;
		cruiser = false;
		submarine = false;
		destroyer = false;
	}
	
	public void generateSelfBoard() {
		placeShip(5);
		placeShip(4);
		placeShip(3);
		placeShip(3);
		placeShip(2);
	}
	
	public boolean[][] getSelfGrid() {
		return self;
	}
	
	public boolean[][] getOpponentGrid() {
		return opponent;
	}
	
	public int generatePosition() {
		return (int)(Math.random() * 10);
	}
	
	public boolean generateOrientation() {
		return (int)(Math.random() * 2) > 0;
	}
	
	public void placeShip(int size) {
		int row = generatePosition();
		int col = generatePosition();
		boolean orientation = generateOrientation();
		while (!isValid(row, col, size, orientation)) {
			row = generatePosition();
			col = generatePosition();
			orientation = generateOrientation();
		}
		if (orientation) {
			for (int i = row; i < row + size; i++) {
				self[i][col] = true;
			}
		}
		else {
			for (int i = col; i < col + size; i++) {
				self[row][i] = true;
			}
		}
	}
	
	public boolean isValid(int row, int col, int size, boolean orientation) {
		if (row + size > 10 || col + size > 10)
			return false;
		
		if (orientation) {
			for (int i = row; i < row + size; i++) {
				if (self[i][col]) {
					return false;
				}
			}
		}
		else {
			for (int i = col; i < col + size; i++) {
				if (self[row][i]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int[] attack() {
		int row = (int)(Math.random() * 10);
		int col = (int)(Math.random() * 10);
		while (opponent[row][col]) {
			row = (int)(Math.random() * 10);
			col = (int)(Math.random() * 10);
		}
		opponent[row][col] = true;
		return new int[]{row, col};
	}

	public boolean isCarrier() {
		return carrier;
	}

	public void sinkCarrier() {
		carrier = true;
	}

	public boolean isBattleship() {
		return battleship;
	}

	public void sinkBattleship() {
		battleship = true;
	}

	public boolean isCruiser() {
		return cruiser;
	}

	public void sinkCruiser() {
		cruiser = true;
	}

	public boolean isSubmarine() {
		return submarine;
	}

	public void sinkSubmarine() {
		submarine = true;
	}

	public boolean isDestroyer() {
		return destroyer;
	}

	public void sinkDestroyer() {
		destroyer = true;
	}
	
	public boolean isHit() {
		return hit;
	}
	
	public void hit() {
		hit = true;
	}
	
	public void miss() {
		hit = false;
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	public void complete() {
		complete = true;
	}
	
	public void incomplete() {
		complete = false;
	}

}
