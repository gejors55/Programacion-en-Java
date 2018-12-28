package es.ucm.fdi.tp.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import es.ucm.fdi.tp.base.Utils;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.extra.jcolor.ColorChooser;
import es.ucm.fdi.tp.extra.jcolor.TableModel;

public class PlayersInfoComp<S extends GameState<S, A>, A extends GameAction<S, A>>
		extends PlayersInfoViewer<S, A> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3855083007127079352L;

	protected MessageViewer<S, A> infoViewer;
	private TableModel tModel;

	private Map<Integer, Color> colors;
	private ColorChooser colorChooser;
	private Iterator<Color> iteradorColores;

	public PlayersInfoComp() {
		this.observers = new ArrayList<PlayersInfoObserver>();
		this.iteradorColores = Utils.colorsGenerator();
		initGUI();

	}

	private void initGUI() {
		this.setBorder(BorderFactory.createTitledBorder("Players Information"));
		colors = new HashMap<Integer, Color>();
		colorChooser = new ColorChooser(new JFrame(), "Choose Cell Color",
				Color.BLACK);

		tModel = new TableModel();
		tModel.getRowCount();
		JTable table = new JTable(tModel) {
			private static final long serialVersionUID = 1L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int col) {
				Component comp = super.prepareRenderer(renderer, row, col);
				if (col == 1)
					comp.setBackground(colors.get(row));
				else
					comp.setBackground(Color.WHITE);
				comp.setForeground(Color.BLACK);
				return comp;
			}
		};

		table.setToolTipText("Click on a row to change the color of a player");
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					changeColor(row);
				}
			}

		});

		JScrollPane sclInfoPlayer = new JScrollPane(table);
		sclInfoPlayer.setPreferredSize(new Dimension(150, 89));
		this.add(sclInfoPlayer);
		this.setOpaque(true);
		this.setVisible(true);
	}

	private void changeColor(int row) {
		colorChooser.setSelectedColorDialog(colors.get(row));
		colorChooser.openDialog();
		if (colorChooser.getColor() != null) {
			colors.put(row, colorChooser.getColor());
			repaint();
			notifyObservers(row, colors.get(row));
			this.infoViewer.addContent("Color changed to "
					+ colorChooser.getColor().toString());
		}

	}

	private Color chooseColor(int player) {
		Color colorsI = iteradorColores.next();
		if (colors.get(player) == null) {
			colors.put(player, colorsI);
			repaint();
			notifyObservers(player, colors.get(player));
			return colorsI;
		} else
			return colors.get(player);
	}

	@Override
	public void setNumberOfPlayer(int n) {
		this.tModel = new TableModel(n);
		this.tModel.refresh();
		this.repaint();
	}

	@Override
	public Color getPlayerColor(int p) {
		return chooseColor(p);
	}

	@Override
	public void enable() {
		this.colorChooser.setEnabled(true);
	}

	@Override
	public void disable() {
	}

	@Override
	public void update(S state) {
	}

	@Override
	public void setMessageViewer(MessageViewer<S, A> infoViewer) {
		this.infoViewer = infoViewer;
	}

	@Override
	public void setGameController(GameController<S, A> gameCtrl) {
	}

}
