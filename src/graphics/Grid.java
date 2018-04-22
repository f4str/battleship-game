package graphics;

import javafx.scene.layout.GridPane;
import javafx.scene.control.*;

public class Grid extends GridPane {
	
	public Grid() {
		setHgap(5);
		setVgap(5);
		addLabels();
		makeGrid();
	}
	
	public void addLabels() {
		char letter = 'A';
		for (int i = 1; i <= 10; i++) {
			add(new Label("" + letter), i, 0);
			letter++;
		}
		char num = '1';
		for (int i = 1; i <= 9; i++) {
			add(new Label("" + num), 0, i);
			num++;
		}
		add(new Label("10"), 0, 10);
	}
	
	public void makeGrid() {
		
	}
	
	public void attack(int row, int col) {
		
	}

}
