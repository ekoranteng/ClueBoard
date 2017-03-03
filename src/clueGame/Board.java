package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Board {
	private int numRows;
	private int numColumns;
	private static Board theInstance = new Board();
	public static final int MAX_BOARD_SIZE = 100;
	private BoardCell [][] board;
	private Map<Character,String> legend;
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private String boardConfigFile;
	private String roomConfigFile;
	
	public static Board getInstance(){
		return theInstance;
	}
	
	public void initialize(){
		loadRoomConfig();
		try {
			loadBoardConfig();
		} catch (BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calcAdjacencies();
	}
	public void setConfigFiles(String boardConfigFile, String roomConfigFile) {
		this.boardConfigFile = "src/data/" + boardConfigFile;
		this.roomConfigFile = "src/data/" + roomConfigFile;
	}
	
	public void loadRoomConfig() {
		legend = new HashMap<Character, String>();
		FileReader reader = null;
		try {
			reader = new FileReader(roomConfigFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner in = new Scanner(reader);
		while (in.hasNextLine()) {
			String legendLine = in.nextLine();
			Character letter = new Character(legendLine.charAt(0));
			String roomName = legendLine.substring(3, legendLine.lastIndexOf(','));
			legend.put(letter, roomName);
		}
		in.close();
	}
	
	public void loadBoardConfig() throws BadConfigFormatException{
		FileReader getDimensions = null;
		try {
			getDimensions = new FileReader(boardConfigFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner in = new Scanner(getDimensions);
		String[] row1 = in.nextLine().split(",");
		numColumns = row1.length;
		numRows = 1;
		while(in.hasNextLine()) {
			in.nextLine();
			numRows++;
		}
		in.close();
		board = new BoardCell[numRows][numColumns];
		FileReader reader = null;
		try {
			reader = new FileReader(boardConfigFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in = new Scanner(reader);
		for (int i = 0; i < numRows; i++) {
			String[] row = in.nextLine().split(",");
			for (int j = 0; j < numColumns; j++) {
				String cell = row[j];
				char initial = cell.charAt(0);
				DoorDirection doorDirec = DoorDirection.NONE;
				if (cell.length() == 2) {
					switch(cell.charAt(1)) {
					case 'U':
						doorDirec = DoorDirection.UP;
						break;
					case 'D':
						doorDirec = DoorDirection.DOWN;
						break;
					case 'L':
						doorDirec = DoorDirection.LEFT;
						break;
					case 'R':
						doorDirec = DoorDirection.RIGHT;
						break;
					}
				}
				board[i][j] = new BoardCell(i, j, initial, doorDirec);
			}
		}
		in.close();
	}
	
	public Map<Character, String> getLegend() {
		return legend;
	}
	public int getNumColumns() {
		return numColumns;
	}
	
	public int getNumRows(){
		return numRows;
	}
	
	public BoardCell getCellAt(int row, int col){
		return board[row][col];
	}
	public void calcAdjacencies(){
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		for(BoardCell[] cellrow : board) {
			for(BoardCell cell : cellrow) {
				adjMatrix.put(cell, new HashSet<BoardCell>());
			}
		}
	}
	
	public void calcTargets(BoardCell cell, int pathLength){
		
	}
	
}