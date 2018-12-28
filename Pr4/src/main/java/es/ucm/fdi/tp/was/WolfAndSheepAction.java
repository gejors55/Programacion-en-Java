package es.ucm.fdi.tp.was;

import es.ucm.fdi.tp.base.model.GameAction;

public class WolfAndSheepAction implements
		GameAction<WolfAndSheepState, WolfAndSheepAction> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2225258245696482691L;

	private int player;
	private int row;
	private int col;
	private int oldrow;
	private int oldcol;

	public WolfAndSheepAction(int player, int row, int col, int oldrow, int oldcol) {
		this.player = player;
		this.row = row;
		this.col = col;
		this.oldrow = oldrow;
		this.oldcol = oldcol;
	}

	@Override
	public int getPlayerNumber() {
		return player;
	}

	@Override
	public WolfAndSheepState applyTo(WolfAndSheepState state) {
		if (player != state.getTurn()) {
			throw new IllegalArgumentException("Not the turn of this player");
		}
		// make move
		int[][] board = state.getBoard();
		board[row][col] = player;
		// delete last sheep position
		board[oldrow][oldcol]= -1;

		// update state
		WolfAndSheepState next;
		if (WolfAndSheepState.isWinner(board, state.getTurn())) {
			next = new WolfAndSheepState(state, board, true, state.getTurn());
		} else {
			next = new WolfAndSheepState(state, board, false, -1);
		}
		return next;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public String toString() {
		return "place " + player + " at (" + row + ", " + col + ")";
	}
}
