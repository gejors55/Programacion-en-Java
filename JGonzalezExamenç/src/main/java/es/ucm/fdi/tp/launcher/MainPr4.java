package es.ucm.fdi.tp.launcher;

import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.was.WolfAndSheepState;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * Main
 * 
 * @author Jorge González Soria y Lázaro Clemen Palafox
 *
 */
public class MainPr4 {
	private static Scanner input = new Scanner(System.in);

	/**
	 * Inicializa el juego elegido: ttt (Tres en Raya), was (Wolf and Sheep)..
	 * 
	 * @param gameName
	 *            Nombre del juego.
	 * @return
	 */
	public static GameState<?, ?> createInitialState(String gameName) {
		if (gameName.equalsIgnoreCase("TTT")) {
			return new TttState(3);
		} else if (gameName.equalsIgnoreCase("WAS")) {
			return new WolfAndSheepState(8);
		} else {
			throw new IllegalArgumentException("modo juego '" + gameName
					+ "' no definido.");
		}
	}

	/**
	 * Crea un jugador del tipo indicado: console (console player), rand (random
	 * player), smart (smart player).
	 * 
	 * @param gameName
	 *            Nombre del juego.
	 * @param playerType
	 *            Tipo jugador.
	 * @param playerName
	 *            Nombre jugador.
	 * @return
	 */
	public static GamePlayer createPlayer(String gameName, String playerType,
			String playerName) {
		if (playerType.equalsIgnoreCase("CONSOLE")) {
			return new ConsolePlayer(playerName, input);
		} else if (playerType.equalsIgnoreCase("SMART")) {
			return new SmartPlayer(playerName, 10);
		} else if (playerType.equalsIgnoreCase("RAND")) {
			return new RandomPlayer(playerName);
		} else {
			throw new IllegalArgumentException("jugador '" + playerType
					+ "' no definido.");
		}

	}

	/**
	 * Same as demo main.
	 * 
	 * @param initialState
	 *            Estado inicial.
	 * @param players
	 *            Jugadores.
	 * @return
	 */
	public static <S extends GameState<S, A>, A extends GameAction<S, A>> int playGame(
			GameState<S, A> initialState, List<GamePlayer> players) {
		int playerCount = 0;
		for (GamePlayer p : players) {
			p.join(playerCount++); // welcome each player, and assign
									// playerNumber
		}
		@SuppressWarnings("unchecked")
		S currentState = (S) initialState;

		while (!currentState.isFinished()) {
			// request move
			A action = players.get(currentState.getTurn()).requestAction(
					currentState);
			// apply move
			currentState = action.applyTo(currentState);
			System.out.println("After action:\n" + currentState);

			if (currentState.isFinished()) {
				// game over
				String endText = "The game ended: ";
				int winner = currentState.getWinner();
				if (winner == -1) {
					endText += "draw!";
				} else {
					endText += "player " + (winner + 1) + " ("
							+ players.get(winner).getName() + ") won!";
				}
				System.out.println(endText);
			}
		}
		return currentState.getWinner();
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		GameState<?, ?> game;
		List<GamePlayer> players = new ArrayList<GamePlayer>();
		List<String> playerName = new ArrayList<String>();
		playerName.add("Bob");
		playerName.add("Dylan");
		playerName.add("Tom");
		playerName.add("Hanks");
		// String line = input.nextLine();
		// String[] x = line.split(" ");

		try {
			if (args.length < 3)
				throw new Exception("Error: faltan jugadores para este juego");
			else if (args.length > 3)
				throw new Exception(
						"Error: demasiados jugadores para este juego");
			else {
				players.add(MainPr4.createPlayer(args[0], args[1],
						playerName.get(3)));
				players.add(MainPr4.createPlayer(args[0], args[2],
						playerName.get(1)));
				game = MainPr4.createInitialState(args[0]);

				playGame(game, players);
				// line = input.nextLine();
				// x = line.split(" ");
			}
		} catch (IllegalArgumentException ie) {
			System.out.println("Error: " + ie.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			input.close();
		}
	}
}
