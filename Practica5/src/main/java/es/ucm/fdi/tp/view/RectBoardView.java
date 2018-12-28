package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComponent;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.extra.jboard.JBoard;
import es.ucm.fdi.tp.extra.jboard.JBoard.Shape;
import es.ucm.fdi.tp.view.PlayersInfoViewer.PlayersInfoObserver;

public abstract class RectBoardView<S extends GameState<S, A>, A extends GameAction<S, A>>
		extends GUIView<S, A> implements PlayersInfoObserver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7491587041138300676L;

	protected GameController<S, A> gameCtrl;
	protected MessageViewer<S, A> infoViewer;
	protected PlayersInfoViewer<S, A> playersInfoViewer;
	protected S state;
	private JComponent jboard;
	protected boolean enable;

	public RectBoardView(S state) {
		this.state = state;
		this.enable = true;
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());

		jboard = new JBoard() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6628277505797336403L;

			@Override
			protected void keyTyped(int keyCode) {
				RectBoardView.this.keyTyped(keyCode);
			}

			@Override
			protected void mouseClicked(int row, int col, int clickCount,
					int mouseButton) {
				RectBoardView.this.mouseClicked(row, col, clickCount,
						mouseButton);
			}

			@Override
			protected Shape getShape(int player) {
				return RectBoardView.this.getShape(player);
			}

			@Override
			protected Color getColor(int player) {
				return RectBoardView.this.getPlayerColor(player);
			}

			@Override
			protected Integer getPosition(int row, int col) {
				return RectBoardView.this.getPosition(row, col);
			}

			@Override
			protected Color getBackground(int row, int col) {
				return RectBoardView.this.getBackground(row, col);
			}

			@Override
			protected int getNumRows() {
				return RectBoardView.this.getNumRows();
			}

			@Override
			protected int getNumCols() {
				return RectBoardView.this.getNumCols();
			}
		};
		this.setVisible(true);
		this.add(jboard, BorderLayout.CENTER);
		this.setOpaque(true);
	}

	@Override
	public void enable() {
		this.enable = true;
	}

	@Override
	public void disable() {
		this.enable = false;
	}

	@Override
	public void update(S state) {

		this.state = state;
		this.jboard.repaint();
	}

	@Override
	public void setMessageViewer(MessageViewer<S, A> infoViewer) {
		this.infoViewer = infoViewer;
	}

	@Override
	public void setPlayersInfoViewer(PlayersInfoViewer<S, A> playersInfoViewer) {
		this.playersInfoViewer = playersInfoViewer;
		this.playersInfoViewer.setNumberOfPlayer(this.state.getPlayerCount());
		this.playersInfoViewer.addObserver(this);
	}

	@Override
	public void setGameController(GameController<S, A> gameCtrl) {
		this.gameCtrl = gameCtrl;
	}

	@Override
	public void colorChanged(int p, Color color) {
		getPlayerColor(p);
		this.jboard.repaint();
	}

	protected abstract int getNumCols();

	protected abstract int getNumRows();

	protected abstract Integer getPosition(int row, int col);

	protected abstract void mouseClicked(int row, int col, int clickCount,
			int mouseButton);

	protected abstract void keyTyped(int keyCode);

	protected Shape getShape(int player) {
		return Shape.CIRCLE;
	}

	protected Color getBackground(int row, int col) {
		return Color.LIGHT_GRAY;
	}

	protected int getSepPixels() {
		return 2;
	}

	protected Color getPlayerColor(int id) {
		if (id >= 0)
			return playersInfoViewer.getPlayerColor(id);
		else
			return id == 0 ? Color.BLACK : Color.CYAN;
	}

	public S getState() {
		return this.state;
	}

}
