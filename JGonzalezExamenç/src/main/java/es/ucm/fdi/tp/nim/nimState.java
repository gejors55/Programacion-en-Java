package es.ucm.fdi.tp.nim;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameState;


public class nimState extends GameState<nimState, nimAction> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int turn; // 0 player0 1 player1
	private final boolean finished;
	private final int[][] board;
	private final int winner;
	
	private final int dimCol;
	private final int dimFil;
	final static int EMPTY = -1;

	public nimState(int dimCol, int dimFil) {
		super(2);
		if (dimCol != 7 && dimFil !=5) {
			throw new IllegalArgumentException("Expected dim to be 7 columna y 5 filas");
		}

		this.dimCol = dimCol;
		this.dimFil = dimFil;
		board = new int[dimFil][];
		for (int i = 0; i < dimFil; i++) {
			board[i] = new int[dimCol];
			for (int j = 0; j < dimCol; j++) {
				if (i == 0 && (j == 0 || j == 1 || j == 2))
					board[i][j] = 0;
				else if (i == 2 && (j == 0 || j == 1 || j == 2 || j == 3 || j == 4))
					board[i][j] = 0;
				else if (i == 4 && (j == 0 || j == 1 || j == 2 || j == 3 || j == 4|| j== 5 || j == 6))
					board[i][j] = 0;	
				else
					board[i][j] = EMPTY;
			}
		}
		this.turn = 0;
		this.winner = -1;
		this.finished = false;
	}
	
	@Override
	public int setTurn(nimState prev) {
		this.turn = (prev.turn + 1) % 2;
		return turn;
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
	public nimState(nimState prev, int[][] board,
			boolean finished, int winner) {
		super(2);
		this.dimCol = prev.dimCol;
		this.dimFil = prev.dimFil;
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
	public List<nimAction> validActions(int playerNumber) {
		ArrayList<nimAction> valid = new ArrayList<>();
		for(int i = 0; i<dimFil; i = i+2){
			for(int j = 0; j< dimCol;j = j+2){
				
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
	
	public int getDimFil(){
		return this.dimFil;
	}
	public int getDimCol(){
		return this.dimCol;
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
		for (int i = 0; i < dimFil; i++) {
			sb.append("|");
			for (int j = 0; j < dimCol; j++) {
				sb.append(board[i][j] == EMPTY ? "   |"
						: board[i][j] == 0 ? " O |" : " X |");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
