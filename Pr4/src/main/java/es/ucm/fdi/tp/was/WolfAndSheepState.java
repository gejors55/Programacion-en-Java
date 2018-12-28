package es.ucm.fdi.tp.was;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameState;

public class WolfAndSheepState extends
		GameState<WolfAndSheepState, WolfAndSheepAction> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3215156352054119135L;

	private final int turn; // 0 wolf 1 sheep
	private final boolean finished;
	private final int[][] board;
	private final int winner;

	private final int dim;

	final static int EMPTY = -1;

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
	public int getTurn() {
		return turn;
	}

	@Override
	public List<WolfAndSheepAction> validActions(int playerNumber) {
		ArrayList<WolfAndSheepAction> valid = new ArrayList<>();
		if (finished)
			return valid;

		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (playerNumber == at(i, j) && playerNumber == 0) {
					board[i][j] = EMPTY;
					if (j == 0 && i == 7) {
						if (at(i - 1, j + 1) == -1)
							valid.add(new WolfAndSheepAction(playerNumber,
									i - 1, j + 1, i, j));
					} else if (i == 7) {
						if (at(i - 1, j + 1) == -1)
							valid.add(new WolfAndSheepAction(playerNumber,
									i - 1, j + 1, i, j));
						if (at(i - 1, j - 1) == -1)
							valid.add(new WolfAndSheepAction(playerNumber,
									i - 1, j - 1, i, j));
					} else if (j == 0) {
						if (at(i - 1, j + 1) == -1)
							valid.add(new WolfAndSheepAction(playerNumber,
									i - 1, j + 1, i, j));
						if (at(i + 1, j + 1) == -1)
							valid.add(new WolfAndSheepAction(playerNumber,
									i + 1, j + 1, i, j));
					} else if (j == 7) {
						if (at(i - 1, j - 1) == -1)
							valid.add(new WolfAndSheepAction(playerNumber,
									i - 1, j - 1, i, j));
						if (at(i + 1, j - 1) == -1)
							valid.add(new WolfAndSheepAction(playerNumber,
									i - 1, j - 1, i, j));
					} else {
						if (at(i + 1, j - 1) == -1)
							valid.add(new WolfAndSheepAction(playerNumber,
									i + 1, j - 1, i, j));
						if (at(i - 1, j - 1) == -1)
							valid.add(new WolfAndSheepAction(playerNumber,
									i - 1, j - 1, i, j));
						if (at(i - 1, j + 1) == -1)
							valid.add(new WolfAndSheepAction(playerNumber,
									i - 1, j + 1, i, j));
						if (at(i + 1, j + 1) == -1)
							valid.add(new WolfAndSheepAction(playerNumber,
									i + 1, j + 1, i, j));
					}
				} else if (playerNumber == at(i, j) && playerNumber == 1) {
					// board[i][j]= EMPTY;
					if (j == 7) {
						if (at(i + 1, j - 1) == -1)
							valid.add(new WolfAndSheepAction(playerNumber,
									i + 1, j - 1, i, j));
					} else if (j == 0) {
						if (at(i + 1, j + 1) == -1)
							valid.add(new WolfAndSheepAction(playerNumber,
									i + 1, j + 1, i, j));
					} else if (i == 7) {
						// no puede hacer nada.
					} else {
						if (at(i + 1, j + 1) == -1)
							valid.add(new WolfAndSheepAction(playerNumber,
									i + 1, j + 1, i, j));
						if (at(i + 1, j - 1) == -1)
							valid.add(new WolfAndSheepAction(playerNumber,
									i + 1, j - 1, i, j));
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

	public int at(int row, int col) {
		return board[row][col];
	}

	public static boolean isWinner(int[][] board, int playerNumber) {
		boolean win = false;
		if (playerNumber == 0) {
			for (int a = 0; !win && a < board.length; a++) {
				if (board[0][a] == playerNumber) {
					win = true;
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

	public static boolean isWinner(int[][] board, int i, int j) {
		if (board[i][j] == 1)
			return true;
		else
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

}
