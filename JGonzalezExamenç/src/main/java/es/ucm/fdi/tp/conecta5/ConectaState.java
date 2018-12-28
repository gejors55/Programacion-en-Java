package es.ucm.fdi.tp.conecta5;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.atax.AtaxState;
import es.ucm.fdi.tp.base.model.GameState;


public class ConectaState extends GameState<ConectaState, ConectaAction> {

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

	public ConectaState(int dim) {
		super(2);
		if (dim != 6) {
			throw new IllegalArgumentException("Expected dim to be 5");
		}
		this.dim = dim;
		board = new int[dim][];
		for (int i = 0; i < dim; i++) {
			board[i] = new int[dim];
			for (int j = 0; j < dim; j++) {
				board[i][j] = EMPTY;
			}
		}
		this.turn = 0;
		this.winner = -1;
		this.finished = false;

	}

	public ConectaState(ConectaState prev, int[][] board, boolean finished,
			int winner) {
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
	public int setTurn(ConectaState prev) {
		this.turn = (prev.turn + 1) % 2;
		return turn;
	}
	@Override
	public List<ConectaAction> validActions(int playerNumber) {
		ArrayList<ConectaAction> valid = new ArrayList<>();
		for (int j = 0; j < dim; j++) {
			for (int i = dim - 1; i >= 0 && j < dim;) {
				if (board[i][j] == EMPTY) {
					valid.add(new ConectaAction(playerNumber, i, j, i, j));
					j++;
					i = dim - 1;

				} else {
					i--;
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

	public static boolean isDraw(int[][] board) {
		boolean empty = false;
		for (int i = 0; !empty && i < board.length; i++) {
			for (int j = 0; !empty && j < board.length; j++) {
				if (board[i][j] == EMPTY) {
					empty = true;
				}
			}
		}
		return !empty;
	}

	public int at(int row, int col) {
		return board[row][col];
	}

	private static boolean isWinner(int[][] board, int playerNumber, int x0,
			int y0, int dx, int dy) {
		boolean won = true;
		for (int i = 0, x = x0, y = y0; won && i < board.length; i++, x += dx, y += dy) {
			if (board[y][x] != playerNumber)
				won = false;
		}
		return won;
	}

	public static boolean isWinner(int[][] board, int playerNumber) {
		boolean won = false;
		for (int i = 0; !won && i < board.length; i++) {
			if (isWinner(board, playerNumber, 0, i, 1, 0))
				won = true;//filas
			if (isWinner(board, playerNumber, i, 0, 0, 1))
				won = true;//columnas
		}
		return won || isWinner(board, playerNumber, 0, 0, 1, 1)
				|| isWinner(board, playerNumber, 5, 0, -1, 1);
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
