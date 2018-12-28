package es.ucm.fdi.tp.view;

//import javax.swing.JButton;

import java.awt.Image;
import java.awt.event.KeyEvent;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;

public class TttView extends RectBoardGameView<TttState ,TttAction> {
	//ATRIBUTOS.
	private static final long serialVersionUID =  -4518722262994516431L;
	private int numOfCols;
	private int numOfRows;
	private boolean firstClickReceived;
	//CONSTRUCTORA.
	public TttView(){
		super();
	}
	//METODOS.
	@Override
	protected int getNumRows() {
		return state.getDim();
	}
	//----------------------------------------------------
	@Override
	protected int getNumCols() {
		return state.getDim();
	}
	//----------------------------------------------------
	@Override
	protected Integer getPosition(int row, int col) {
		if(state.getEmpty(row, col)) return null;
		else return state.at(row, col);
	}
	//----------------------------------------------------
	@Override
	protected void mouseClicked(int row, int col, int clickCount, int mouseButton) {
		if(this.enabled){
		if (!this.state.isFinished()) {
			TttAction action = new TttAction(this.state.getTurn(), row, col);
			if (this.state.isValid(action))
				this.gvCtrl.userActionAvailable(action);

		}
		this.showInfoMessage("Mouse: " + clickCount
				+ "clicks at position (" + row + "," + col
				+ ") with Button " + mouseButton);
		}
	}
	//----------------------------------------------------
	@Override
	protected void keyTyped(int keyCode) {
		if(firstClickReceived && keyCode == KeyEvent.VK_ESCAPE){
			firstClickReceived = false;
		}
	}
	@Override
	protected Image getImage(int player) {
		// TODO Auto-generated method stub
		return null;
	}
}
//mirar lo del scape.