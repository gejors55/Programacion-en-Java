package es.ucm.fdi.tp.launcher.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameError;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.was.WolfAndSheepState;
public class MainPr4 {
	private static Scanner in = new Scanner (System.in); 
	public static <S extends GameState<S, A>, A extends GameAction<S, A>> int playGame(GameState<S, A> initialState,
			List<GamePlayer> players) {
		int playerCount = 0;
		for (GamePlayer p : players) {
			p.join(playerCount++); // welcome each player, and assign
									// playerNumber
		}
		@SuppressWarnings("unchecked")
		S currentState = (S) initialState;
			while (!currentState.isFinished()) {
			// request move
			A action = players.get(currentState.getTurn()).requestAction(currentState);
				// apply move
			currentState = action.applyTo(currentState);
			System.out.println("After action:\n" + currentState);
			if (currentState.isFinished()) {					// game over
				String endText = "The game ended: ";
				int winner = currentState.getWinner();
					if (winner == -1) {
						endText += "draw!";
					} else {
						endText += "player " + (winner + 1) + " (" + players.get(winner).getName() + ") won!";
					}
					System.out.println(endText);
				}
			}
			return currentState.getWinner();
		}
		/**
		 * 
		 */
		public static GameState<?,?> createInitialState(String gameName){
			if(gameName.equalsIgnoreCase("ttt")){
				return new TttState(3);
			}
			else if(gameName.equalsIgnoreCase("was")){
				return  new WolfAndSheepState(8);
			}
			else throw new GameError("Expected game to be ttt or was");
		}
		/**
		 * 
		 */
		
		public static GamePlayer createPlayer(String gameName,String playerType, String playerName){
			GamePlayer player = null;
			if(playerType.equalsIgnoreCase("Console")) player = new ConsolePlayer(playerName, in);
			else if (playerType.equalsIgnoreCase("Smart"))player = new SmartPlayer(playerName, 5);
			else if (playerType.equalsIgnoreCase("Rand"))player = new RandomPlayer(playerName);
			else throw new GameError("Type of player not exist");
			return player;
		}
		/**
		 * Main method.
		 * 
		 * @param args
		 */
		public static void main(String... args) {
			try{
				if(args.length == 3){
					List<GamePlayer> players = new ArrayList<GamePlayer>();
					GameState<?, ?> game = createInitialState(args[0]);
					players.add(createPlayer(args[0], args[1], "jugador1"));
					players.add(createPlayer(args[0], args[2], "jugador2"));
					playGame(game, players);
				}else throw new Exception("The number of arguments is incorrect.");
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
}
