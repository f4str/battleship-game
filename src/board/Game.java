package board;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import players.AI;


public class Game extends Application {
	
	GridPane board;
	Scene scene;
	BoardPane playerBoard;
	BoardPane opponentBoard;
	StatusPane playerStatus;
	StatusPane opponentStatus;
	Label playerLabel;
	Label opponentLabel;
	Label playerShipsLabel;
	Label opponentShipsLabel;
	Button start;
	AI computer;
	boolean planning = true;
	boolean playerTurn = true;
	boolean win = false;
	boolean lose = false;
	
	int currentShipSize;
	int currentShipRow;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		createBoard();
		createHeadings();
		createGrid();
		createShipsLabels();
		createStatus();
		createButton();
		assignShipSelection();
		assignShipPlacement();
		assignAttack();
		System.out.println();
		System.out.println("Click a ship to place");
		
		scene = new Scene(board, 800, 600);
		primaryStage.setTitle("Battleship");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void createBoard() {
		board = new GridPane();
		board.setAlignment(Pos.CENTER);
		board.setPadding(new Insets(10));
		board.setHgap(10);
		board.setVgap(10);
		System.out.println("Main board created");
	}
	
	public void createHeadings() {
		playerLabel = new Label("Player");
		opponentLabel = new Label("Opponent");
		
		GridPane.setHalignment(playerLabel, HPos.CENTER);
		GridPane.setHalignment(opponentLabel, HPos.CENTER);
		
		board.add(playerLabel, 0, 0);
		board.add(opponentLabel, 1, 0);
		System.out.println("Player labels created");
	}
	
	public void createShipsLabels() {
		playerShipsLabel = new Label("Ships");
		opponentShipsLabel = new Label("Ships");
		
		GridPane.setHalignment(playerShipsLabel, HPos.CENTER);
		GridPane.setHalignment(opponentShipsLabel, HPos.CENTER);
		
		board.add(playerShipsLabel, 0, 2);
		board.add(opponentShipsLabel, 1, 2);
		System.out.println("Ship labels created");
	}
	
	public void createGrid() {
		playerBoard = new BoardPane();
		opponentBoard = new BoardPane();
		
		GridPane.setConstraints(playerBoard, 0, 1);
		GridPane.setConstraints(opponentBoard, 1, 1);
		
		board.getChildren().add(playerBoard);
		board.getChildren().add(opponentBoard);
		System.out.println("Player boards created");
	}
	
	public void createStatus() {
		playerStatus = new StatusPane();
		opponentStatus = new StatusPane();
		
		GridPane.setConstraints(playerStatus, 0, 3);
		GridPane.setConstraints(opponentStatus, 1, 3);
		
		board.getChildren().add(playerStatus);
		board.getChildren().add(opponentStatus);
		System.out.println("Player statuses created");
	}
	
