package es.ucm.fdi.tp.mvc;

import java.util.ArrayList;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameError;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent.EventType;

/**
 * An event-driven game engine.
 * Keeps a list of players and a state, and notifies observers
 * of any changes to the game.
 */
public class GameTable<S extends GameState<S, A>, A extends GameAction<S, A>> implements GameObservable<S, A> {
	//ATRIBUTOS.
	private S iniState;
	private S actState;
	private boolean start;
	private ArrayList <GameObserver<S,A>> observer;
	//CONSTRUCTORA.
    public GameTable(S initState) {
        this.iniState = initState;
        this.actState = initState;
        this.start = false;
        this.observer = new ArrayList<>();
    }
    //METODOS.
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void start() {
    	this.actState = this.iniState;
    	GameEvent<S, A> event = new GameEvent (EventType.Start, null, this.iniState, null, "Game Start");
    	notifyObservers(event);
    	this.start = true;
    }
	//--------------------------------------
    @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void stop() {
    	GameEvent<S, A> event;
    	GameError error = null;
		if (start) {
			this.start = false;
			event = new GameEvent(EventType.Stop, null, this.actState, null,
					"Game ended");
			notifyObservers(event);
			if(this.actState.getWinner() == -1){
				event = new GameEvent (EventType.Info, null, this.actState, null,
						"Draw");
				notifyObservers(event);
			}
			else{
				event = new GameEvent (EventType.Info, null, this.actState, null,
						"Victory for the player " + this.actState.getWinner());
				notifyObservers(event);
			}
		}
    }
    //--------------------------------------
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void execute(A action) {
    	GameEvent<S, A> event;
    	GameError error = null;
    	if(start){
    		S state = action.applyTo(this.actState);
    		if (state.equals(this.actState)){
    			error = new GameError ("Error!");
    			event = new GameEvent (EventType.Error, null, this.actState, error, "The game has an error");
    		}else{
    			this.actState = state;
        		event = new GameEvent (EventType.Change, action, this.actState, null, "GameState changed");
    		}
    	}else{
    		error = new GameError ("Error!");
			event = new GameEvent (EventType.Error, null, this.actState, error, "The game has an error");
    	}
   	 notifyObservers(event);
   	 if (error != null)throw error;
    }
    //--------------------------------------
    public S getState() {
    	return actState;
    }
    //--------------------------------------
    public void addObserver(GameObserver<S, A> o) {
        observer.add(o);
    }
    //--------------------------------------
    public void removeObserver(GameObserver<S, A> o) {
    	observer.remove(o);
    }
    //--------------------------------------
    private void notifyObservers(GameEvent<S, A> e) {
    	for(GameObserver<S, A> o: observer){
    		o.notifyEvent(e);
    	}
	}
}
