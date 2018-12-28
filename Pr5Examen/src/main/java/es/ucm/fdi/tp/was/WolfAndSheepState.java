package es.ucm.fdi.tp.was;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameState;

/**
 * Representa el estado completo de un juego, incluyendo todo lo necesario para
 * poder seguir jugando en otro momento.
 * 
 * @author Jorge González Soria y Lázaro Clemen Palafox
 *
 */
public class WolfAndSheepState extends
		GameState<WolfAndSheepState, WolfAndSheepAction> {

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
	public WolfAndSheepState(int dim) {
		super(2);
		if (dim != 8) {
			throw new IllegalArgumentException("Expected dim to be 8");
		}

		this.dim = dim;
		board = new int[dim][];
		for (int i = 0; i < dim; i++) {
			board[i] = new int[dim];
			for (int j = 0; j < dim; j++) {
				if (i == 7 && j == 0)
					board[i][j] = 0;
				else if (i == 0 && (j == 1 || j == 3 || j == 5 || j == 7))
					board[i][j] = 1;
				else
					board[i][j] = EMPTY;
			}
		}
		this.turn = 0;
		this.winner = -1;
		this.finished = false;
	}

	/**
	 * 
	 * @param prev
	 *            Estado previo.
	 * @param board
	 *            Tablero.
	 * @param finished
	 *            Boolean indicandosi la partida esta terminada.
	 * @param winner
	 *            Numero del jugador ganador.
	 */
	public WolfAndSheepState(WolfAndSheepState prev, int[][] board,
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
	public List<WolfAndSheepAction> validActions(int playerNumber) {
		ArrayList<WolfAndSheepAction> valid = new ArrayList<>();
		ArrayList<WolfAndSheepAction> validAux = new ArrayList<>();
		int n = 0;
		if (finished)
			return valid;
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (playerNumber == at(i, j) && playerNumber == 0) {
					if (j == 0 && i == 7) {
						if (at(i - 1, j + 1) == EMPTY)
							valid.add(new WolfAndSheepAction(playerNumber,
									i - 1, j + 1, i, j));
					} else if (i == 7) {
						if (at(i - 1, j + 1) == EMPTY)
							valid.add(new WolfAndSheepAction(playerNumber,
									i - 1, j + 1, i, j));
						if (at(i - 1, j - 1) == EMPTY)
							valid.add(new WolfAndSheepAction(playerNumber,
									i - 1, j - 1, i, j));
					} else if (j == 0) {
						if (at(i - 1, j + 1) == EMPTY)
							valid.add(new WolfAndSheepAction(playerNumber,
									i - 1, j + 1, i, j));
						if (at(i + 1, j + 1) == EMPTY)
							valid.add(new WolfAndSheepAction(playerNumber,
									i + 1, j + 1, i, j));
					} else if (j == 7) {
						if (at(i - 1, j - 1) == EMPTY)
							valid.add(new WolfAndSheepAction(playerNumber,
									i - 1, j - 1, i, j));
						if (at(i + 1, j - 1) == EMPTY)
							valid.add(new WolfAndSheepAction(playerNumber,
									i + 1, j - 1, i, j));
					} else {
						if (at(i + 1, j - 1) == EMPTY)
							valid.add(new WolfAndSheepAction(playerNumber,
									i + 1, j - 1, i, j));
						if (at(i - 1, j - 1) == EMPTY)
							valid.add(new WolfAndSheepAction(playerNumber,
									i - 1, j - 1, i, j));
						if (at(i - 1, j + 1) == EMPTY)
							valid.add(new WolfAndSheepAction(playerNumber,
									i - 1, j + 1, i, j));
						if (at(i + 1, j + 1) == EMPTY)
							valid.add(new WolfAndSheepAction(playerNumber,
									i + 1, j + 1, i, j));
					}
				} else if (playerNumber == at(i, j) && playerNumber == 1) {
					if (j == 0 && i == 7) {
					} else if (j == 7) {
						if (at(i + 1, j - 1) == EMPTY)
							valid.add(new WolfAndSheepAction(playerNumber,
									i + 1, j - 1, i, j));
					} else if (i == 7) {
					} else if (j == 0) {
						if (at(i + 1, j + 1) == EMPTY)
							valid.add(new WolfAndSheepAction(playerNumber,
									i + 1, j + 1, i, j));
					} else {
						if (at(i + 1, j + 1) == EMPTY)
							valid.add(new WolfAndSheepAction(playerNumber,
									i + 1, j + 1, i, j));
						if (at(i + 1, j - 1) == EMPTY)
							valid.add(new WolfAndSheepAction(playerNumber,
									i + 1, j - 1, i, j));
					}
					if (valid.isEmpty()) {
						n++;
						validAux.add(new WolfAndSheepAction(playerNumber, i, j,
								i, j));
						if (n == 4)
							valid = validAux;
					}
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
		boolean win = false;
		if (playerNumber == 0) {
			if (board[7][0] == 0 && board[7][2] == 0 && board[7][4] == 0
					&& board[7][6] == 0)
				return true;
			else {
				for (int a = 0; !win && a < board.length; a++) {
					if (board[0][a] == playerNumber) {
						win = true;
					}
				}
			}
		} else if (playerNumber == 1) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (board[i][j] == 0) {
						if (i == 7 && j == 0)
							return isWinner(board, i - 1, j + 1);
						else if (i == 7)
							return isWinner(board, i - 1, j - 1)
									&& isWinner(board, i - 1, j + 1);
						else if (j == 0)
							return isWinner(board, i - 1, j + 1)
									&& isWinner(board, i + 1, j + 1);
						else if (j == 7)
							return isWinner(board, i - 1, j - 1)
									&& isWinner(board, i + 1, j - 1);
						else
							return isWinner(board, i - 1, j - 1)
									&& isWinner(board, i + 1, j + 1)
									&& isWinner(board, i - 1, j + 1)
									&& isWinner(board, i + 1, j - 1);
					}

				}
			}
		}
		return win;
	}

	/**
	 * 
	 * @param board
	 *            Tablero.
	 * @param i
	 *            Fila.
	 * @param j
	 *            Columna.
	 * @return Boolean indicando si en la casilla indicada esta el lobo.
	 */
	public static boolean isWinner(int[][] board, int i, int j) {
		return board[i][j] == 1;
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

}