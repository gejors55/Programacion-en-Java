package es.ucm.fdi.tp.was;

import es.ucm.fdi.tp.base.model.GameAction;

/**
 * Describe cómo pasar de un estado a otro, aunque no contiene el estado
 * destino.
 * 
 * @author Jorge González Soria y Lázaro Clemen Palafox
 *
 */
public class WolfAndSheepAction implements
		GameAction<WolfAndSheepState, WolfAndSheepAction> {

	private static final long serialVersionUID = -2225258245696482691L;

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
	public WolfAndSheepAction(int player, int row, int col, int oldRow,
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
	public WolfAndSheepState applyTo(WolfAndSheepState state) {
		if (player != state.getTurn()) {
			throw new IllegalArgumentException("Not the turn of this player");
		}
		int[][] board = state.getBoard();
		// delete last sheep position
		board[oldRow][oldCol] = -1;
		board[row][col] = player;
		// update state
		WolfAndSheepState next;
		if (WolfAndSheepState.isWinner(board, state.getTurn())) {
			next = new WolfAndSheepState(state, board, true, state.getTurn());
		} else {
			next = new WolfAndSheepState(state, board, false, -1);
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