package es.ucm.fdi.tp.damas;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameState;


public class damasState extends GameState<damasState, damasAction> {
	private static final long serialVersionUID = 3215156352054119135L;

	private final int turn; // 0 wolf 1 sheep
	private final boolean finished;
	private final int[][] board;
	private final int winner;

	private final int dim;

	final static int EMPTY = -1;

	/**
	 * 
	 * @param dim
	 *            Dimension tablero.
	 */
	public damasState(int dim) {
		super(2);
		if (dim != 8) {
			throw new IllegalArgumentException("Expected dim to be 8");
		}

		this.dim = dim;
		board = new int[dim][];
		for (int i = 0; i < dim; i++) {
			board[i] = new int[dim];
			for (int j = 0; j < dim; j++) {
				if ((i == 7) && (j == 1 || j == 3 || j == 5 || j == 7))
					board[i][j] = 0;
				else if (i == 6 && (j == 0 || j == 2 || j == 4 || j == 6 || j == 8))
					board[i][j] = 0;
				else if (i == 5 && (j == 1 || j == 3 || j == 5 || j == 7))
					board[i][j] = 0;
				else if (i == 0 && (j == 0 || j == 2 || j == 4 || j == 6 || j == 8))
					board[i][j] = 1;
				else if (i == 1 && (j == 1 || j == 3 || j == 5 || j == 7))
					board[i][j] = 1;
				else if (i == 2 && (j == 0 || j == 2 || j == 4 || j == 6 || j == 8))
					board[i][j] = 1;
				else
					board[i][j] = EMPTY;
			}
		}
		this.turn = 0;
		this.winner = -1;
		this.finished = false;
	}


	public damasState(damasState prev, int[][] board,
			boolean finished, int winner) {
		super(2);
		this.dim = prev.dim;
		this.board = board;
		this.turn = (prev.turn + 1) % 2;
		this.finished = finished;
		this.winner = winner;
	}

	@Override
	public int getWinner() {
		return winner;
	}

	@Override
	public int getTurn() {
		return turn;
	}

	@Override
	public List<damasAction> validActions(int playerNumber) {
		ArrayList<damasAction> valid = new ArrayList<>();
		if (finished)
			return valid;
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (playerNumber == at(i, j) && playerNumber == 0) {
					
				} else if (playerNumber == at(i, j) && playerNumber == 1) {
		
				}
			}
		}
		return valid;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

	/**
	 * 
	 * @return Tablero copia.
	 */
	public int[][] getBoard() {
		int[][] copy = new int[board.length][];
		for (int i = 0; i < board.length; i++)
			copy[i] = board[i].clone();
		return copy;
	}

	public int getDim(){
		return this.dim;
	}

	/**
	 * Comprueba que jugador esta en la casilla indicada.
	 * 
	 * @param row
	 *            Fila.
	 * @param col
	 *            Columna.
	 * @return
	 */
	public int at(int row, int col) {
		return board[row][col];
	}

	/**
	 * 
	 * @param board
	 *            Tablero.
	 * @param playerNumber
	 *            Numero del jugador.
	 * @return Boolean indicando si el jugador del turno a ganado al aplicar la
	 *         jugada.
	 */
	public static boolean isWinner(int[][] board, int playerNumber) {
		return false;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < board.length; i++) {
			sb.append("|");
			for (int j = 0; j < board.length; j++) {
				sb.append(board[i][j] == EMPTY ? "   |"
						: board[i][j] == 0 ? " O |" : " X |");
			}
			sb.append("\n");
		}
		return sb.toString();
	}


	@Override
	public int setTurn(damasState state) {
		// TODO Auto-generated method stub
		return 0;
	}


}
