package es.ucm.fdi.tp.view;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.ConcurrentAiPlayer;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameObserver;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.view.GameWindow.PlayerMode;

public class GameController<S extends GameState<S, A>, A extends GameAction<S, A>> {
	// ATRIBUTOS
	protected GameTable<S, A> modelo;
	private GamePlayer randPlayer;
	private ConcurrentAiPlayer smartPlayer;

	// CONSTRUCTORA.
	public GameController(GameTable<S, A> game) {
		this.modelo = game;
	}

	// METODOS
	public void makeManualMove(A action) {
		this.modelo.execute(action);
	}

	// -------------------------------------------------------
	public void makeRandomMove() {
		randPlayer.join(modelo.getState().getTurn());
		this.modelo.execute(this.randPlayer.requestAction(modelo.getState()));
	}

	// -------------------------------------------------------
	public void makeSmartMove(A action) {
		this.modelo.execute(action);
		System.out.println(SwingUtilities.isEventDispatchThread());
	}

	// -------------------------------------------------------
	public void stopGame() {
		this.modelo.stop();
	}

	// -------------------------------------------------------
	public void restartGame() {
		this.modelo.start();
	}

	// -------------------------------------------------------
	public S getState() {
		return this.modelo.getState();
	}

	// -------------------------------------------------------
	public int getplayerId() {
		return this.modelo.getState().getTurn();
	}

	// -------------------------------------------------------
	public void setRandomPlayer(GamePlayer rand) {
		this.randPlayer = rand;
	}

	// -------------------------------------------------------
	public void setSmartPlayer(ConcurrentAiPlayer smart) {
		this.smartPlayer = smart;
	}
}