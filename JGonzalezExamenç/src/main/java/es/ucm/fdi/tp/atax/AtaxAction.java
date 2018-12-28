package es.ucm.fdi.tp.atax;

import es.ucm.fdi.tp.base.model.GameAction;

public class AtaxAction implements GameAction<AtaxState, AtaxAction> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int player;
	private int row;
	private int col;
	private int oldRow;
	private int oldCol;

	public AtaxAction(int player, int row, int col, int oldRow, int oldCol) {
		this.player = player;
		this.row = row;
		this.col = col;
		this.oldRow = oldRow;
		this.oldCol = oldCol;
	}

	@Override
	public int getPlayerNumber() {
		return player;
	}

	@Override
	public AtaxState applyTo(AtaxState state) {
		if (player != state.getTurn()) {
			throw new IllegalArgumentException("Not the turn of this player");
		}
		// make move
		int[][] board = state.getBoard();
		board[row][col] = player;
		if (player == 0) {
			if (row < state.getDim() && row >= 0 && col - 1 < state.getDim()
					&& col - 1 >= 0) {
				if (board[row][col - 1] == 1)
					board[row][col - 1] = 0;
			}
			if (row < state.getDim() && row >= 0 && col + 1 < state.getDim()
					&& col + 1 >= 0) {
				if (board[row][col + 1] == 1)
					board[row][col + 1] = 0;
			}
			if (row - 1 < state.getDim() && row - 1 >= 0
					&& col < state.getDim() && col >= 0) {
				if (board[row - 1][col] == 1)
					board[row - 1][col] = 0;
			}
			if (row + 1 < state.getDim() && row + 1 >= 0
					&& col < state.getDim() && col >= 0) {
				if (board[row + 1][col] == 1)
					board[row + 1][col] = 0;
			}
			if (row + 1 < state.getDim() && row + 1 >= 0
					&& col + 1 < state.getDim() && col + 1 >= 0) {
				if (board[row + 1][col + 1] == 1)
					board[row + 1][col + 1] = 0;
			}
			if (row - 1 < state.getDim() && row - 1 >= 0
					&& col - 1 < state.getDim() && col - 1 >= 0) {
				if (board[row - 1][col - 1] == 1)
					board[row - 1][col - 1] = 0;
			}
			if (row + 1 < state.getDim() && row + 1 >= 0
					&& col - 1 < state.getDim() && col - 1 >= 0) {
				if (board[row + 1][col - 1] == 1)
					board[row + 1][col - 1] = 0;
			}
			if (row - 1 < state.getDim() && row - 1 >= 0
					&& col + 1 < state.getDim() && col + 1 >= 0) {
				if (board[row - 1][col + 1] == 1)
					board[row - 1][col + 1] = 0;
			}
		} else {
			if (row < state.getDim() && row >= 0 && col - 1 < state.getDim()
					&& col - 1 >= 0) {
				if (board[row][col - 1] == 0)
					board[row][col - 1] = 1;
			}
			if (row < state.getDim() && row >= 0 && col + 1 < state.getDim()
					&& col + 1 >= 0) {
				if (board[row][col + 1] == 0)
					board[row][col + 1] = 1;
			}
			if (row - 1 < state.getDim() && row - 1 >= 0
					&& col < state.getDim() && col >= 0) {
				if (board[row - 1][col] == 0)
					board[row - 1][col] = 1;
			}
			if (row + 1 < state.getDim() && row + 1 >= 0
					&& col < state.getDim() && col >= 0) {
				if (board[row + 1][col] == 0)
					board[row + 1][col] = 1;
			}
			if (row + 1 < state.getDim() && row + 1 >= 0
					&& col + 1 < state.getDim() && col + 1 >= 0) {
				if (board[row + 1][col + 1] == 0)
					board[row + 1][col + 1] = 1;
			}
			if (row - 1 < state.getDim() && row - 1 >= 0
					&& col - 1 < state.getDim() && col - 1 >= 0) {
				if (board[row - 1][col - 1] == 0)
					board[row - 1][col - 1] = 1;
			}
			if (row + 1 < state.getDim() && row + 1 >= 0
					&& col - 1 < state.getDim() && col - 1 >= 0) {
				if (board[row + 1][col - 1] == 0)
					board[row + 1][col - 1] = 1;
			}
			if (row - 1 < state.getDim() && row - 1 >= 0
					&& col + 1 < state.getDim() && col + 1 >= 0) {
				if (board[row - 1][col + 1] == 0)
					board[row - 1][col + 1] = 1;
			}
		}
		// update state
		AtaxState next;
		if (AtaxState.isWinner(board, state.getTurn())) {
			next = new AtaxState(state, board, true, state.getTurn());
		} else {
			next = new AtaxState(state, board, false, -1);
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
