package es.ucm.fdi.tp.view;

import java.util.List;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameObserver;
import es.ucm.fdi.tp.mvc.GameTable;

public class ConsoleController<S extends GameState<S, A>, A extends GameAction<S, A>>
		implements GameController<S, A> {

	private List<GamePlayer> players;
	private GameTable<S, A> game;

	public ConsoleController(List<GamePlayer> players, GameTable<S, A> game) {
		this.players = players;
		this.game = game;
	}

	@Override
	public void run() {
		int playerCount = 0;
		for (GamePlayer p : players)
			p.join(playerCount++);
		game.start();
		// @SuppressWarnings("unchecked")
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

	public void addObservador(GameObserver<S, A> ob) {
		game.addObserver(ob);
	}

	public void removeObservador(GameObserver<S, A> ob) {
		game.removeObserver(ob);
	}

	@Override
	public void makeManualMove(A a) {
	}

	@Override
	public void makeRandomMove() {
	}

	@Override
	public void makeSmartMove() {
	}

	@Override
	public void restratGame() {
	}

	@Override
	public void stopGame() {
	}

	@Override
	public void changePlayerMode(PlayerMode p) {
	}

	@Override
	public void handleEvent(GameEvent<S, A> e) {
	}

	@Override
	public PlayerMode getPlayerMode() {
		return null;
	}

	@Override
	public int getPlayerId() {
		return 0;
	}

	@Override
	public void guarda() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void carga() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saltarTurno() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remvoe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desHacer() {
		// TODO Auto-generated method stub
		
	}

}
