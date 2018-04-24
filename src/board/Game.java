package board;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.geometry.*;


public class Game extends Application {
	
	BoardPane playerGrid;
	BoardPane opponentGrid;
	StatusPane playerStatus;
	StatusPane opponentStatus;
	Label playerLabel;
	Label opponentLabel;
	Label playerShipsLabel;
	Label opponentShipsLabel;
	Button start;
	boolean planning = true;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane board = new GridPane();
		board.setAlignment(Pos.CENTER);
		board.setPadding(new Insets(10));
		board.setHgap(10);
		board.setVgap(10);
		
		playerGrid = new BoardPane();
		opponentGrid = new BoardPane();
		playerStatus = new StatusPane();
		opponentStatus = new StatusPane();
		
		createHeadings();
		board.add(playerLabel, 0, 0);
		board.add(opponentLabel, 1, 0);
		
		GridPane.setConstraints(playerGrid, 0, 1);
		board.getChildren().add(playerGrid);
		GridPane.setConstraints(opponentGrid, 1, 1);
		board.getChildren().add(opponentGrid);
		
		createShipsLabels();
		board.add(playerShipsLabel, 0, 2);
		board.add(opponentShipsLabel, 1, 2);
		
		GridPane.setConstraints(playerStatus, 0, 3);
		board.getChildren().add(playerStatus);
		GridPane.setConstraints(opponentStatus, 1, 3);
		board.getChildren().add(opponentStatus);
		
		start = new Button("Start Game");
		board.add(start, 0, 4);
		
		Scene scene = new Scene(board, 800, 600);
		primaryStage.setTitle("Battleship");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void createHeadings() {
		playerLabel = new Label("Player");
		GridPane.setHalignment(playerLabel, HPos.CENTER);
		opponentLabel = new Label("Opponent");
		GridPane.setHalignment(opponentLabel, HPos.CENTER);
	}
	
	public void createShipsLabels() {
		playerShipsLabel = new Label("Ships");
		GridPane.setHalignment(playerShipsLabel, HPos.CENTER);
		opponentShipsLabel = new Label("Ships");
		GridPane.setHalignment(opponentShipsLabel, HPos.CENTER);
	}
	
}
