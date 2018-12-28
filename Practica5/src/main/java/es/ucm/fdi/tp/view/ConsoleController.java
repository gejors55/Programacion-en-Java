package es.ucm.fdi.tp.view;

import java.util.List;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameTable;

public class ConsoleController<S extends GameState<S, A>, A extends GameAction<S, A>> {

	private List<GamePlayer> players;
	private GameTable<S, A> game;

	public ConsoleController(List<GamePlayer> players, GameTable<S, A> game) {
		this.players = players;
		this.game = game;
	}

	public void run() {
		int playerCount = 0;
		for (GamePlayer p : players)
			p.join(playerCount++);
		game.start();
		S currentState = (S) game.getState();
		while (!currentState.isFinished()) {
			A action = players.get(currentState.getTurn()).requestAction(
					currentState);
			// apply move
			currentState = action.applyTo(currentState);
			game.execute(action);

			if (currentState.isFinished())
				game.stop();
			// game over
		}
	}
}
