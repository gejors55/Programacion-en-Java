package es.ucm.fdi.tp.conecta5;

import es.ucm.fdi.tp.base.model.GameAction;
public class ConectaAction implements GameAction<ConectaState, ConectaAction> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int player;
	private int row;
	private int col;
	private int oldRow;
	private int oldCol;

	public ConectaAction(int player, int row, int col, int oldrow, int oldcol) {
		this.player = player;
		this.row = row;
		this.col = col;
		this.oldRow = oldrow;
		this.oldCol = oldcol;
	}

	@Override
	public int getPlayerNumber() {
		return player;
	}

	@Override
	public ConectaState applyTo(ConectaState state) {
		if (player != state.getTurn()) {
			throw new IllegalArgumentException("Not the turn of this player");
		}
		// make move
		int[][] board = state.getBoard();
		board[row][col] = player;
		// update state
		ConectaState next;
		if (ConectaState.isWinner(board, state.getTurn())) {
			next = new ConectaState(state, board, true, state.getTurn());
		} else if (ConectaState.isDraw(board)) {
			next = new ConectaState(state, board, true, -1);
		} else {
			next = new ConectaState(state, board, false, -1);
		}
		return next;
	}

	public int getRow() {
		return row;
	}

	public int getRowO() {
		return this.oldRow;
	}

	public int getCol() {
		return col;
	}

	public int getColO() {
		return this.oldCol;
	}

	public String toString() {
		return "place " + player + " at (" + row + ", " + col + ")";
	}
}
