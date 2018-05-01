package board;

import javafx.scene.layout.GridPane;
import javafx.geometry.*;
import javafx.scene.control.*;
import ships.*;


public class StatusPane extends GridPane {
	
	Ship[] ships;
	Piece[] pieces;
	boolean selected;
	
	public StatusPane() {
		setPadding(new Insets(5));
		setHgap(3);
		setVgap(3);
		ships = new Ship[5];
		ships[0] = new Carrier();
		ships[1] = new Battleship();
		ships[2] = new Cruiser();
		ships[3] = new Submarine();
		ships[4] = new Destroyer();
		selected = false;
		addLabels();
		drawShips();
		addShips();
	}
	
	public void addLabels() {
		for (int i = 0; i < ships.length; i++) {
			Label label = new Label(ships[i].getType());
			add(label, 0, i);
		}
	}
	
	public void drawShips() {
		pieces = new Piece[5];
		for (int i = 0; i < ships.length; i++) {
			pieces[i] = new Piece(20, ships[i].getSize());
		}
	}
	
	public void addShips() {
		for (int i = 0; i < ships.length; i++) {
			Piece space = pieces[i];
			GridPane.setRowIndex(space, i);
	        GridPane.setColumnIndex(space, 1);
	        GridPane.setHalignment(space, HPos.RIGHT);
	        getChildren().addAll(space);
		}
	}
	
	public void updateShips() {
		for (int i = 0; i < ships.length; i++) {
			if (ships[i].isSunk()) {
				pieces[i].destroy();
			}
		}
	}

}
