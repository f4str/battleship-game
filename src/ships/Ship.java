package ships;

import board.Tile;

public abstract class Ship {
	
	protected String type;
	protected int size;
	protected boolean vertical;
	protected boolean sunk;
	protected Tile[] tiles;
	
	public Ship() {
		vertical = true;
		sunk = false;
	}
	
	public String getType() {
		return type;
	}
	
	public int getSize() {
		return size;
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
	
	public boolean isDestroyed() {
		for (Tile t : tiles) {
			if (!t.isSunk())
				return false;
		}
		return true;
	}
	
	
}
