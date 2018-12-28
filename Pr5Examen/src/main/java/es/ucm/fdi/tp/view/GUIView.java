package es.ucm.fdi.tp.view;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class GUIView<S extends GameState<S, A>, A extends GameAction<S, A>>
		extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4903353897128556609L;

	protected JFrame window;

	public void enableWindowMode() {
		if (!(window != null)) {
			this.window = new JFrame();
		}
		this.window.setContentPane(this);
		this.window.setResizable(true);
		this.window.setEnabled(true);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void disableWindowMode() {
		if (window != null) {
			window.dispose();
			window = null;
		}
	}

	public JFrame getWindow() {
		return window;
	}

	public void setTitle(String title) {
		if (window != null) {
			window.setTitle(title);
		} else {
			this.setBorder(BorderFactory.createTitledBorder(title));
		}
	}

	public abstract void enable();

	public abstract void disable();

	public abstract void update(S state);

	public abstract void setMessageViewer(MessageViewer<S, A> infoViewer);

	public abstract void setPlayersInfoViewer(
			PlayersInfoViewer<S, A> playersInfoViewer);

	public abstract void setGameController(GameController<S, A> gameCtrl);

}
