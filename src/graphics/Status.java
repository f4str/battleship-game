package graphics;

import javafx.scene.layout.GridPane;
import javafx.scene.control.*;

public class Status extends GridPane {
	
	public Status() {
		setHgap(3);
		setVgap(3);
		addLabels();
	}
	
	public void addLabels() {
		add(new Label("Carrier"), 0, 0);
		add(new Label("Battleship"), 0, 1);
		add(new Label("Cruiser"), 0, 2);
		add(new Label("Submarine"), 0, 3);
		add(new Label("Destroyer"), 0, 4);
	}

}
