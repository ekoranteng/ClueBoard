package clueGame;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
		loadBoardConfig();
		calcAdjacencies();
	}
	public void setConfigFiles(String boardConfigFile, String roomConfigFile) {
		this.boardConfigFile = boardConfigFile;
		this.roomConfigFile = roomConfigFile;
	}
	public void loadRoomConfig(){
		legend = new HashMap<Character, String>();
	}
	
	public void loadBoardConfig(){
		board = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
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