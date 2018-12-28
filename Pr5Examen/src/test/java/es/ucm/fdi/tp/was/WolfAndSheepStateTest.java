package es.ucm.fdi.tp.was;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;

/**
 * comprueba que: 
 * 1) un lobo rodeado resulta en victoria de las ovejas 
 * 2) un lobo en una casilla con y = 0 resulta en victoria del lobo 
 * 3) un lobo en su posición inicial sólo tiene 1 acción válida; y tras llevarla a cabo, en su
 *    siguiente turno, tiene 4 acciones válidas. 
 * 4) una oveja en su posición inicial tiene 2 acciones válidas; y si está en un lateral, tiene 2 acciones
 *    válidas.
 * 
 * @author Jorge González Soria y Lázaro Clemen Palafox
 *
 */
public class WolfAndSheepStateTest {
	private int[][] board;

	/**
	 * un lobo rodeado resulta en victoria de las ovejas.
	 * 
	 * @throws IOException
	 */
	@Test
	public void test1() throws IOException {
		int[][] board = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = WolfAndSheepState.EMPTY;
			}
		}
		board[0][1] = 1;
		board[0][3] = 1;
		board[2][1] = 1;
		board[2][3] = 1;
		board[1][2] = 0;
		assertTrue(WolfAndSheepState.isWinner(board, 1));
	}

	/**
	 * un lobo en una casilla con y = 0 resulta en victoria de lobo.
	 * 
	 * @throws IOException
	 */
	@Test
	public void test2() throws IOException {
		int[][] board = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = WolfAndSheepState.EMPTY;
			}
		}
		for (int i = 0; i < 8; i++) {
			board[0][i] = 0;
			assertTrue(WolfAndSheepState.isWinner(board, 0));
			board[0][i] = -1;
		}
	}

	/**
	 * un lobo en su posición inicial sólo tiene 1 acción válida; y tras
	 * llevarla a cabo, en su siguiente turno, tiene 4 acciones válidas.
	 * 
	 * @throws IOException
	 */
	@Test
	public void test3() throws IOException {
		WolfAndSheepState state0 = new WolfAndSheepState(8);
		assertEquals(state0.validActions(0).size(), 1);
		for (int i = 0; i < 2; i++)
			state0 = (WolfAndSheepState) takeRandomAction(state0);
		assertEquals(state0.validActions(0).size(), 4);
	}

	/**
	 * una oveja en su posición inicial tiene 2 acciones válidas; y si está en
	 * un lateral, tiene 2 acciones válidas.
	 * 
	 * @throws IOException
	 */
	@Test
	public void test4() throws IOException {
		WolfAndSheepState state0 = new WolfAndSheepState(8);
		board = new int[8][8];
		board = state0.getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = -1;
			}
		}
		int num = 2;
		for (int i = 1; i < 8; i += 2) {
			if (i == 7)
				num = 1;
			board[0][i] = 1;
			WolfAndSheepState state2 = new WolfAndSheepState(state0, board,
					false, -1);
			assertEquals(state2.validActions(1).size(), num);
			board[0][i] = -1;
		}

		num = 0;
		for (int i = 0; i < 7; i += 2) {
			board[i][num] = 1;
			WolfAndSheepState state2 = new WolfAndSheepState(state0, board,
					false, -1);
			assertEquals(state2.validActions(1).size(), 1);
			board[i][num] = -1;
		}
		num = 7;
		for (int i = 1; i < 7; i += 2) {
			board[i][num] = 1;
			WolfAndSheepState state2 = new WolfAndSheepState(state0, board,
					false, -1);
			assertEquals(state2.validActions(1).size(), 1);
			board[i][num] = -1;
		}
	}

	/**
	 * Escoge una accion aleatoria.
	 * 
	 * @param state 
	 * @return
	 */
	private static <S extends GameState<S, A>, A extends GameAction<S, A>> S takeRandomAction(
			S state) {
		List<A> actions = state.validActions(state.getTurn());
		return actions.get(new Random().nextInt(actions.size())).applyTo(state);
	}
}
