package es.ucm.fdi.tp.nim;

import es.ucm.fdi.tp.base.model.GameAction;


public class nimAction implements GameAction<nimState, nimAction> {

	private static final long serialVersionUID = -2225258245696482691L;

	private int player;
	private int row;
	private int col;
	private int colFin;


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
	public nimAction(int player, int row, int col, int colFin) {
		this.player = player;
		this.row = row;
		this.col = col;
		this.colFin = colFin;
	}

	@Override
	public int getPlayerNumber() {
		return player;
	}

	@Override
	public nimState applyTo(nimState state) {
		if (player != state.getTurn()) {
			throw new IllegalArgumentException("Not the turn of this player");
		}
		// make move
		int[][] board = state.getBoard();
		// delete last sheep position
		board[row][col] = -1;
		// update state
		nimState next;
		if (nimState.isWinner(board, state.getTurn())) {
			next = new nimState(state, board, true, state.getTurn());
		} else {
			next = new nimState(state, board, false, -1);
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
	
	public int getcolFin() {
		return this.colFin;
	}

	/**
	 * 
	 * @return Columna.
	 */
	public int getCol() {
		return col;
	}

	public String toString() {
		return "delete " + player + " at (" + row + ", " + col + ")";
	}
}
