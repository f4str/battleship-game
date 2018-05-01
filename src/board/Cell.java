package board;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Cell extends Rectangle {
	
	private boolean placed;
	private boolean attacked;

	public Cell(int size) {
		super();
		placed = false;
		attacked = false;
		setHeight(size);
		setWidth(size);
		setStroke(Color.DARKBLUE);
		setFill(Color.DARKBLUE);
	}
	
	public boolean isPlaced() {
		return placed;
	}
	
	public void place() {
		placed = true;
		setStroke(Color.BLACK);
		setFill(Color.GREY);
	}
	
	public void placeHidden() {
		placed = true;
	}
	
	public void remove() {
		placed = false;
		setFill(Color.DARKBLUE);
	}
	
	public boolean isAttacked() {
		return attacked;
	}
	
	public void attack() {
		attacked = true;
		if (placed) {
			setFill(Color.DARKRED);
		}
		else {
			setFill(Color.BLUEVIOLET);
		}
	}
	
}
