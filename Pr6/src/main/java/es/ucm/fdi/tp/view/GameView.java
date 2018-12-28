package es.ucm.fdi.tp.view;


import javax.swing.JPanel;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;

public abstract class GameView <S extends GameState<S,A>,A extends GameAction<S,A>> extends JPanel{
	//CONSTRUCTORA.
	public GameView(){
		super();
	}
	//METODOS.
	private static final long serialVersionUID =  -4518722262994516431L;
	public abstract void enable();
	public abstract void disable();
	public abstract void update( S state);
	public abstract void showInfoMessage(String msg);
	public abstract void setController(GameController<S, A> gameCtrl);
	public abstract void setGameViewCtrl(GameWindow<S,A> gvCtrl);
}