	public void createButton() {
		start = new Button("Start Game");
		start.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				for (int i = 0; i < playerStatus.ships.length; i++) {
					if (!playerStatus.ships[i].isPlaced()) {
						System.out.println("Not all ships placed\n");
						return;
					}
				}
				planning = false;
				start.setVisible(false);
				System.out.println("Game started\n");
				startGame();
			}
		});
		board.add(start, 0, 4);
		System.out.println("Start button created");
	}
	
	public void assignShipSelection() {
		for (int i = 0; i < playerStatus.pieces.length; i++) {
			int row = i;
			playerStatus.pieces[row].setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					if (!playerStatus.selected && !playerStatus.pieces[row].isSelected()) {
						playerStatus.pieces[row].select();
						currentShipSize = playerStatus.pieces[row].getSize();
						playerStatus.selected = true;
						currentShipRow = row;
						System.out.println("Ship of size: " + currentShipSize + " selected");
						System.out.println("Left click on the board to place horizontally");
						System.out.println("Right click on the board to place vertically");
					}
				}
			});
		}
	}
	
	public void assignShipPlacement() {
		for (int i = 0; i < playerBoard.grid.length; i++) {
			for (int j = 0; j < playerBoard.grid[i].length; j++) {
				Cell cell = playerBoard.grid[i][j];
				int row = i;
				int col = j;
				cell.setOnMouseClicked(new EventHandler<MouseEvent>() { 
					@Override
					public void handle(MouseEvent e) {
						if (planning) {
							if (e.getButton() == MouseButton.PRIMARY) {
								if (playerStatus.selected && validHorizontalPlacement(row, col)) {
									for (int i = col; i < col + currentShipSize; i++) {
										playerBoard.grid[row][i].place();
										playerBoard.cells++;
									}
									playerStatus.ships[currentShipRow].place();
									playerStatus.selected = false;
								}
							}
							else if (e.getButton() == MouseButton.SECONDARY) {
								if (playerStatus.selected && validVerticalPlacement(row, col)) {
									for (int i = row; i < row + currentShipSize; i++) {
										playerBoard.grid[i][col].place();
										playerBoard.cells++;
									}
									playerStatus.ships[currentShipRow].place();
									playerStatus.selected = false;
								}
							}
						}
					}
				});
			}
		}
	}
	
	public void assignAttack() {
		for (int i = 0; i < opponentBoard.grid.length; i++) {
			for (int j = 0; j < opponentBoard.grid[i].length; j++) {
				Cell cell = opponentBoard.grid[i][j];
				int row = i;
				int col = j;
				cell.setOnMouseClicked(new EventHandler<MouseEvent>() { 
					@Override
					public void handle(MouseEvent e) {
						if (!planning && playerTurn) {
							if (opponentBoard.grid[row][col].isAttacked()) {
								System.out.println("Space already attacked\n");
							}
							else if (opponentBoard.grid[row][col].isPlaced()) {
								opponentBoard.grid[row][col].attack();
								opponentBoard.cells--;
								System.out.println("Hit!");
								System.out.println("Keep attacking\n");
								checkWinner();
							}
							else {
								opponentBoard.grid[row][col].attack();
								System.out.println("Miss!");
								System.out.println("Opponent's turn\n");
								playerTurn = false;
								opponentAttack();
							}
						}
					}
				});
			}
		}
	}
	
	public boolean validHorizontalPlacement(int row, int col) {
		if (playerBoard.grid[row].length - col < currentShipSize) {
			System.out.println("Not a valid placement\n");
			return false;
		}
		for (int i = col; i < col + currentShipSize; i++) {
			if (playerBoard.grid[row][i].isPlaced()) {
				System.out.println("Not a valid placement\n");
				return false;
			}
		}
		System.out.println("Valid placement\n");
		return true;
	}
	
	public boolean validVerticalPlacement(int row, int col) {
		if (playerBoard.grid.length - row < currentShipSize) {
			System.out.println("Not a valid placement\n");
			return false;
		}
		for (int i = row; i < row + currentShipSize; i++) {
			if (playerBoard.grid[i][col].isPlaced()) {
				System.out.println("Not a valid placement\n");
				return false;
			}
		}
		System.out.println("Valid placement\n");
		return true;
	}
	
	public void startGame() {
		createComputer();
		fillEnemyBoard();
		System.out.println();
		System.out.println("Select enemy ship to attack\n");
	}
	
	public void createComputer() {
		computer = new AI();
		computer.generateSelfBoard();
		System.out.println("Enemy board generated");
	}
	
	public void fillEnemyBoard() {
		for (int i = 0; i < opponentStatus.pieces.length; i++) {
			opponentStatus.pieces[i].select();
		}
		for (int i = 0; i < opponentBoard.grid.length; i++) {
			for (int j = 0; j < opponentBoard.grid[i].length; j++) {
				if (computer.getSelfGrid()[i][j]) {
					opponentBoard.grid[i][j].place();
					opponentBoard.cells++;
				}
			}
		}
		System.out.println("Enemy ships placed");
	}
	
	public void opponentAttack() {
		int[] coordinates;
		while (!planning && !playerTurn) {
			coordinates = computer.attack();
			if (playerBoard.grid[coordinates[0]][coordinates[1]].isPlaced()) {
				playerBoard.grid[coordinates[0]][coordinates[1]].attack();
				playerBoard.cells--;
				System.out.println("Hit!");
				System.out.println("Opponent keeps attacking\n");
			}
			else {
				playerBoard.grid[coordinates[0]][coordinates[1]].attack();
				System.out.println("Miss!");
				System.out.println("Player's turn\n");
				playerTurn = true;
			}
		}
	}
	
	public void checkWinner() {
		if (opponentBoard.cells <= 0) {
			planning = true;
			win = true;
			endScreen();
		}
		else if (playerBoard.cells <= 0) {
			planning = true;
			lose = true;
			endScreen();
		}
	}
	
	public void endScreen() {
		if (win) {
			System.out.println("Player has won!");
			for (int i = 0; i < opponentStatus.pieces.length; i++) {
				opponentStatus.pieces[i].destroy();
			}
		}
		else if (lose) {
			System.out.println("Opponent has won!");
			for (int i = 0; i < playerStatus.pieces.length; i++) {
				playerStatus.pieces[i].select();
			}
		}
	}
	
}
