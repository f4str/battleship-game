package ships;

public abstract class Ship {
	
	protected String type;
	protected boolean placed;
	protected boolean vertical;
	protected boolean sunk;
	protected int size;
	
	public Ship() {
		vertical = true;
		placed = false;
		sunk = false;
	}
	
	public String getType() {
		return type;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isPlaced() {
		return placed;
	}
	
	public void place() {
		placed = true;
	}
	
	public void remove() {
		placed = false;
	}
	
	public boolean isVertical() {
		return vertical;
	}
	
	public void rotate() {
		vertical = !vertical;
	}
	
	public void setVertical() {
		vertical = true;
	}
	
	public void setHorizontal() {
		vertical = false;
	}
	
	public boolean isSunk() {
		return sunk;
	}
	
	public void sink() {
		sunk = true;
	}
	
	
}
