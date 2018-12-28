package es.ucm.fdi.tp.view;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.view.GameWindow.PlayerMode;

public interface GameViewCtrl<S extends GameState<S,A>,A extends GameAction <S,A>>{
	//para que sirve????
	//METODOS.
	public void userActionAvailable (A p);
	public void randomActionButtonPressed();
	public void smartActionButtonPressed();
	public void restartButtonPressed();
	public void quitButtonPressed();
	public void playerModeSelected(GameWindow.PlayerMode playerMode);
}
