package board;

import ships.*;

public class Board {
	
	private Tile[][] board;
	private Ship[] ships;
	
	public Board() {
		board = new Tile[10][10];
		ships = new Ship[5];
		ships[0] = new Carrier();
		ships[1] = new Battleship();
		ships[2] = new Cruiser();
		ships[3] = new Submarine();
		ships[4] = new Destroyer();
	}

}
