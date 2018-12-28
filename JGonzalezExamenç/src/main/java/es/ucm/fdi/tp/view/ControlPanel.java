package es.ucm.fdi.tp.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.view.GameController.PlayerMode;

/*
 * buttons:
 * 1 - Ask the controller to make a random move
 * 2 - Ask the controller to make a smart move
 * 3 - Ask the controller to restart the game
 * 4 - First ask the user to confirm the action, then ask the user to stop the game, and then exit the program.
 * 
 * combo-box:
 * 5 - Ask the controller to change the player's mode
 */
public class ControlPanel<S extends GameState<S, A>, A extends GameAction<S, A>>
		extends GUIView<S, A> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7617550919831633808L;

	private GameController<S, A> gameCtrl;
	private JButton randomMove;
	private JButton smartMove;
	private JButton restart;
	private JButton exit;
	private JButton puta;
	private JButton puta2;
	private JButton saltarTurno;
	private JButton remove;
	private JButton desHacer;
	private JComboBox<String> lstLista;
	protected MessageViewer<S, A> infoViewer;
	protected boolean show = true;

	public ControlPanel(GameController<S, A> gameCtrl) {
		this.gameCtrl = gameCtrl;
		initGUI();
	}

	private void initGUI() {
		String[] modes = { "Manual", "Random", "Smart" };

		lstLista = new JComboBox<String>(modes);
		// this.lstLista.setSize(50, 10);

		lstLista.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (lstLista.getSelectedItem().toString()
						.equalsIgnoreCase("Manual")) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							ControlPanel.this.gameCtrl
									.changePlayerMode(PlayerMode.MANUAL);
							if (ControlPanel.this.show)
								ControlPanel.this.infoViewer
										.addContent("Player mode changed to Manual.");
						}
					});
				}

				else if (lstLista.getSelectedItem().toString()
						.equalsIgnoreCase("RANDOM")) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							ControlPanel.this.gameCtrl
									.changePlayerMode(PlayerMode.RANDOM);
							ControlPanel.this.infoViewer
									.addContent("Player mode changed to Random.");
						}
					});
				} else if (lstLista.getSelectedItem().toString()
						.equalsIgnoreCase("SMART")) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							ControlPanel.this.gameCtrl
									.changePlayerMode(PlayerMode.SMART);
							ControlPanel.this.infoViewer
									.addContent("Player mode changed to Smart.");
						}
					});
				}
			}
		});
		randomMove = new JButton("");
		randomMove.setIcon(new ImageIcon(getClass().getResource("/dice.png")));
		randomMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						ControlPanel.this.gameCtrl.makeRandomMove();
						ControlPanel.this.infoViewer
								.addContent("Random Move Accomplished.");
					}
				});
			}
		});
		smartMove = new JButton("");
		smartMove.setIcon(new ImageIcon(getClass().getResource("/nerd.png")));
		smartMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						ControlPanel.this.gameCtrl.makeSmartMove();
						ControlPanel.this.infoViewer
								.addContent("Smart Move Accomplished.");
					}
				});
			}
		});
		restart = new JButton("");
		restart.setIcon(new ImageIcon(getClass().getResource("/restart.png")));
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						ControlPanel.this.repaint();
						ControlPanel.this.gameCtrl.restratGame();
						ControlPanel.this.infoViewer
								.addContent("Game has been restated.");
					}
				});
			}
		});
		exit = new JButton("");
		exit.setIcon(new ImageIcon(getClass().getResource("/exit.png")));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						ControlPanel.this.gameCtrl.stopGame();
					}
				});
			}
		});
		
		
		
		/*
		nThreadsSpinner = new JSpinner(new SpinnerNumberModel(
				ControlPanel.this.smartNThreads, 1, Runtime.getRuntime()
						.availableProcessors(), 1));
		nThreadsSpinner.addChangeListener((e) -> {
			ControlPanel.this.smartNThreads = (Integer) nThreadsSpinner
					.getValue();

			ControlPanel.this.gameCtrl
					.smartPlayerConcurrencyLevel((Integer) nThreadsSpinner
							.getValue());

		});
	*/
		
		
		puta = new JButton("");
		puta.setIcon(new ImageIcon(getClass().getResource("/timer.png")));
		puta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						ControlPanel.this.gameCtrl.carga();
						ControlPanel.this.infoViewer
						.addContent("Game has been charged.");
					}
				});
			}
		});
		puta2 = new JButton("");
		puta2.setIcon(new ImageIcon(getClass().getResource("/nerd.png")));
		puta2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						ControlPanel.this.gameCtrl.guarda();
						ControlPanel.this.infoViewer
						.addContent("Game has been saved.");
					}
				});
			}
		});
		saltarTurno = new JButton("");
		saltarTurno.setIcon(new ImageIcon(getClass().getResource("/exit.png")));
		saltarTurno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						ControlPanel.this.gameCtrl.saltarTurno();
						ControlPanel.this.infoViewer
						.addContent("Has saltado el turno.");
					}
				});
			}
		});
		remove = new JButton("");
		remove.setIcon(new ImageIcon(getClass().getResource("/dice.png")));
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						ControlPanel.this.gameCtrl.remvoe();
						ControlPanel.this.infoViewer
						.addContent("Has retrocedido una jugada.");
					}
				});
			}
		});
		desHacer = new JButton("");
		desHacer.setIcon(new ImageIcon(getClass().getResource("/dice.png")));
		desHacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						ControlPanel.this.gameCtrl.desHacer();
						ControlPanel.this.infoViewer
						.addContent("Has retrocedido una jugada.");
					}
				});
			}
		});
		
		
		this.setLayout(new FlowLayout((FlowLayout.LEFT)));
		JToolBar bar = new JToolBar();
		bar.add(randomMove);
		bar.add(smartMove);
		bar.add(restart);
		bar.add(exit);
		bar.add(desHacer);
		bar.addSeparator();
		bar.add(new JLabel("Player Mode:"));
		bar.addSeparator();
		bar.add(lstLista);
		this.add(bar);
		this.setVisible(true);
	}

	@Override
	public void enable() {
		this.randomMove.setEnabled(true);
		this.smartMove.setEnabled(true);
		this.restart.setEnabled(true);
		this.exit.setEnabled(true);
		this.puta.setEnabled(true);
		this.puta2.setEnabled(true);
		this.saltarTurno.setEnabled(true);
		this.desHacer.setEnabled(true);
	}

	@Override
	public void disable() {
		this.randomMove.setEnabled(false);
		this.smartMove.setEnabled(false);
		//this.restart.setEnabled(false);
		//this.exit.setEnabled(false);
		this.puta.setEnabled(false);
		this.puta2.setEnabled(false);
		this.saltarTurno.setEnabled(false);
		this.desHacer.setEnabled(false);
	}

	@Override
	public void update(S state) {
	}

	@Override
	public void setMessageViewer(MessageViewer<S, A> infoViewer) {
		this.infoViewer = infoViewer;

	}

	@Override
	public void setPlayersInfoViewer(PlayersInfoViewer<S, A> playersInfoViewer) {

	}

	@Override
	public void setGameController(GameController<S, A> gameCtrl) {
		this.gameCtrl = gameCtrl;
	}

	public void resetControlPanel() {
		this.show = false;
		this.lstLista.setSelectedIndex(0);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ControlPanel.this.show = true;
			}
		});
	}

	public void enableEnd() {
		this.randomMove.setEnabled(false);
		this.smartMove.setEnabled(false);
		this.restart.setEnabled(true);
		this.exit.setEnabled(false);
	}
}
