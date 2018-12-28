package es.ucm.fdi.tp.view;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent;

public interface GameController<S extends GameState<S, A>, A extends GameAction<S, A>>
		extends Runnable {
	public enum PlayerMode {
		MANUAL, RANDOM, SMART
	}

	public void makeManualMove(A a);

	public void makeRandomMove();

	public void makeSmartMove();

	public void restratGame();

	public void stopGame();

	public void changePlayerMode(PlayerMode p);

	public void handleEvent(GameEvent<S, A> e);

	public PlayerMode getPlayerMode();

	public int getPlayerId();

	public void guarda();

	public void carga();
	public void saltarTurno();
	public void remvoe();
	public void desHacer();
}
