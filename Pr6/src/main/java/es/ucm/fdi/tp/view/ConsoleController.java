package es.ucm.fdi.tp.view;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent;
//import es.ucm.fdi.tp.mvc.GameObserver;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.view.GameWindow.PlayerMode;

/*El ConsoleController recibe, en el constructor, un GameTable y una lista 
de jugadores, del mismo tipo que los de la P4 El juego se inicia llamando a run, que debe ser
bastante similar al playGame de la P4. En un juego con ConsoleController, el método
requestAction de los jugadores se llama desde dentro del bucle de juego contenido en
run. El controlador no debe mostrar nada en la pantalla (exceptuando los mensajes 
mostrados por ConsolePlayer): mostrar mensajes es tarea de la vista. La clase ConsoleView
recibe un GameObservable como entrada, y se registra como observador. Cada vez que
recibe una notificación, muestra un mensaje apropiado. Por ejempl: comienzo de juego, a
quién le toca ahora, fin de juego, etcétera.*/

public class ConsoleController<S extends GameState<S, A>, A extends GameAction<S, A>> extends GameController <S,A> implements  Runnable {
	//ATRIBUTOS.
	private List<GamePlayer> ListaDejugadores;
	//CONSTRUCTORA.
	public ConsoleController(List<GamePlayer> players, GameTable<S, A> game) {
		super(game);
		this.ListaDejugadores = new ArrayList<GamePlayer>();
		for(int i = 0; i< players.size();i++){
			this.ListaDejugadores.add(players.get(i));
		}
	}
	//METODOS.
	public void run() {
		this.modelo.start();
		int playerCount = 0;
		for (GamePlayer p :this.ListaDejugadores) {
			p.join(playerCount++); 
		}
		while (!this.modelo.getState().isFinished()) {
			A action = ListaDejugadores.get(this.modelo.getState().getTurn()).requestAction(this.modelo.getState());
			this.modelo.execute(action);
		}
		if(this.modelo.getState().isFinished()) this.modelo.stop();
	}
	
}