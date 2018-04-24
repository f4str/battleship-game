package board;

import javafx.scene.layout.GridPane;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class BoardPane extends GridPane {
	
	Rectangle[][] grid;
	
	public BoardPane() {
		setPadding(new Insets(5));
		setHgap(1);
		setVgap(1);
		addLabels();
		makeGrid();
		fillGrid();
	}
	
	public void addLabels() {
		Label label;
		
		char letter = 'A';
		for (int i = 1; i <= 10; i++) {
			label = new Label("" + letter);
			GridPane.setHalignment(label, HPos.CENTER);
			add(label, i, 0);
			letter++;
			
		}
		char num = '1';
		for (int i = 1; i <= 9; i++) {
			label = new Label("" + num);
			GridPane.setHalignment(label, HPos.CENTER);
			add(label , 0, i);
			num++;
		}
		add(new Label("10"), 0, 10);
	}
	
	public void makeGrid() {
		grid = new Rectangle[10][10];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Rectangle();
				grid[i][j].setHeight(30);
				grid[i][j].setWidth(30);
				grid[i][j].setStroke(Color.BLACK);
				grid[i][j].setFill(Color.DARKBLUE);
			}
		}
	}
	
	public void fillGrid() {
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				Rectangle space = grid[i - 1][j - 1];
				GridPane.setRowIndex(space, i);
		        GridPane.setColumnIndex(space, j);
		        getChildren().addAll(space);
			}
		}
	}
	
	public void attack(int row, int col) {
		
	}

}
