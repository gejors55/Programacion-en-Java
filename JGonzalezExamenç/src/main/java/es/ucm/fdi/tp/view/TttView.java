package es.ucm.fdi.tp.view;

import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JOptionPane;

import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.view.GameController.PlayerMode;

public class TttView extends RectBoardView<TttState, TttAction> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4440563216188180306L;

	private int numOfRows;
	private int numOfCols;

	public TttView(TttState state) {
		super(state);
		this.numOfCols = 3;
		this.numOfRows = 3;
	}

	@Override
	public void enable() {
		super.enable();
		infoViewer.addContent("Click on a square.");
	}

	@Override
	protected int getNumCols() {
		return this.numOfCols;
	}

	@Override
	protected int getNumRows() {
		return this.numOfRows;
	}

	@Override
	protected Integer getPosition(int row, int col) {
		if (this.state.at(row, col) != -1)
			return state.at(row, col);
		else
			return null;
	}

	@Override
	protected void mouseClicked(int row, int col, int clickCount,
			int mouseButton) {
		if (enable && gameCtrl.getPlayerMode().equals(PlayerMode.MANUAL)) {
			if (((TttState) state).getBoard()[row][col] == -1) {
				List<TttAction> valids = null;
				TttAction action = new TttAction(state.getTurn(), row, col);
				valids = state.validActions(state.getTurn());
				if (valids.isEmpty()) {
					infoViewer.addContent("Casilla no valida");
				} else {
					boolean found = false;
					for (TttAction actions : valids) {
						if (actions.getCol() == action.getCol()
								&& actions.getRow() == action.getRow()) {// actions.equals(action))
							infoViewer.addContent("Selected (" + row + ", "
									+ col + ")");
							gameCtrl.makeManualMove(action);
							found = true;
							break;
						}
					}
					if (!found)
						infoViewer.addContent("Position not valid (" + row
								+ ", " + col + ")" + "\n Select a new one!");
				}
			}
		}
	}

	@Override
	protected void keyTyped(int keyCode) {
		String[] x = { "Yes", "No" };
		if ((keyCode + 27) == KeyEvent.VK_ESCAPE) {
			if (this.gameCtrl.getPlayerId() == this.state.getTurn()) {
				if (JOptionPane.showOptionDialog(getRootPane(), "Exit",
						"Do you want to exit?", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, x, "Accept") == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		}
	}
	@Override
	protected int getSepPixels() {
		return 2;
	}
}
