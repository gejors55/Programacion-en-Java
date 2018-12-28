import java.util.ArrayList;


public class Modelo {
	//En observadores llevaremos las vistas registradas como observadores del modelo
	ArrayList<ObservadorModelo> observadores;
		
	public Modelo() {
		observadores = new ArrayList<ObservadorModelo>();
	}
	
	public void calcular(double valor) {
		double res = valor*valor;
		
		// se notifica a los observadores
		for (ObservadorModelo ob : observadores) 
			ob.actualizar(res);
	}
	
	//añade la vista al arrayList observadores
	public void addObservador(ObservadorModelo ob) { 
		observadores.add(ob);
	}
	
	public void removeObservador(ObservadorModelo ob) {
		observadores.remove(ob);
	}
}
