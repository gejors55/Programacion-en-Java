package es.ucm.fdi.tp.view;

import java.awt.Color;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;

public abstract class PlayersInfoViewer<S extends GameState<S, A>, A extends GameAction<S, A>>
		extends GUIView<S, A> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8526944675333099720L;

	protected List<PlayersInfoObserver> observers;

	public void setPlayersInfoViewer(PlayersInfoViewer<S, A> playersInfoViewer) {
	}

	abstract public void setNumberOfPlayer(int n);

	abstract public Color getPlayerColor(int p);

	public interface PlayersInfoObserver {
		public void colorChanged(int p, Color color);
	}

	public void addObserver(PlayersInfoObserver o) {
		observers.add(o);
	}

	public void removeObserver(PlayersInfoObserver o) {
		observers.remove(o);
	}

	protected void notifyObservers(int p, Color c) {
		for (PlayersInfoObserver o : observers) {
			o.colorChanged(p, c);
		}
	}
}
