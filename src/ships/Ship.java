package ships;

public abstract class Ship {
	
	protected String type;
	protected boolean vertical;
	protected boolean sunk;
	protected Cell[] cells;
	
	public Ship() {
		vertical = true;
		sunk = false;
	}
	
	public String getType() {
		return type;
	}
	
	public int getSize() {
		return cells.length;
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
