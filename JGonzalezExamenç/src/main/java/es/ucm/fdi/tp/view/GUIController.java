package es.ucm.fdi.tp.view;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameObserver;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.view.GameController;

public class GUIController<S extends GameState<S, A>, A extends GameAction<S, A>>
		implements GameController<S, A> {

	private GamePlayer randPlayer;
	private GamePlayer smartPlayer;
	private PlayerMode playerMode;
	private int playerId;
	private GameTable<S, A> game;
	private boolean stop;

	public GUIController(int playerId, GamePlayer randPlayer,
			GamePlayer smartPlayer, GameTable<S, A> game) {
		this.randPlayer = randPlayer;
		this.smartPlayer = smartPlayer;
		this.playerId = playerId;
		this.game = game;
		this.playerMode = PlayerMode.MANUAL;
		this.stop = false;
	}

	@Override
	public void run() {
	}

	@Override
	public void makeManualMove(A a) {
		this.game.execute(a);

	}

	@Override
	public void makeRandomMove() {
		this.game.execute(randPlayer.requestAction(this.game.getState()));

	}
	@Override
	public void desHacer() {
		this.game.desHacer();
		
	}
	@Override
	public void makeSmartMove() {
		this.game.execute(smartPlayer.requestAction(this.game.getState()));

	}
	@Override
	public void saltarTurno() {
		this.game.saltarTurno(this.game.getState());
		
	}
	
	public void carga(){
		game.carga();
	}
	public void guarda(){
		game.guarda(this.game.getState());
	}
	@Override
	public void restratGame() {
		this.stop = false;
		this.game.start();

	}

	@Override
	public void stopGame() {
		this.stop = true;
		this.game.stop();

	}
	
	@Override
	public void remvoe() {
		game.remove();
		
	}
	@Override
	public void changePlayerMode(PlayerMode p) {
		this.playerMode = p;
		if (p != PlayerMode.MANUAL
				&& this.game.getState().getTurn() == this.playerId
				&& !this.game.getState().isFinished() && !stop)
			decideMakeAutomaicMove();
	}

	@Override
	public void handleEvent(GameEvent<S, A> e) {
		if (this.game.isActive()) {
			switch (e.getType()) {
			case Start:
				this.stop = false;
				break;
			case Change:
				if (e.getState().getTurn() == this.playerId) {
					decideMakeAutomaicMove();
				}
				break;
			case Stop:
				this.stop = true;
				this.game.stop();
				break;
			case Error:
				e.getError();
				break;
			case Info:
				if (e.getState().getTurn() == this.playerId)
					decideMakeAutomaicMove();
				break;
			default:
				break;
			}
		}
	}

	@Override
	public PlayerMode getPlayerMode() {
		return this.playerMode;
	}

	@Override
	public int getPlayerId() {
		return this.playerId;
	}

	private void decideMakeAutomaicMove() {
		if (this.playerMode != PlayerMode.MANUAL) {
			switch (this.playerMode) {
			case SMART:
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						makeSmartMove();
					}
				});
				break;
			case RANDOM:
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						makeRandomMove();
					}
				});
				break;
			default:
				break;
			}
		}
	}

	public void addObservador(GameObserver<S, A> ob) {
		game.addObserver(ob);
	}

	public void removeObservador(GameObserver<S, A> ob) {
		game.removeObserver(ob);
	}







}
