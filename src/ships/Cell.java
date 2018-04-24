package ships;

public class Cell {
	
	private boolean sunk;
	private String x;
	private String y;
	
	public Cell() {
		sunk = false;
	}
	
	public boolean isSunk() {
		return sunk;
	}
	
	public void sink() {
		sunk = true;
	}
	
	public String getX() {
		return x;
	}
	
	public void setX(String x) {
		this.x = x;
	}
	
	public String getY() {
		return y;
	}
	
	public void setY(String y) {
		this.y = y;
	}

}
