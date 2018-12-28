package es.ucm.fdi.tp.mvc;

import java.util.ArrayList;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameError;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameEvent.EventType;

/**
 * An event-driven game engine. Keeps a list of players and a state, and
 * notifies observers of any changes to the game.
 */
public class GameTable<S extends GameState<S, A>, A extends GameAction<S, A>>
		implements GameObservable<S, A> {

	private S initState;
	private S actualState;
	private boolean activo;
	private ArrayList<GameObserver<S, A>> obser;

	public GameTable(S initState) {
		this.actualState = initState;
		this.initState = initState;
		this.activo = false;
		this.obser = new ArrayList<GameObserver<S, A>>();
	}

	public void start() {
		this.actualState = this.initState;
		this.activo = true;
		GameEvent<S, A> eventStart = new GameEvent<S, A>(EventType.Start, null,
				this.actualState, null, "Game has started");
		notifyObservers(eventStart);
	}

	public void stop() {
		if (this.activo) {
			this.activo = false;
			GameError error = new GameError("Error stop");
			GameEvent<S, A> eventStop = new GameEvent<S, A>(EventType.Stop,
					null, this.actualState, error, "Stop");
			notifyObservers(eventStop);
			// throw error;
			if (this.actualState.getWinner() == -1) {
				GameEvent<S, A> drawEvent = new GameEvent<S, A>(EventType.Info,
						null, this.actualState, null, "Draw");
				notifyObservers(drawEvent);
			} else {
				GameEvent<S, A> winEvent = new GameEvent<S, A>(EventType.Info,
						null, this.actualState, null, "player "
								+ this.actualState.getWinner() + " won!");
				notifyObservers(winEvent);
			}
		}
	}

	public void execute(A action) {
		S nuevoState = action.applyTo(this.actualState);
		if (!nuevoState.equals(this.actualState) && this.activo) {
			this.actualState = nuevoState;
			GameEvent<S, A> executeEvent = new GameEvent<S, A>(
					EventType.Change, action, this.actualState, null, "Change");
			notifyObservers(executeEvent);
		} else {
			GameError error = new GameError("Error execute");
			GameEvent<S, A> errorEvent = new GameEvent<S, A>(EventType.Error,
					action, this.actualState, error, "Execute");
			notifyObservers(errorEvent);
			throw error;
		}
	}

	public S getState() {
		return this.actualState;
	}

	public void addObserver(GameObserver<S, A> o) {
		this.obser.add(o);
	}

	public void removeObserver(GameObserver<S, A> o) {
		this.obser.remove(o);
	}

	private void notifyObservers(GameEvent<S, A> e) {
		for (GameObserver<S, A> O : obser) {
			O.notifyEvent(e);
		}
	}

	public boolean isActive() {
		return this.activo;
	}
}
