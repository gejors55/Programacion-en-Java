package es.ucm.fdi.tp.launcher;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.atax.AtaxAction;
import es.ucm.fdi.tp.atax.AtaxState;
import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.conecta5.ConectaAction;
import es.ucm.fdi.tp.conecta5.ConectaState;
import es.ucm.fdi.tp.damas.damasAction;
import es.ucm.fdi.tp.damas.damasState;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.nim.nimAction;
import es.ucm.fdi.tp.nim.nimState;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.view.AtaxView;
import es.ucm.fdi.tp.view.ConectaView;
import es.ucm.fdi.tp.view.ConsoleController;
import es.ucm.fdi.tp.view.ConsoleView;
import es.ucm.fdi.tp.view.GUIController;
import es.ucm.fdi.tp.view.GUIView;
import es.ucm.fdi.tp.view.GameContainer;
import es.ucm.fdi.tp.view.GameController;
import es.ucm.fdi.tp.view.TttView;
import es.ucm.fdi.tp.view.WasView;
import es.ucm.fdi.tp.was.WolfAndSheepAction;
import es.ucm.fdi.tp.was.WolfAndSheepState;

/**
 * 
 * @author Jorge Gonzalez Soria y Lazaro Clemen Palafox
 *
 */
public class Main {
	private static Scanner input = new Scanner(System.in);

	private static GameTable<?, ?> createGame(String gType) {
		if (gType.equalsIgnoreCase("WAS")) {
			return new GameTable<WolfAndSheepState, WolfAndSheepAction>(
					new WolfAndSheepState(8));
		} else if (gType.equalsIgnoreCase("TTT")) {
			return new GameTable<TttState, TttAction>(new TttState(3));
		} else if (gType.equalsIgnoreCase("atax")) {
			return new GameTable<AtaxState, AtaxAction>(new AtaxState(7));
		} else if (gType.equalsIgnoreCase("conecta")) {
			return new GameTable<ConectaState, ConectaAction>(new ConectaState(6));
		} else if (gType.equalsIgnoreCase("damas")) {
			return new GameTable<damasState, damasAction>(new damasState(8));
		} else if (gType.equalsIgnoreCase("nim")) {
			return new GameTable<nimState, nimAction>(new nimState(7,5));
		} else {
			return null;
		}
		
	}

	private static <S extends GameState<S, A>, A extends GameAction<S, A>> void startConsoleMode(
			GameTable<S, A> game, String playerModes[]) {
		List<GamePlayer> players = new ArrayList<GamePlayer>();
		for (int i = 0; i < playerModes.length; i++) {
			if (playerModes[i].equalsIgnoreCase("MANUAL")) {
				players.add(new ConsolePlayer(playerModes[i], input));
			} else if (playerModes[i].equalsIgnoreCase("RAND")) {
				players.add(new RandomPlayer(playerModes[i]));
			} else if (playerModes[i].equalsIgnoreCase("SMART")) {
				players.add(new SmartPlayer(playerModes[i], 0));
			} else {
				throw new IllegalArgumentException("Jugador '" + playerModes[i]
						+ "' no definido.");
			}
		}
		new ConsoleView<S, A>(game);
		new ConsoleController<S, A>(players, game).run();
	}

	private static <S extends GameState<S, A>, A extends GameAction<S, A>> GUIView<?, ?> createGameView(
			String gType, GameTable<S, A> game) {
		if (gType.equalsIgnoreCase("WAS"))
			return new WasView((WolfAndSheepState) game.getState());
		else if (gType.equalsIgnoreCase("TTT"))
			return new TttView((TttState) game.getState());
		else if (gType.equalsIgnoreCase("atax"))
			return new AtaxView((AtaxState) game.getState());
		else if (gType.equalsIgnoreCase("conecta"))
			return new ConectaView((ConectaState) game.getState());
		else
			return null;

	}

	private static <S extends GameState<S, A>, A extends GameAction<S, A>> void startGUIMode(
			String gType, GameTable<S, A> game, String[] otherArgs) {
		for (int i = 0; i < game.getState().getPlayerCount(); i++) {
			GamePlayer p1 = new ConsolePlayer("Player1", input);
			p1.join(i);
			GamePlayer rand = new RandomPlayer("Rand");
			GamePlayer smart = new SmartPlayer("Samrt", 10);
			rand.join(i);
			smart.join(i);
			GameController<S, A> gameCtrl = new GUIController<S, A>(
					p1.getPlayerNumber(), rand, smart, game);
			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					@SuppressWarnings("unchecked")
					public void run() {
						GUIView<S, A> guiView = (GUIView<S, A>) createGameView(
								gType, game);
						guiView.setGameController(gameCtrl);
						GUIView<S, A> container = new GameContainer<S, A>(
								guiView, gameCtrl, game);
						container.enableWindowMode();
						container.setTitle((gType.equalsIgnoreCase("WAS") ? "Wolf and Sheep"
								: "Tic Tac Toe")
								+ " (view for player "
								+ p1.getPlayerNumber()
								+ ")");
					}
				});
			} catch (InvocationTargetException | InterruptedException e) {
				System.err
						.println("Some error occurred while creating the view ...");
				System.exit(1);
			}
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				game.start();
			}
		});
	}

	private static void usage() {
		System.out.println("Error, insuficientes argumentos");
	}

	public static void main(String[] args) {
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
		switch (args[1]) {
		case "console":
			startConsoleMode(game, playerModes);
			break;
		case "gui":
			startGUIMode(args[0], game, playerModes);
			break;
		default:
			System.err.println("Invalid view mode: " + args[1]);
			usage();
			System.exit(1);
		}
	}
}