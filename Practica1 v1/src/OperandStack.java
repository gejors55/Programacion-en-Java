
public class OperandStack {
	private int[] stack;
	private int contador = 0;
	public void meter(int elem){
		stack[contador]= elem;
		contador++;
	}
	public void sacar(int pos){
		for( int i = pos+1; i < contador;i++){
			stack[i-1] = stack[i];
		}
		contador--;
	}

	public String toString(){
		String pila = "Pila: " + stack[]);// no se hacer los to string
	
	}
}