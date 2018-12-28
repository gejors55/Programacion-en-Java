package es.ucm.fdi.tp.view;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameEvent.EventType;
import es.ucm.fdi.tp.mvc.GameObservable;
import es.ucm.fdi.tp.mvc.GameObserver;

public class ConsoleView<S extends GameState<S, A>, A extends GameAction<S, A>>
		implements GameObserver<S, A> {
	//CONSTRUCTORA.
	public ConsoleView(GameObservable<S, A> gameTable) {
		gameTable.addObserver(this);
	}
	//METODOS.
	@Override
	public void notifyEvent(GameEvent<S, A> e) {
		System.out.println(e);
		if(e.getType().equals(EventType.Start) || e.getType().equals(EventType.Change))System.out.println(e.getState());
	}
}

