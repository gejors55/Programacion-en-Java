package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameObservable;
import es.ucm.fdi.tp.mvc.GameObserver;
import es.ucm.fdi.tp.view.GameController.PlayerMode;

public class GameContainer<S extends GameState<S, A>, A extends GameAction<S, A>>
		extends GUIView<S, A> implements GameObserver<S, A> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GUIView<S, A> gameView;
	private MessageViewer<S, A> messageViewer;
	private PlayersInfoViewer<S, A> playersInfoViewer;
	private ControlPanel<S, A> controlPanel;
	private GameController<S, A> gameCtrl;

	public GameContainer(GUIView<S, A> gameView, GameController<S, A> gameCtrl,
			GameObservable<S, A> game) {
		this.gameView = gameView;
		this.gameCtrl = gameCtrl;
		initGUI();
		game.addObserver(this);
	}

	private void initGUI() {
		messageViewer = new MessageViewerComp<S, A>();
		playersInfoViewer = new PlayersInfoComp<S, A>();
		controlPanel = new ControlPanel<S, A>(gameCtrl);

		controlPanel.setMessageViewer(messageViewer);

		playersInfoViewer.setMessageViewer(messageViewer);

		gameView.setMessageViewer(messageViewer);
		gameView.setPlayersInfoViewer(playersInfoViewer);

		this.setLayout(new BorderLayout());
		this.add(gameView, BorderLayout.CENTER);
		this.add(controlPanel, BorderLayout.PAGE_START);
		JPanel right = new JPanel();
		right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
		right.add(messageViewer);
		right.add(playersInfoViewer);
		right.setVisible(true);
		this.add(right, BorderLayout.LINE_END);
		this.setVisible(true);

	}

	@Override
	public void notifyEvent(GameEvent<S, A> e) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				handleEvent(e);
			}
		});
	}

	public void handleEvent(GameEvent<S, A> e) {
		if (!e.getState().isFinished()) {
			switch (e.getType()) {
			case Start:
				this.messageViewer.setContent("Game has started.");
				if (!this.gameCtrl.getPlayerMode().equals(PlayerMode.MANUAL)) {
					this.gameCtrl.changePlayerMode(PlayerMode.MANUAL);
					this.controlPanel.resetControlPanel();
				}
				if (e.getState().getTurn() == this.gameCtrl.getPlayerId()
						&& this.gameCtrl.getPlayerMode().equals(
								PlayerMode.MANUAL)) {
					if (this.window != null)
						this.window.setBounds(100, 100, 600, 500);
					this.messageViewer.addContent("Your turn.");
					this.enable();
				} else {
					if (this.window != null)
						this.window.setBounds(700, 100, 600, 500);
					this.disable();
				}
				update(e.getState());
				break;
			case Change:
				if (e.getState().getTurn() == this.gameCtrl.getPlayerId()
						&& this.gameCtrl.getPlayerMode().equals(
								PlayerMode.MANUAL)) {
					this.messageViewer.addContent("Your turn.");
					this.enable();
				} else {
					this.messageViewer.addContent("Turn for player "
							+ e.getState().getTurn() + ".");
					this.disable();
				}
				update(e.getState());
				break;
			case Error:
				e.getError();
				break;
			case Stop:
				this.messageViewer.addContent(e.toString());
				if (e.getState().getTurn() == this.gameCtrl.getPlayerId())
					if (this.quitButtonPressed()) {
						this.disableWindowMode();
						System.exit(0);
					} else {
						disable();
						this.controlPanel.enableEnd();
					}
				break;
			case Info:
				this.messageViewer.addContent(e.toString());
				break;
			default:
				break;
			}
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					gameCtrl.handleEvent(e);
				}
			});
		} else {
			update(e.getState());
			this.controlPanel.enableEnd();
			this.machFinisehd(e.getState().getWinner());
		}

	}

	public void enable() {
		this.gameView.enable();
		this.controlPanel.enable();
		this.playersInfoViewer.enable();
	}

	public void disable() {
		this.gameView.disable();
		this.controlPanel.disable();
		this.playersInfoViewer.disable();
	}

	public void update(S state) {
		this.gameView.update(state);
		this.controlPanel.update(state);
		this.messageViewer.update(state);
		this.playersInfoViewer.update(state);
	}

	@Override
	public void setMessageViewer(MessageViewer<S, A> infoViewer) {
		this.messageViewer = infoViewer;

	}

	@Override
	public void setPlayersInfoViewer(PlayersInfoViewer<S, A> playersInfoViewer) {
		this.playersInfoViewer = playersInfoViewer;
	}

	@Override
	public void setGameController(GameController<S, A> gameCtrl) {
		this.gameCtrl = gameCtrl;
	}

	private boolean quitButtonPressed() {
		String[] x = { "Accept", "Cancel" };
		if (JOptionPane.showOptionDialog(getRootPane(),
				"Do you want to close the game?", "Close game window.",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				x, "Accept") == JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}

	protected void machFinisehd(int winner) {
		String[] x = { "Accept", "Play again", "Exit" };

		if (winner == this.gameCtrl.getPlayerId()) {
			this.messageViewer.addContent("You Won!");
			int op = JOptionPane.showOptionDialog(getRootPane(), "You Won!",
					"Game has finish.", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, x, "Accept");
			if (op == JOptionPane.CANCEL_OPTION) {
				this.disableWindowMode();
				System.exit(0);
			} else if (op == JOptionPane.NO_OPTION) {
				this.gameCtrl.restratGame();
			}
		} else if (winner == -1) {
			this.messageViewer.addContent("Draw");
			int draw = JOptionPane.showOptionDialog(getRootPane(), "Draw",
					"Game has finish.", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, x, "Accept");
			if (draw == JOptionPane.CANCEL_OPTION) {
				this.disableWindowMode();
				System.exit(0);
			} else if (draw == JOptionPane.NO_OPTION)
				this.gameCtrl.restratGame();

		} else
			this.messageViewer.addContent("You Lost...");
	}
}
