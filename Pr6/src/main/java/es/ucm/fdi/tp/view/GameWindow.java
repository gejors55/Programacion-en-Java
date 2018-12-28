package es.ucm.fdi.tp.view;

//import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.ConcurrentAiPlayer;
import es.ucm.fdi.tp.mvc.GameEvent;

//import es.ucm.fdi.tp.mvc.GameObservable;
import es.ucm.fdi.tp.mvc.GameObserver;


public class GameWindow<S extends GameState<S, A>, A extends GameAction<S, A>>
		extends JFrame implements GameViewCtrl<S, A>, GameObserver<S, A> {
	// ENUMERADO.
	public enum PlayerMode {
		Random, Smart, Manual
	}

	// ATRIBUTOS.
	private int playerId;
	private GamePlayer randPlayer; // Necesario para utilizar el boton random o
									// ponerlo en modo random.
	private ConcurrentAiPlayer smartPlayer; // Necesario para utilizar el boton
											// smart o ponerlo en modo smart.
	private GameView<S, A> gameView; // Para el tablero.
	private GameController<S, A> controlador; // El controlador del MVC.
	private PlayerMode playerMode; // Modo del jugador, por defecto Mnual.
	private JButton boton1; // boton random
	private JButton boton2; // boton smart
	private JButton boton3; // boton restart
	private JButton boton4; // boton exit
	private JButton boton5; // boton stop
	private int threads; // hilos
	private int ms; // tiempo maximo de ejecuccion.
	private Thread smartThread;
	private JPanel panelCerebro;

	// CONSTRUCTORA.
	public GameWindow(int playerId, GamePlayer randPlayer,
			ConcurrentAiPlayer smartPlayer, GameView<S, A> gameView,
			GameController<S, A> gameCtrl) {
		// Inicializa los atrubutos.
		this.playerId = playerId;
		this.randPlayer = randPlayer;
		this.smartPlayer = smartPlayer;
		this.gameView = gameView;
		this.controlador = gameCtrl;
		this.boton1 = new JButton();
		this.boton2 = new JButton();
		this.boton3 = new JButton();
		this.boton4 = new JButton();
		this.boton5 = new JButton();
		this.boton5.setEnabled(false);
		this.threads = 1;
		this.smartPlayer.setMaxThreads(this.threads);
		this.ms = 1000;
		this.smartPlayer.setTimeout(this.ms);
		// Para la vista.
		this.gameView.update(gameCtrl.getState());
		this.gameView.setController(this.controlador); // le pasas el
														// controlador a was o
														// ttt view
		this.gameView.setGameViewCtrl(this); //
		// Para el controlador.
		this.controlador.setRandomPlayer(randPlayer);
		this.controlador.setSmartPlayer(smartPlayer);
		this.playerMode = PlayerMode.Manual;
		// Inicia la vista.
		iniGUI();
		// Añade el observador.
		this.controlador.modelo.addObserver(this);
	}

	// METODOS.
	private void iniGUI() {
		// Panel principal.
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		// Crea todo de RedBoardGameView.
		panelPrincipal.add(this.gameView);
		// Anade la barra en el norte de la ventana.
		this.getContentPane().add(toolBar(), BorderLayout.NORTH);
		// Anade el panel principal al centro.
		this.add(panelPrincipal, BorderLayout.CENTER);
		// Medidas de la ventana.
		if (playerId == 0) {
			this.setBounds(5, 100, 720, 550);
		} else {
			this.setBounds(710, 100, 720, 550);
		}
		// No se puede hacer mas grande.
		this.setResizable(false);
		// Es visible.
		this.setVisible(true);
		// Se cierra con la cruz.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// ---------------------------------------------------------------
	private JToolBar toolBar() {
		JToolBar barraDeArriba = new JToolBar();
		// Creas los bot1ones y los agrega a la barra.
		botonRandom();
		barraDeArriba.add(boton1);
		botonSmart();
		barraDeArriba.add(boton2);
		botonRestart();
		barraDeArriba.add(boton3);
		botonExit();
		barraDeArriba.add(boton4);
		// Crea una separacion despues de los botones.
		barraDeArriba.addSeparator();
		// Crea un texto en la barra.
		barraDeArriba.add(new JLabel("Player mode "));
		// Crea una caja con los modos del jugador.
		barraDeArriba.add(comboMode());
		// Crea una separacion despues de los botones.
		barraDeArriba.addSeparator();
		barraDeArriba.add(panelSmartMoves());
		return barraDeArriba;
	}

	// ---------------------------------------------------------------
	private void botonRandom() {
		this.boton1.setIcon(new ImageIcon(getClass().getResource("/dice.png")));
		this.boton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameWindow.this.gameView
						.showInfoMessage("Has pulsado el boton random");
				GameWindow.this.randomActionButtonPressed();
			}
		});
	}

	// ---------------------------------------------------------------
	@Override
	public void randomActionButtonPressed() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (!GameWindow.this.controlador.getState().isFinished())
					GameWindow.this.gameView
							.showInfoMessage("movimiento hecho por jugador "
									+ GameWindow.this.controlador.getState().getTurn());
				GameWindow.this.controlador.makeRandomMove();
			}
		});

	}

	// ---------------------------------------------------------------
	private void botonSmart() {
		this.boton2.setIcon(new ImageIcon(getClass().getResource("/nerd.png")));
		this.boton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameWindow.this.gameView
						.showInfoMessage("Has pulsado el boton smart");
				GameWindow.this.smartActionButtonPressed();
			}
		});
	}

	// ---------------------------------------------------------------
	@Override
	public void smartActionButtonPressed() {
		if (!this.controlador.getState().isFinished()) {
			setThinking(true);
			this.smartThread = new Thread(new Runnable() {

				@Override
				public void run() {
					GameWindow.this.smartPlayer.join(GameWindow.this.controlador.getState().getTurn());
					GameWindow.this.smartPlayer.setMaxThreads(GameWindow.this.threads);
					GameWindow.this.smartPlayer.setTimeout(GameWindow.this.ms);
					long startTime = System.currentTimeMillis();
					A accion = smartPlayer.requestAction(GameWindow.this.controlador.getState());
					long endTime = System.currentTimeMillis();
					if(accion != null){
						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								GameWindow.this.controlador.makeSmartMove(accion);
							}

						});
						long time = endTime - startTime;
						long div = smartPlayer.getEvaluationCount() / time;
						
						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								String s;
								GameWindow.this.setThinking(false);
								s = smartPlayer.getEvaluationCount() + " nodes in " + time + " ms "
										+ " (" + div + " n/ms) value = " + smartPlayer.getValue();
								GameWindow.this.gameView.showInfoMessage(s);
							}

						});
						
					}
				}

			});
			this.smartThread.start();
		}
	}

	// ---------------------------------------------------------------
	private void setThinking(boolean think) {
		if(think == true){
			SwingUtilities.invokeLater(new Runnable(){

				@Override
				public void run() {
					GameWindow.this.panelCerebro.setBackground(Color.YELLOW);
					GameWindow.this.boton5.setEnabled(true);
					GameWindow.this.boton1.setEnabled(false);
					GameWindow.this.boton2.setEnabled(false);
				}
				
			});
		}else{
			SwingUtilities.invokeLater(new Runnable(){

				@Override
				public void run() {
					GameWindow.this.panelCerebro.setBackground(null);
					GameWindow.this.boton5.setEnabled(false);
				}
				
			});
		}
	}

	// ---------------------------------------------------------------
	private void botonRestart() {
		this.boton3.setIcon(new ImageIcon(getClass()
				.getResource("/restart.png")));
		this.boton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameWindow.this.gameView
						.showInfoMessage("Has pulsado el boton restar");
				GameWindow.this.restartButtonPressed();
				GameWindow.this.boton1.setEnabled(true);
				GameWindow.this.boton1.setEnabled(true);
			}
		});
	}

	// ---------------------------------------------------------------
	@Override
	public void restartButtonPressed() {
		this.controlador.restartGame();
	}

	// ---------------------------------------------------------------
	private void botonExit() { // Revisar
		this.boton4.setIcon(new ImageIcon(getClass().getResource("/exit.png")));
		this.boton4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameWindow.this.gameView
						.showInfoMessage("Has pulsado el boton exit");
				GameWindow.this.quitButtonPressed();
			}
		});
	}

	// ---------------------------------------------------------------
	@Override
	public void quitButtonPressed() {
		if (JOptionPane.showConfirmDialog(null, "¿Desea abandonar el juego?",
				"Confirmar salida", JOptionPane.YES_NO_OPTION,
				JOptionPane.CANCEL_OPTION) == 0) {
			this.controlador.stopGame();
			System.exit(0);
		}
	}

	// ---------------------------------------------------------------
	@SuppressWarnings("rawtypes")
	private JComboBox comboMode() {
		JComboBox<String> combo = new JComboBox<String>();
		combo.setModel(new DefaultComboBoxModel<>(new String[] { "Manual",
				"Random", "Smart" }));
		// combo.setSize(1,1);
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (combo.getSelectedItem().toString()
						.equalsIgnoreCase("SMART")) {
					GameWindow.this.gameView
							.showInfoMessage("Has activado el modo smart");
					GameWindow.this.playerModeSelected(PlayerMode.Smart);
				} else if (combo.getSelectedItem().toString()
						.equalsIgnoreCase("RANDOM")) {
					GameWindow.this.gameView
							.showInfoMessage("Has activado el modo random");
					GameWindow.this.playerModeSelected(PlayerMode.Random);
				} else {
					GameWindow.this.gameView
							.showInfoMessage("Has activado el modo manual");
					GameWindow.this.playerModeSelected(PlayerMode.Manual);
				}
			}

		});
		return combo;
	}

	// ---------------------------------------------------------------
	private JPanel panelSmartMoves() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Smart Moves"));
		panel.setVisible(true);
		// Imagen del crebro.
		this.panelCerebro = new JPanel();
		JLabel labelCerebro = new JLabel();
		ImageIcon imagenCerebro = new ImageIcon(this.getClass().getResource(
				"/brain.png"));
		labelCerebro.setIcon(imagenCerebro);
		this.panelCerebro.add(labelCerebro);
		panel.add(this.panelCerebro);
		// Spiner de los hilos.
		panel.add(spinnerThreads());
		panel.add(new JLabel("threads"));
		// Imagen reloj.
		JLabel labelReloj = new JLabel();
		ImageIcon imagenReloj = new ImageIcon(this.getClass().getResource(
				"/timer.png"));
		labelReloj.setIcon(imagenReloj);
		panel.add(labelReloj);
		// Spiner del tiempo.
		panel.add(spinnerTime());
		panel.add(new JLabel("ms"));
		// Boton stop.
		botonStop();
		panel.add(this.boton5);

		return panel;
	}

	// ---------------------------------------------------------------
	private JSpinner spinnerThreads() {
		int nucleosProcesador = Runtime.getRuntime().availableProcessors();
		JSpinner spinerNucleos = new JSpinner(new SpinnerNumberModel(1, 1,
				nucleosProcesador, 1));
		spinerNucleos.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				GameWindow.this.threads = (int) spinerNucleos.getValue();
				GameWindow.this.gameView
						.showInfoMessage("Has cambiado el numero de hilos a: "
								+ GameWindow.this.threads);
			}
		});
		return spinerNucleos;
	}

	// ---------------------------------------------------------------
	private JSpinner spinnerTime() {
		JSpinner spinerTiempo = new JSpinner(new SpinnerNumberModel(1000, 500,
				5000, 500));
		spinerTiempo.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				GameWindow.this.ms = (int) spinerTiempo.getValue();
				GameWindow.this.gameView
						.showInfoMessage("Has cambiado el tiempo maximo a: "
								+ GameWindow.this.ms);
			}
		});
		return spinerTiempo;
	}

	// ---------------------------------------------------------------
	private void botonStop() {
		ImageIcon imagenStop = new ImageIcon(this.getClass().getResource(
				"/stop.png"));
		this.boton5.setIcon(imagenStop);
		this.boton5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameWindow.this.stopButtonPressed();
			}
		});

	}

	// ---------------------------------------------------------------
	public void stopButtonPressed() { 
		this.smartThread.interrupt();
		setThinking(false);
	}

	// ---------------------------------------------------------------
	@Override
	public void notifyEvent(GameEvent<S, A> e) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				handleEvent(e);
			}
		});
	}

	// ---------------------------------------------------------------
	@Override
	public void userActionAvailable(A p) {
		if (controlador.getState().getTurn() == this.playerId) {
			this.controlador.makeManualMove(p);
			GameWindow.this.gameView.disable();
		}
	}

	// ---------------------------------------------------------------
	public void playerModeSelected(PlayerMode playerMode) {
		this.playerMode = playerMode;
		if (playerMode.equals(PlayerMode.Random)) {
			if (this.controlador.getState().getTurn() == this.playerId)
				randomActionButtonPressed();
		} else if (playerMode.equals(PlayerMode.Smart)) {
			if (this.controlador.getState().getTurn() == this.playerId)
				smartActionButtonPressed();
		}
	}

	// ---------------------------------------------------------------
	public void handleEvent(GameEvent<S, A> e) { // cambiar
		switch (e.getType()) {
		case Start:
		case Change:
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					gameView.update(GameWindow.this.controlador.getState());
					decideAutomaticMakeMove();
				}
			});
			// this.gameView.update(e.getState());
			this.gameView.showInfoMessage(e.toString());
			if (controlador.getState().getTurn() != this.playerId) {
				boton1.setEnabled(false);
				boton2.setEnabled(false);
			} else {
				boton1.setEnabled(true);
				boton2.setEnabled(true);
			}
			break;
		case Error:
		case Stop:
		case Info:
			this.gameView.showInfoMessage(e.toString());
			break;
		default:
			System.out.println("Evento incorrecto");
			break;
		}
	}

	// ---------------------------------------------------------------
	public void decideAutomaticMakeMove() { // cambiar
		if (!GameWindow.this.controlador.getState().isFinished()) {
			if (GameWindow.this.controlador.getState().getTurn() == GameWindow.this.playerId) {
				GameWindow.this.gameView.enable();
				switch (GameWindow.this.playerMode) {
				case Random:
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							GameWindow.this.randomActionButtonPressed();
						}
					});
					GameWindow.this.gameView.disable();
					break;
				case Smart:
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							GameWindow.this.smartActionButtonPressed();
						}
					});
					GameWindow.this.gameView.disable();
					break;
				default:
					break;
				}
			} else {
				if (GameWindow.this.playerMode == GameWindow.PlayerMode.Manual) {
					GameWindow.this.gameView.showInfoMessage("Turn for player "
							+ GameWindow.this.controlador.getState().getTurn());
					GameWindow.this.gameView.disable();
				}
			}
		}else{
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					GameWindow.this.boton1.setEnabled(false);
					GameWindow.this.boton2.setEnabled(false);
				}
			});
		}	
	}
}
