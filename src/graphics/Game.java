package graphics;

import board.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.geometry.*;


public class Game extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane board = new GridPane();
		board.setAlignment(Pos.CENTER);
		board.setPadding(new Insets(10));
		board.setHgap(8);
		board.setVgap(8);
		
		Grid playerGrid = new Grid();
		Grid opponentGrid = new Grid();
		
		Board playerBoard = new Board();
		Board opponentBoard = new Board();
		
		Status playerShips = new Status();
		Status opponentShips = new Status();
		
		board.add(new Label("Player"), 0, 0);
		board.add(new Label("Opponent"), 1, 0);
		
		GridPane.setConstraints(playerGrid, 0, 1);
		board.getChildren().add(playerGrid);
		GridPane.setConstraints(opponentGrid, 1, 1);
		board.getChildren().add(opponentGrid);
		
		board.add(new Label("Ships"), 0, 2);
		board.add(new Label("Ships"), 1, 2);
		
		GridPane.setConstraints(playerShips, 0, 3);
		board.getChildren().add(playerShips);
		GridPane.setConstraints(opponentShips, 1, 3);
		board.getChildren().add(opponentShips);
		
		Scene scene = new Scene(board, 500, 500);
		primaryStage.setTitle("Battleship");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
}
