package es.ucm.fdi.tp.was;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameState;

public class WolfAndSheepState extends GameState<WolfAndSheepState, WolfAndSheepAction> {
	//ATRIBUTOS.
	private static final long serialVersionUID = -2641387354190472377L;
	private final int turn;
    private final boolean finished;
    private final int[][] board;
    private final int winner;
    private final int dim;
    final static int EMPTY = -1;
    //CONSTRUCTORA.
    public WolfAndSheepState(int dim) {    	
        super(2);
        if (dim != 8) {
            throw new IllegalArgumentException("Expected dim to be 8");
        }

        this.dim = dim;
        board = new int[dim][];
        for (int i=0; i<dim; i++) {
            board[i] = new int[dim];
            for (int j=0; j<dim; j++){
            	if(j%2 == 1 && i == 0){
            		board[i][j] = 1;
            	}else if(i == 7 && j == 0){
            		board[i][j] = 0;
            	}else board[i][j] = EMPTY;
            }
        }
        this.turn = 0;
        this.winner = -1;
        this.finished = false;
    }
    //---------------------------------------------------------------------------    
    public WolfAndSheepState(WolfAndSheepState prev, int[][] board, boolean finished, int winner) {
    	super(2);
    	this.dim = prev.dim;
        this.board = board;
        this.turn = (prev.turn + 1) % 2;
        this.finished = finished;
        this.winner = winner;
    }    
    //METODOS.
    public boolean isValid(WolfAndSheepAction action) {
        if (isFinished()) {
            return false;
        }
        return at(action.getRow(), action.getCol()) == EMPTY;
    }
    //---------------------------------------------------------------------------
    public List<WolfAndSheepAction> validActions(int playerNumber) {
        ArrayList<WolfAndSheepAction> valid = new ArrayList<>();
        int posCol = 0, posRow = 0;
        if (finished) {
            return valid;
        }

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (at(i, j) == playerNumber) {
                	if(i+1 < dim){
                		if(j-1 >= 0 && at(i+1, j-1) == EMPTY)valid.add(new WolfAndSheepAction(playerNumber, i+1, j-1, i, j));
                		if(j+1 < dim && at(i+1, j+1) == EMPTY)valid.add(new WolfAndSheepAction(playerNumber, i+1, j+1, i, j));
                	}
                	if(playerNumber == 0){
                    	if(i-1 >= 0){
                    		if(j-1 >= 0 && at(i-1, j-1) == EMPTY)valid.add(new WolfAndSheepAction(playerNumber, i-1, j-1, i, j));
                    		if(j+1 < dim && at(i-1, j+1) == EMPTY)valid.add(new WolfAndSheepAction(playerNumber, i-1, j+1,i ,j));
                    	}
                	}
                	posRow = i;
                	posCol = j;
                }
            }
        }
        if(valid.isEmpty()){
        	valid.add(new WolfAndSheepAction(playerNumber, posRow, posCol, posRow, posCol));
        }
        return valid;
    }
    //---------------------------------------------------------------------------
    private static boolean isWinner(int[][] board, 
    		int x, int y) {
    	boolean won = false;
    	int cont = 0;
		if (x + 1 < board.length) {
			if (y - 1 >= 0 && board[x + 1][y - 1] == EMPTY) cont++;
			if (y + 1 < board.length && board[x + 1][y + 1] == EMPTY) cont++;
		}
		if (x - 1 >= 0) {
			if (y - 1 >= 0 && board[x - 1][y - 1] == EMPTY) cont++;
			if (y + 1 < board.length && board[x - 1][y + 1] == EMPTY) cont++;
		}
		if (cont == 0)won = true;
		return won;
    }
    //---------------------------------------------------------------------------
    public static boolean isWinner(int[][] board, int playerNumber) {
        boolean won = false;
        int posCol = 0, posRow = 0;
        if(playerNumber == 0){
        	for(int i=0; !won && i<board.length; i++){
        		if(board[0][i] == playerNumber)won = true;
        	}
        }else{
        	for(int i=0; i<board.length; i++){
        		for(int j=0; j<board.length; j++){
            		if(board[i][j] == 0){
            			posRow = i;
            			posCol = j;
            		}
            	}
        	}
            if (isWinner(board, posRow, posCol)) won = true;
        }
        return won;
    }    
    //---------------------------------------------------------------------------
    public int at(int row, int col) {
        return board[row][col];
    }
    //---------------------------------------------------------------------------
    public int getTurn() {
        return turn;
    }
    //---------------------------------------------------------------------------
    public boolean getEmpty(int row,int col){
    	if(board[row][col] == EMPTY) return true;
    	else return false;
    }
    //---------------------------------------------------------------------------
    public int getDim(){
    	return this.dim;
    }
    //---------------------------------------------------------------------------
    public boolean isFinished() {
        return finished;
    }
    //---------------------------------------------------------------------------
    public int getWinner() {
        return winner;
    }
    //---------------------------------------------------------------------------
    /**
     * @return a copy of the board
     */
    public int[][] getBoard() {
        int[][] copy = new int[board.length][];
        for (int i=0; i<board.length; i++) copy[i] = board[i].clone();
        return copy;
    }
    //---------------------------------------------------------------------------
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<board.length; i++) {
            sb.append("|");
            for (int j=0; j<board.length; j++) {
                sb.append(board[i][j] == EMPTY ? "   |" : board[i][j] == 0 ? " O |" : " X |");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
