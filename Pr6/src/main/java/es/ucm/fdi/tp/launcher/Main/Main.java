package es.ucm.fdi.tp.launcher.Main;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameError;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.ConcurrentAiPlayer;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.chess.ChessAction;
import es.ucm.fdi.tp.chess.ChessState;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.view.ChessView;
import es.ucm.fdi.tp.view.ConsoleController;
import es.ucm.fdi.tp.view.ConsoleView;
import es.ucm.fdi.tp.view.GameController;
import es.ucm.fdi.tp.view.GameView;
import es.ucm.fdi.tp.view.GameWindow;
import es.ucm.fdi.tp.view.TttView;
import es.ucm.fdi.tp.view.WasView;
import es.ucm.fdi.tp.was.WolfAndSheepAction;
import es.ucm.fdi.tp.was.WolfAndSheepState;

public class Main {
	private static Scanner in = new Scanner(System.in);

	@SuppressWarnings("unused")
	private static GameTable<?, ?> createGame(String gType) {
		if (gType.equalsIgnoreCase("ttt")) {
			System.out.println("Set size ");
			return new GameTable<TttState, TttAction>(
					new TttState(in.nextInt()));
		} else if (gType.equalsIgnoreCase("was")) {
			return new GameTable<WolfAndSheepState, WolfAndSheepAction>(
					new WolfAndSheepState(8));
		}else if(gType.equalsIgnoreCase("chess")){
			return new GameTable<ChessState, ChessAction>(
					new ChessState());
		}
		else
			throw new GameError("Expected game to be ttt or was");
	}

	// ---------------------------------------------------
	private static GamePlayer createPlayer(String gameName, String playerType,
			String playerName) {
		GamePlayer player = null;
		if (playerType.equalsIgnoreCase("Manual"))
			player = new ConsolePlayer(playerName, in);
		else if (playerType.equalsIgnoreCase("Smart"))
			player = new SmartPlayer(playerName, 5);
		else if (playerType.equalsIgnoreCase("Rand"))
			player = new RandomPlayer(playerName);
		else
			throw new GameError("Type of player not exist");
		return player;
	}

	// ---------------------------------------------------
	@SuppressWarnings("unused")
	private static <S extends GameState<S, A>, A extends GameAction<S, A>> void startConsoleMode(
			String gType, GameTable<S, A> game, String playerModes[]) {

		List<GamePlayer> players = new ArrayList<GamePlayer>();

		players.add(createPlayer(gType, playerModes[0], "jugador1"));
		players.add(createPlayer(gType, playerModes[1], "jugador2"));

		new ConsoleView<S, A>(game);
		new ConsoleController<S, A>(players, game).run();
	}
	// ---------------------------------------------------
	@SuppressWarnings("unchecked")
	private static <S extends GameState<S, A>, A extends GameAction<S, A>> void startGuiMode(
			String gType, final GameTable<S, A> game) {
		GameController<S, A> controlador = new GameController<S, A>(game);
		for (int i = 0; i < game.getState().getPlayerCount(); i++) {
			int playerId = i;
			//SmartPlayer smart = new SmartPlayer(gType, 5);
			RandomPlayer rand = new RandomPlayer(gType);
			ConcurrentAiPlayer aiPlayer = new ConcurrentAiPlayer(gType);

			GameView<S, A> vista ;
			if (gType.equalsIgnoreCase("ttt"))
				vista = (GameView<S, A>) new TttView();
			else if (gType.equalsIgnoreCase("was"))
				vista = (GameView<S, A>) new WasView();
			else if (gType.equalsIgnoreCase("chess"))
				vista = (GameView<S, A>) new ChessView();
			else vista = null;
			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					@SuppressWarnings("unused")
					@Override
					public void run() {
						GameWindow<S, A> window = new GameWindow<S, A>(
								playerId, rand, aiPlayer, vista, controlador);
					}
				});
			} catch (InvocationTargetException | InterruptedException e) {
				System.err.println("ERROR: Ha ocurrido un error mientras se creaba la vista");
				System.exit(1);
			}
		}
		try {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					game.start();
				}
			});
		} catch (Exception e) {
			System.err
					.println("ERROR: Ha ocurrido un error mientras se creaba la vista");
			System.exit(1);
		}
	}
	// ---------------------------------------------------
	private static void usage() {
		System.out.println("Bienvenido al Main de la Practica 5. Funciona "
				+ "de la siguiente manera: Se inserta un juego(was, chess o ttt), un "
				+ "modo(console o gui) y sus dos jugadores con su tipo(random"
				+ ", manual o smart).");
	}

	// ---------------------------------------------------
	public static void main(String... args) {
		try {
			if (args.length < 2) {
				usage();
				System.exit(1);
			}
			GameTable<?, ?> game = createGame(args[0]);
			if (game == null) {
				System.err.println("Invalid game");
				System.exit(1);
			}
			String[] playerModes = Arrays.copyOfRange(args, 2, args.length);
			if (args[1].equalsIgnoreCase("console"))
				startConsoleMode(args[0], game, playerModes);
			else if (args[1].equalsIgnoreCase("gui"))
				startGuiMode(args[0], game);
			else {
				System.err.println("Invalid view mode: " + args[1]);
				usage();
				System.exit(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		//System.out.println(Runtime.getRuntime().availableProcessors());
	}
}
