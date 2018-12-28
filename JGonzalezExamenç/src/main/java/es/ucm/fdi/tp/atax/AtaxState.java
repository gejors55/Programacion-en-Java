package es.ucm.fdi.tp.atax;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.was.WolfAndSheepState;


public class AtaxState extends GameState<AtaxState, AtaxAction> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private  int turn; // 0 wolf 1 sheep
	private final boolean finished;
	private final int[][] board;
	private final int winner;

	private final int dim;

	final static int EMPTY = -1;

	public AtaxState(int dim) {
		super(2);
		if (dim != 7) {
			throw new IllegalArgumentException("Expected dim to be 7");
		}
		this.dim = dim;
		board = new int[dim][];
		for (int i = 0; i < dim; i++) {
			board[i] = new int[dim];
			for (int j = 0; j < dim; j++) {
				if (i == 0 && j == 0) {
					board[i][j] = 0;
				} else if (i == 0 && j == 6) {
					board[i][j] = 1;
				} else if (i == 6 && j == 0) {
					board[i][j] = 1;
				} else if (i == 6 && j == 6) {
					board[i][j] = 0;
				} else
					board[i][j] = EMPTY;
			}
		}
		this.turn = 0;
		this.winner = -1;
		this.finished = false;
	}

	public AtaxState(AtaxState prev, int[][] board, boolean finished, int winner) {
		super(2);
		this.dim = prev.dim;
		this.board = board;
		this.turn = (prev.turn + 1) % 2;// pasa el turno al jugador 2
		this.finished = finished;
		this.winner = winner;
	}
	@Override
	public int setTurn(AtaxState prev) {
		this.turn = (prev.turn + 1) % 2;
		return turn;
	}

	@Override
	public int getTurn() {
		return turn;
	}

	@Override
	public List<AtaxAction> validActions(int playerNumber) {
		ArrayList<AtaxAction> valid = new ArrayList<>();
		if (finished)
			return valid;
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (playerNumber == at(i, j)) {
					if (j == 0 && i == 6) {
						if (at(i - 1, j) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i - 1, j, i,
									j));
						if (at(i - 1, j + 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i - 1,
									j + 1, i, j));
						if (at(i, j + 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i, j + 1, i,
									j));
					} else if (j == 6 && i == 6) {
						if (at(i - 1, j - 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i - 1,
									j - 1, i, j));
						if (at(i - 1, j) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i - 1, j, i,
									j));
						if (at(i, j - 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i, j - 1, i,
									j));
					} else if (j == 0 && i == 0) {
						if (at(i + 1, j + 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i + 1,
									j + 1, i, j));
						if (at(i + 1, j) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i + 1, j, i,
									j));
						if (at(i, j + 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i, j + 1, i,
									j));
					} else if (j == 6 && i == 0) {
						if (at(i + 1, j - 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i + 1,
									j - 1, i, j));
						if (at(i + 1, j) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i + 1, j, i,
									j));
						if (at(i, j - 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i, j - 1, i,
									j));
					} else if (i == 6) {
						if (at(i, j + 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i, j + 1, i,
									j));
						if (at(i, j - 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i, j - 1, i,
									j));
						if (at(i - 1, j + 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i - 1,
									j + 1, i, j));
						if (at(i - 1, j - 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i - 1,
									j - 1, i, j));
						if (at(i - 1, j) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i - 1, j, i,
									j));

					} else if (j == 0) {
						if (at(i + 1, j) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i + 1, j, i,
									j));
						if (at(i + 1, j) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i + 1, j, i,
									j));
						if (at(i, j + 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i, j + 1, i,
									j));
						if (at(i - 1, j + 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i - 1,
									j + 1, i, j));
						if (at(i + 1, j + 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i + 1,
									j + 1, i, j));
					} else if (j == 6) {
						if (at(i - 1, j) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i - 1, j, i,
									j));
						if (at(i - 1, j) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i - 1, j, i,
									j));
						if (at(i, j - 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i, j - 1, i,
									j));
						if (at(i - 1, j - 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i - 1,
									j - 1, i, j));
						if (at(i + 1, j - 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i + 1,
									j - 1, i, j));
					} else if (i == 0) {
						if (at(i, j + 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i, j + 1, i,
									j));
						if (at(i, j - 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i, j - 1, i,
									j));
						if (at(i + 1, j + 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i + 1,
									j + 1, i, j));
						if (at(i + 1, j - 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i + 1,
									j - 1, i, j));
						if (at(i + 1, j) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i + 1, j, i,
									j));
					} else {
						if (at(i , j - 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i ,
									j - 1, i, j));
						if (at(i , j + 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i ,
									j + 1, i, j));
						if (at(i - 1, j ) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i - 1,
									j , i, j));
						if (at(i - 1, j + 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i - 1,
									j + 1, i, j));
						if (at(i - 1, j - 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i - 1,
									j - 1, i, j));
						if (at(i + 1, j) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i + 1,
									j , i, j));
						if (at(i + 1, j + 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i + 1,
									j + 1, i, j));
						if (at(i + 1, j - 1) == EMPTY)
							valid.add(new AtaxAction(playerNumber, i + 1,
									j - 1, i, j));
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

	@Override
	public int getWinner() {
		return winner;
	}

	public int[][] getBoard() {
		int[][] copy = new int[board.length][];
		for (int i = 0; i < board.length; i++)
			copy[i] = board[i].clone();
		return copy;
	}

	public int getDim() {
		return this.dim;
	}

	public int at(int row, int col) {
		return board[row][col];
	}

	public static boolean isWinner(int[][] board, int playerNumber){
		return false;
	}
	// mirarr
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
