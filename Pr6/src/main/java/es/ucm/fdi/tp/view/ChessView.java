package es.ucm.fdi.tp.view;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.base.Utils;
import es.ucm.fdi.tp.chess.ChessAction;
import es.ucm.fdi.tp.chess.ChessAction.Special;
import es.ucm.fdi.tp.chess.ChessBoard;
import es.ucm.fdi.tp.chess.ChessBoard.Piece;
import es.ucm.fdi.tp.chess.ChessState;
import es.ucm.fdi.tp.was.WolfAndSheepAction;

public class ChessView extends RectBoardGameView<ChessState, ChessAction> {
	//ATRIBUTOS.
	private static final long serialVersionUID = -4518722262994516431L;
	private int numOfCols;
	private int numOfRows; //1
	private boolean click;
	private ArrayList<Image> imagenes = new ArrayList<Image>();
	//CONSTRUCTORA.
	public ChessView(){
		super();
		this.click = false;
		for(int i = 0; i < 15; i++){
			imagenes.add(Utils.loadImage(ChessBoard.Piece.iconName((byte) i)));
		}
	}
	//METODOS.
	@Override
	protected int getNumCols() {
		return state.getDimension();
	}
	//-----------------------------------------------------------
	@Override
	protected int getNumRows() {
		return state.getDimension();
	}
	//-----------------------------------------------------------
	@Override
	protected Integer getPosition(int row, int col) {
		if(state.getEmpty(row, col)) return null;
		else return state.at(row, col);
	}
	//-----------------------------------------------------------
	@Override
	protected void mouseClicked(int row, int col, int clickCount,
			int mouseButton) {
		if (this.enabled) {
			if (!this.click && mouseButton == 1) {
				if (((ChessBoard.black((byte) this.state.at(row, col)) && this.state
								.getTurn() == ChessState.BLACK) || ChessBoard
								.white((byte) this.state.at(row, col))
								&& this.state.getTurn() == ChessState.WHITE)) {
					this.numOfRows = row;
					this.numOfCols = col;
					this.click = true;
					showInfoMessage("Selected ("
									+ row
									+ ", "
									+ col
									+ ")ESC to cancel selection");
				}
			} else if (mouseButton == 1) {
				if (row != this.numOfRows || col != this.numOfCols) {
					List<ChessAction> validActions = state
							.validActions(this.guCtrl.getplayerId());
					List<ChessAction> specialActions = new ArrayList<ChessAction>();
					ChessAction action = new ChessAction(this.guCtrl.getplayerId(),
							this.numOfRows, this.numOfCols, row, col);
					switch (Piece.valueOf((byte) this.state.at(this.numOfRows,
							this.numOfCols))) {
					case King:
						specialActions.add(new ChessAction(this.guCtrl
								.getplayerId(), this.numOfRows, this.numOfCols, row, col,
								Special.LongCastle));
						specialActions.add(new ChessAction(this.guCtrl
								.getplayerId(), this.numOfRows, this.numOfCols, row, col,
								Special.ShortCastle));
						break;
					case Pawn:
						specialActions.add(new ChessAction(this.guCtrl
								.getplayerId(), this.numOfRows, this.numOfCols, row, col,
								Special.EnPassant));
						specialActions.add(new ChessAction(this.guCtrl
								.getplayerId(), this.numOfRows, this.numOfCols, row, col,
								Special.QueenQ));
						break;
					default:
						specialActions = null;
						break;

					}
					boolean found = false;
					
					if (validActions.isEmpty()) {
						showInfoMessage("No actions available");
					} else if (specialActions == null) {
						if (this.state.isValid(action))
						{
							showInfoMessage("Selected (" + row + " "
									+ col + ")");
							guCtrl.makeManualMove(action);
							found = true;
							this.click = false;
						}
					} else {
						for (int j = specialActions.size(); j > 0; j--) {
							if (j > 0 && this.state.isValid(specialActions.get(j - 1))) {
								if (Piece.valueOf((byte) this.state.at(this.numOfRows, this.numOfCols)).equals(Piece.Pawn))
								{
									if (row == 0 || row == 7)
										this.guCtrl.makeManualMove(new ChessAction(
											     this.guCtrl.getplayerId(), this.numOfRows, this.numOfCols, row,
											     col, Special.QueenQ));
									else
										guCtrl.makeManualMove(specialActions.get(j - 1));
								}
								else
									guCtrl.makeManualMove(specialActions
											.get(j - 1));
								this.click= false;
								found = true;
								//break;
								j = 0;
							}
							if (j < specialActions.size())
								//break;
								j = 0;
						}
						if (!found) {
							if (this.state.isValid(action)) {
								guCtrl.makeManualMove(action);
								found = true;
								this.click = false;

							}
						}
						showInfoMessage("Selected (" + row + ", " + col
								+ ")");

					}
					if (!found)
						showInfoMessage("Position not valid (" + row
								+ ", " + col + ")" + "\n Select a new one!");
				}
			}
		}

	}
	//-----------------------------------------------------------
	@Override
	protected void keyTyped(int keyCode) {
		if(click && (keyCode + 27) == KeyEvent.VK_ESCAPE){
			click = false;
			this.showInfoMessage("Has cancelado");
		}
	}
	//-----------------------------------------------------------
	@Override
	protected Image getImage(int player) {
	return this.imagenes.get(player);
	}
	
}
