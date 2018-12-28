package es.ucm.fdi.tp.was;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;

public class WolfAndSheepStateTest {
	//ATRIBUTOS.
	private int[][] board;
	private static final int DIM = 8;
	final static int EMPTY = -1;
	final static int WOLF = 0;
	final static int SHEEP = 1;
	// METODOS.
	public void inicializaTablero(int board[][]){
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				this.board[i][j] = EMPTY;
			}
		}
	}
	//-------------------------------------------------------------------
	@Test
	public void wolfValidAction() throws IOException {
		WolfAndSheepState stateAux = new WolfAndSheepState(8);
		assertEquals(1, stateAux.validActions(WOLF).size());
		stateAux = takeRandomAction(stateAux);
		assertEquals(4, stateAux.validActions(WOLF).size());
	}
	//-------------------------------------------------------------------
	@Test
	public void WolfWin() throws IOException {
		this.board = new int[DIM][DIM];
		inicializaTablero(this.board);
		for (int i = 1; i < DIM; i += 2) {
			board[0][i] = WOLF;
			assertTrue(WolfAndSheepState.isWinner(board, WOLF));
			board[0][i] = EMPTY;
		}
	}
	//-------------------------------------------------------------------
	@Test
	public void wolfLose() throws IOException {
		this.board = new int[DIM][DIM];
		inicializaTablero(this.board);
		board[1][2] = SHEEP;
		board[1][4] = SHEEP;
		board[3][2] = SHEEP;
		board[3][4] = SHEEP;
		board[2][3] = WOLF;
		assertTrue(WolfAndSheepState.isWinner(this.board, SHEEP));
	}
	//-------------------------------------------------------------------
	@Test
	public void sheepMoves() throws IOException {
		WolfAndSheepState state = new WolfAndSheepState(DIM);
		this.board = new int[DIM][DIM];
		inicializaTablero(this.board);
		
		int j = 2;
		for (int i = 1; i < 8; i += 2) {
			if (i == 7)
				j = 1;
			this.board[0][i] = 1;
			WolfAndSheepState auxState = new WolfAndSheepState(state, this.board, false, -1);
			assertEquals(j, auxState.validActions(1).size());
			this.board[0][i] = -1;
		}
		/*
		j = 0;
		for (int i = 1; i < 7; i += 2) {
			this.board[i][j] = 1;
			WolfAndSheepState auxState = new WolfAndSheepState(state, this.board, false, -1);
			assertEquals(1, auxState.validActions(1).size());
			this.board[i][j] = -1;
		}
		j = 7;
		for (int i = 0; i < 7; i += 2) {
			this.board[i][j] = 1;
			WolfAndSheepState auxState = new WolfAndSheepState(state, this.board, false, -1);
			assertEquals(1, auxState.validActions(1).size());
			this.board[i][j] = -1;
		}*/
	}
	//-------------------------------------------------------------------
	private static <S extends GameState<S, A>, A extends GameAction<S, A>> S takeRandomAction(
			S state) {
		List<A> actions = state.validActions(state.getTurn());
		return actions.get(new Random().nextInt(actions.size())).applyTo(state);
	}
}
