package board;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Piece extends Rectangle {
	
	private boolean selected;
	private boolean destroyed;
	private int size;
	
	public Piece(int height, int size) {
		super();
		this.size = size;
		selected = false;
		destroyed = false;
		setHeight(height);
		setWidth(height * size);
		setStroke(Color.BLACK);
		setFill(Color.GREY);
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void select() {
		selected = true;
		setFill(Color.GREEN);
	}
	
	public boolean isDestroyed() {
		return destroyed;
	}
	
	public void destroy() {
		destroyed = true;
		setFill(Color.RED);
	}

}
