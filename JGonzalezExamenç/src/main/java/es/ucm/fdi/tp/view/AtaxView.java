package es.ucm.fdi.tp.view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;

import es.ucm.fdi.tp.atax.AtaxAction;
import es.ucm.fdi.tp.atax.AtaxState;
import es.ucm.fdi.tp.view.GameController.PlayerMode;


public class AtaxView  extends RectBoardView<AtaxState, AtaxAction> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numOfRows;
	private int numOfCols;
	private boolean firstClick;
	private int rowO;
	private int colO;

	public AtaxView(AtaxState state) {
		super(state);
		this.firstClick = false;
		this.numOfCols = state.getDim();
		this.numOfRows = state.getDim();
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
			if (!this.firstClick && mouseButton == 1) {
				if (this.state.getBoard()[row][col] == state.getTurn()) {
					this.rowO = row;
					this.colO = col;
					this.firstClick = true;
					infoViewer
							.addContent("Selected ("
									+ row
									+ ", "
									+ col
									+ ") Click on destination cell or ESC to cancel selection");
				}
			} else if (mouseButton == 1) {
				if (row != this.rowO || col != this.colO) {
					;
					List<AtaxAction> validActions = state
							.validActions(this.gameCtrl.getPlayerId());
					AtaxAction action = new AtaxAction(
							this.gameCtrl.getPlayerId(), row, col, this.rowO,
							this.colO);
					if (validActions.isEmpty()) {
						infoViewer.addContent("No actions available");
					} else {
						boolean found = false;
						for (AtaxAction actions : validActions) {
							if (actions.getCol() == action.getCol()
									&& actions.getRow() == action.getRow()
									&& actions.getColO() == action.getColO()
									&& actions.getRowO() == action.getRowO()) {// actions.equals(action))
								infoViewer.addContent("Selected (" + row + ", "
										+ col + ")");
								gameCtrl.makeManualMove(action);
								found = true;
								this.firstClick = false;
								break;

							}
						}
						if (!found)
							infoViewer
									.addContent("Position not valid (" + row
											+ ", " + col + ")"
											+ "\n Select a new one!");

					}
				}
			}
		}
	}
	@Override
	protected void keyTyped(int keyCode) {
		if (this.firstClick && (keyCode + 27) == KeyEvent.VK_ESCAPE) {
			this.firstClick = false;
			infoViewer.addContent("Selection has been cancel");

		}
	}
	protected Color getBackground(int row, int col) {
		return (row + col) % 2 == 0 ? Color.LIGHT_GRAY : Color.BLACK;
	}
	@Override
	protected int getSepPixels() {
		return 1;
	}

}
