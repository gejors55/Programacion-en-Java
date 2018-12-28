package es.ucm.fdi.tp.view;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameObservable;
import es.ucm.fdi.tp.mvc.GameObserver;

public class ConsoleView<S extends GameState<S, A>, A extends GameAction<S, A>>
		implements GameObserver<S, A> {

	public ConsoleView(GameObservable<S, A> gameTable) {
		gameTable.addObserver(this);
	}

	@Override
	public void notifyEvent(GameEvent<S, A> e) {
		switch (e.getType()) {
		case Start:

			break;
		case Stop:
			String endText = "The game ended: ";
			int winner = e.getState().getWinner();
			if (winner == -1) {
				endText += "draw!";
			} else {
				endText += "player " + (winner + 1) + " ("
						+ e.getState().getWinner() + ") won!";
			}
			System.out.println(endText);

			break;
		case Change:
			System.out.println("After action:\n" + e.getState());
			break;
		case Error:
			System.out.println(e.getError());
			break;
		case Info:
			System.out.println(e);
			break;
		default:
			break;
		}
	}
}