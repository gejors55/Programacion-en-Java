package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

import es.ucm.fdi.tp.base.Utils;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.extra.jboard.JBoard;
import es.ucm.fdi.tp.extra.jboard.JBoard.Shape;
import es.ucm.fdi.tp.extra.jcolor.ColorChooser;
import es.ucm.fdi.tp.extra.jcolor.MyTableModel;
import es.ucm.fdi.tp.was.WolfAndSheepState;

public abstract class RectBoardGameView<S extends GameState<S, A>, A extends
GameAction<S, A>> extends GameView<S, A>{
	//ATRIBUTOS.
	private static final long serialVersionUID = 1L;
	protected JBoard jboard; //El tablero.
	protected GameController<S,A> guCtrl; //El controlador. NO SE SI ES NECERSARIO.
	protected GameViewCtrl<S,A> gvCtrl; //??????????????????
	protected S state; //El stado.
	protected boolean enabled; //Si esta disponible la vista.
	protected JTextArea area; //El area de lectura de las notificaciones.
	protected ColorChooser colorC; //Para el color.
	protected Map<Integer, Color> colores; //Mapa de colores.
	Iterator<Color> iteradorColores; //Iterador para recorrer el mapa.
	//METODOS ABSTRACTOS.
	protected abstract int getNumCols();
	protected abstract int getNumRows();
	protected abstract Integer getPosition(int row, int col);
	protected abstract void mouseClicked(int row, int col, int clickCount, int mouseButton);
	protected abstract void keyTyped(int keyCode);
	protected abstract Image getImage(int player);
	//CONSTRUCTORA.
	public RectBoardGameView(){
		super();
		this.enabled = true;
		this.area = new JTextArea();
		this.iteradorColores = Utils.colorsGenerator();
		this.setOpaque(true);
		initTablero();
	}
	//METODOS.
	protected void initTablero(){
		//Crea el tablero.
		this.jboard = new JBoard(){
			private static final long serialVersionUID = 1L;
			@Override
			protected void keyTyped(int keyCode) {
				RectBoardGameView.this.keyTyped(keyCode);
			}
			// --------------------
			@Override
			protected void mouseClicked(int row, int col, int clickCount,
					int mouseButton) {
				RectBoardGameView.this.mouseClicked(row, col, clickCount, mouseButton);
			}
			// --------------------
			@Override
			protected Shape getShape(int player) {
				return Shape.CIRCLE;
			}
			// --------------------
			@Override
			protected Color getColor(int player) {
				return getPlayerColor(player);
			}
			// --------------------
			@Override
			protected Integer getPosition(int row, int col) {
				return RectBoardGameView.this.getPosition(row, col);
			}
			// --------------------
			@Override
			protected Color getBackground(int row, int col) {
				return (row+col) % 2 == 0 ? Color.WHITE: Color.BLACK;
			}
			// --------------------
			@Override
			protected int getNumRows() {
				return RectBoardGameView.this.getNumRows();
			}
			// --------------------
			@Override
			protected int getNumCols() {
				return RectBoardGameView.this.getNumCols();
			}
			// --------------------
			@Override
			protected Image getImage(int piece) {
				return RectBoardGameView.this.getImage(piece);
			}
		};
		//Anade el tablero.
		this.add(this.jboard, BorderLayout.CENTER);
		//Anade el panel que contiene el JTextArea y el colorChooser.
		this.add(panelDerecho(), BorderLayout.EAST);
	}
	// --------------------------------------------------------------
	private JPanel panelDerecho(){
		JPanel panel = new JPanel(new BorderLayout());
		//Para el JTextArea.
		panel.add(panelArea(), BorderLayout.CENTER);
		//Para el JTable
		panel.add(panelTable(), BorderLayout.SOUTH);
		return panel;
	}
	//---------------------------------------------------------------
	private JPanel panelArea(){
		JPanel panelText = new JPanel();
		panelText.setBorder(BorderFactory.createTitledBorder("Status Messages"));
		this.area = new JTextArea();
		this.area.setEditable(false);
		this.area.setLineWrap(true);
		//PARA QUE LA BARRA SE META ENEL TEXTaREA
		JScrollPane barra = new JScrollPane(this.area);
		barra.setPreferredSize(new Dimension(150,250));
		panelText.add(barra);
		return panelText;
	}
	//---------------------------------------------------------------
	private JPanel panelTable(){ //cambiar
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Players Information"));
		colorC = new ColorChooser(new JFrame(), "Choose Line Color", Color.BLACK);
		colores = new HashMap<>();
		MyTableModel tModel = new MyTableModel();
		tModel.getRowCount();
		JTable tabla = new JTable(tModel) {
			private static final long serialVersionUID = 1L;
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component comp = super.prepareRenderer(renderer, row, col);
				if (col == 1)
					comp.setBackground(colores.get(row));
				else
					comp.setBackground(Color.WHITE);
				comp.setForeground(Color.BLACK);
				return comp;
			}
		};
		tabla.setToolTipText("Click on a row to change the color of a player");
		tabla.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = tabla.rowAtPoint(evt.getPoint());
				int col = tabla.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					changeColor(row);
				}
			}

		});
		JScrollPane barra2 = new JScrollPane(tabla);
		barra2.setPreferredSize(new Dimension(150,89));
		panel.add(barra2);
		return panel;
	}
	//---------------------------------------------------------------
	@Override
	public void enable() {
		enabled = true;
	}
	// --------------------------------------------------------------
	@Override
	public void disable() {
		enabled = false;
	}
	// --------------------------------------------------------------
	@Override
	public void update(S state) { //???????????
		if (state.isFinished()&& state.getWinner() == this.state.getTurn())
				this.guCtrl.stopGame();
		this.state = state;
		this.jboard.repaint();
	}
	// --------------------------------------------------------------
	@Override
	public void showInfoMessage(String msg) {
		//MIRAR SALTO DE LINEA
		this.area.setText(this.area.getText()+ System.getProperty("line.separator") + (msg));
	}
	// --------------------------------------------------------------
	@Override
	public void setController(GameController<S, A> guCtrl) { //????????????
		this.guCtrl = guCtrl;
	}
	// --------------------------------------------------------------
	@Override
	public void setGameViewCtrl(GameWindow<S,A> gvCtrl) { //???????????????
		this.gvCtrl = gvCtrl;
	}
	// --------------------------------------------------------------
	private void changeColor(int row) {
		colorC.setSelectedColorDialog(colores.get(row));
		colorC.openDialog();
		if (colorC.getColor() != null) {
			colores.put(row, colorC.getColor());
			this.jboard.repaint();
		}
	}
	// --------------------------------------------------------------
	private Color getPlayerColor(int player) {
		if(player == 1 || player == 2 || player == 3 || player == 4 || player == 5 || player == 6)
			player = 1;
		else player = 0;
			
		Color clorDado = colores.get(player);
		if( clorDado == null){
			
			clorDado = asigColor(player);
		}
		return clorDado;
	}
	// --------------------------------------------------------------
	private Color asigColor(int player) {
		Color colorss = iteradorColores.next();
		colores.put(player, colorss);
		return colorss;
	}
}