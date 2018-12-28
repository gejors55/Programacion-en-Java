package es.ucm.fdi.tp.view;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;

public abstract class MessageViewer<S extends GameState<S, A>, A extends GameAction<S, A>>
		extends GUIView<S, A> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2152520803715714183L;

	public void setMessageViewer(MessageViewer<S, A> infoViewer) {
	}

	abstract public void addContent(String msg);

	abstract public void setContent(String msg);

}
