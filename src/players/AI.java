package players;

public class AI {
	
	private boolean[][] self;
	private boolean[][] opponent;
	private int row;
	private int col;
	private int originalRow;
	private int originalCol;
	
	private boolean found;
	private boolean hit;
	private boolean horizontal;
	private int direction;
	private int count;
	
	public AI() {
		self = new boolean[10][10];
		opponent = new boolean[10][10];
		found = false;
		hit = false;
		horizontal = true;
		direction = 1;
		count = 0;
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
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void hit() {
		hit = true;
	}
	
	public void miss() {
		hit = false;
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
	
	public void status() {
		if (hit && !found) {
			found = true;
			originalRow = row;
			originalCol = col;
			count = 1;
			direction = 1;
			horizontal = true;
		}
		else if (hit && found) {
			count++;
		}
	}
	
	public void attack() {
		status(); 
		
		if (found) {
			if (hit) {
				if (count == 1) {
					primaryAttack();
					return;
				}
				else if (count > 1) {
					continuedAttack();
					return;
				}
			}
			else if (!hit) {
				if (count == 1) {
					surroundAttack();
					return;
				}
				else if (count > 1) {
					reverseAttack();
					return;
				}
			}
		}
		else {
			randomAttack();
			return;
		}
	}
	
	public void randomAttack() {
		row = (int)(Math.random() * 10);
		col = (int)(Math.random() * 10);
		while (opponent[row][col]) {
			row = (int)(Math.random() * 10);
			col = (int)(Math.random() * 10);
		}
		opponent[row][col] = true;
	}
	
	public void primaryAttack() {
		if (col + 1 < opponent[row].length && !opponent[row][col + 1]) {
			col++;
			opponent[row][col] = true;
			direction = 1;
			horizontal = true;
			return;
		}
		else if (col - 1 > 0 && !opponent[row][col - 1]) {
			col--;
			opponent[row][col] = true;
			direction = -1;
			horizontal = true;
			return;
		}
		else if (row + 1 < opponent.length && !opponent[row + 1][col]) {
			row++;
			opponent[row][col] = true;
			direction = 1;
			horizontal = false;
			return;
		}
		else if (row - 1 >= 0 && !opponent[row - 1][col]) {
			row--;
			opponent[row][col] = true;
			direction = -1;
			horizontal = false;
			return;
		}
		else {
			found = false;
			randomAttack();
			return;
		}
	}
	
	public void continuedAttack() {
		if (horizontal) {
			if (col + direction < opponent[row].length && col + direction > 0 && !opponent[row][col + direction]) {
				col += direction;
				opponent[row][col] = true;
				return;
			}
			else if (originalCol - direction < opponent[row].length && originalCol - direction > 0 && !opponent[row][originalCol - direction]) {
				direction = -1 * direction;
				col = originalCol + direction;
				opponent[row][col] = true;
				return;
			}
			else {
				found = false;
				randomAttack();
				return;
			}
		}
		else {
			if (row + direction < opponent.length && row + direction > 0 && !opponent[row + direction][col]) {
				row += direction;
				opponent[row][col] = true;
				return;
			}
			else if (originalRow - direction < opponent.length && originalRow - direction > 0 && !opponent[originalRow - direction][col]) {
				direction = -1 * direction;
				row = originalRow + direction;
				opponent[row][col] = true;
				return;
			}
			else {
				found = false;
				randomAttack();
				return;
			}
		}
	}
	
	public void surroundAttack() {
		if (originalCol + direction < opponent[row].length && originalCol + direction > 0 && !opponent[row][originalCol + direction]) {
			col = originalCol + direction;
			opponent[row][col] = true;
			horizontal = true;
			return;
		}
		else if (originalCol - direction < opponent[row].length && originalCol - direction > 0 && !opponent[row][originalCol - direction]) {
			direction = -1 * direction;
			col = originalCol + direction;
			opponent[row][col] = true;
			horizontal = true;
			return;
		}
		else if (originalRow + direction < opponent.length && originalRow + direction > 0 && !opponent[originalRow + direction][col]) {
			row = originalRow + direction;
			opponent[row][col] = true;
			horizontal = false;
			return;
		}
		else if (originalRow - direction < opponent.length && originalRow - direction > 0 && !opponent[originalRow - direction][col]) {
			direction = -1 * direction;
			row = originalRow + direction;
			opponent[row][col] = true;
			horizontal = false;
			return;
		}
		else {
			found = false;
			randomAttack();
			return;
		}
	}
	
	public void reverseAttack() {
		if (horizontal) {
			if (originalCol - direction < opponent[row].length && originalCol - direction > 0 && !opponent[row][originalCol - direction]) {
				direction = -1 * direction;
				col = originalCol + direction;
				opponent[row][col] = true;
				return;
			}
			else {
				found = false;
				randomAttack();
				return;
			}
		}
		else {
			if (originalRow - direction < opponent.length && originalRow - direction > 0 && !opponent[originalRow - direction][col]) {
				direction = -1 * direction;
				row = originalRow + direction;
				opponent[row][col] = true;
				return;
			}
			else {
				found = false;
				randomAttack();
				return;
			}
		}
	}	

}
