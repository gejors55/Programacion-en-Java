package es.ucm.fdi.tp.was;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.ttt.TttState;

public class WolfAndSheepAction implements GameAction<WolfAndSheepState, WolfAndSheepAction>{
	//ATRIBUTOS.
	private static final long serialVersionUID = -8491198872908329925L;
	private int player;
    private int row;
    private int col;
    private int oldRow;
    private int oldCol;
    //CONSTRUCTORAS.
    public WolfAndSheepAction(int player, int row, int col, int oldRow, int oldCol) {
        this.player = player;
        this.row = row;
        this.col = col;
        this.oldRow = oldRow;
        this.oldCol = oldCol;
    }
    //METODOS.
    public int getPlayerNumber() {
        return player;
    }
    //---------------------------------------------------------------------------
    public WolfAndSheepState applyTo(WolfAndSheepState state) {
        if (player != state.getTurn()) {
            throw new IllegalArgumentException("Not the turn of this player");
        }
        // make move
        int[][] board = state.getBoard();
        board[oldRow][oldCol] = -1;
        board[row][col] = player;

        // update state
        WolfAndSheepState next;
        if (WolfAndSheepState.isWinner(board, state.getTurn())) {
            next = new WolfAndSheepState(state, board, true, state.getTurn());
        }
        else {
            next = new WolfAndSheepState(state, board, false, -1);
        }
        return next;
    }
    //---------------------------------------------------------------------------
    public int getRow() {
        return row;
    }
    //---------------------------------------------------------------------------
    public int getCol() {
        return col;
    }
    //---------------------------------------------------------------------------
    public int getOldRow() {
        return oldRow;
    }
    //---------------------------------------------------------------------------
    public int getOldCol() {
        return oldCol;
    }
    //---------------------------------------------------------------------------
    public String toString() {
        return "place " + player + " at (" + row + ", " + col + ")";
    }
}
