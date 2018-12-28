package es.ucm.fdi.tp.damas;

import es.ucm.fdi.tp.base.model.GameAction;


public class damasAction implements GameAction<damasState, damasAction> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int player;
	private int row;
	private int col;
	private int oldRow;
	private int oldCol;

	/**
	 * 
	 * @param player
	 *            Jugador.
	 * @param row
	 *            Fila.
	 * @param col
	 *            Columna
	 * @param oldRow
	 *            Fila antes de aplicar la accion.
	 * @param oldCol
	 *            Columna antes de aplicar la accion.
	 */
	public damasAction(int player, int row, int col, int oldRow,
			int oldCol) {
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
	public damasState applyTo(damasState state) {
		if (player != state.getTurn()) {
			throw new IllegalArgumentException("Not the turn of this player");
		}
		// make move
		int[][] board = state.getBoard();
		// delete last sheep position
		board[oldRow][oldCol] = -1;
		board[row][col] = player;
		// update state
		damasState next;
		if (damasState.isWinner(board, state.getTurn())) {
			next = new damasState(state, board, true, state.getTurn());
		} else {
			next = new damasState(state, board, false, -1);
		}
		return next;
	}

	/**
	 * 
	 * @return Fila.
	 */
	public int getRow() {
		return row;
	}
	
	public int getRowO() {
		return this.oldRow;
	}

	/**
	 * 
	 * @return Columna.
	 */
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
