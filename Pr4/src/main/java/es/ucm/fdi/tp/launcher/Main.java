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
 * Demo main, used to test game functionality. You can use it as an inspiration,
 * but we expect you to build your own main-class.
 */
public class Main {
	private static Scanner in = new Scanner(System.in);

	// gameName: ttt (Tres en Raya), was (Wolf and Sheep).
	// player: console (console player), rand (random player), smart (smart
	// player).
	public static GameState<?, ?> createInitialState(String gameName) {
		if (gameName.equalsIgnoreCase("TTT")) {
			return new TttState(3);
		} else if (gameName.equalsIgnoreCase("WAS")) {
			return new WolfAndSheepState(8);
		} else {
			return null;
		}
	}

	public static GamePlayer createPlayer(String gameName, String playerType,
			String playerName) {
		if (playerType.equalsIgnoreCase("CONSOLE")) {
			return new ConsolePlayer(playerName, in); 
			//peta en CONSOLE PLAYER LINEA 63 porque le pasamos este in que esta a null.... HAY QUE PSARLE EL IN DEL MAIN	
		} else if (playerType.equalsIgnoreCase("SMART")) {
			return new SmartPlayer(playerName, 5);
		} else if (playerType.equalsIgnoreCase("RAND")) {
			return new RandomPlayer(playerName);
		} else {
			return null;
		}
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
		System.out.print("> ");
		String line = in.nextLine();
		String[] x = line.split(" "); // ttt console rand
		//try   /// NO CONTROLO LOS EERORES, la cosa es que se puede hacer con try catch y que se vaya a la mierda si hay errores, o como pocdemos hacerlo poniendo, si devuelve null cualquiere de lor dos metodos, pones los errores y fuera..
		game = Main.createInitialState(x[0]);
		players.add(createPlayer(x[0], x[1], playerName.get(0)));
		players.add(createPlayer(x[0], x[2], playerName.get(1)));
		//catch y finally, si hay error que acabe ejecucion, si sigue la ejecucion hace playgame.
		playGame(game, players);
		in.close();
		
	}
	/**
	 * Same as demo main.
	 * 
	 * @param initialState
	 * @param players
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

	
}
