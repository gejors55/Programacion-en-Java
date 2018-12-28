package es.ucm.fdi.tp.view;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import es.ucm.fdi.tp.was.WolfAndSheepAction;
//import es.ucm.fdi.tp.extra.jboard.BoardExample;
import es.ucm.fdi.tp.was.WolfAndSheepState;

public class WasView extends RectBoardGameView<WolfAndSheepState, WolfAndSheepAction> {
	//ATRIBUTOS.
	private static final long serialVersionUID = -4518722262994516431L;
	private int numOfCols;
	private int numOfRows; //1
	private boolean click;
	//CONSTRUCTORA.
	public WasView(){
		super();
		this.click = false;
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
		if (this.enabled) {
			if (!this.state.isFinished()) {
				if (!click) {
					if(this.state.at(row, col) == this.state.getTurn()){
					this.numOfRows = row;
					this.numOfCols = col;
					this.click = true;
					this.showInfoMessage("Mouse: " + clickCount
							+ "clicks at position (" + row + "," + col
							+ ") with Button " + mouseButton);
					}
				} else {
				ArrayList<WolfAndSheepAction> accionesValidas = (ArrayList<WolfAndSheepAction>) this.state.validActions(this.guCtrl.getplayerId());
				WolfAndSheepAction action = new WolfAndSheepAction(this.state.getTurn(), row, col, this.numOfRows,this.numOfCols);

				for (WolfAndSheepAction act : accionesValidas) {
					if (act.getCol() == col && act.getRow() == row && 
							act.getOldCol() == this.numOfCols&& 
							act.getOldRow() == this.numOfRows) {
							this.gvCtrl.userActionAvailable(action);
							this.click = false;
						}
					}
				}
			}
		}
	}
	//----------------------------------------------------
	@Override
	protected void keyTyped(int keyCode) {
		if(click && (keyCode + 27) == KeyEvent.VK_ESCAPE){
			click = false;
			this.showInfoMessage("Has cancelado");
		}
	}
	@Override
	protected Image getImage(int player) {
		// TODO Auto-generated method stub
		return null;
	}
}
