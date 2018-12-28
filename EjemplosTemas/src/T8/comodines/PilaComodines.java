package comodines;
class Pila<T extends Number> {
  private Object[] datos;
  private int numDatos;
  private static final int TAM_INI = 10, TAM_INC = 10;

  public Pila() {
      //no se pueden crear arrays ni objetos de genericos.
      datos = new Object[TAM_INI];
      numDatos = 0;
  }

  public void push(T dato) {
    if (numDatos == datos.length) {
	Object[] aux = new Object[datos.length+TAM_INC];
      System.arraycopy(datos,0,aux,0,datos.length);
      datos = aux;
    }
    datos[numDatos++] = dato;
  }

  public T pop() throws Exception {
    if (numDatos > 0) {
      numDatos--;
      return (T)datos[numDatos];
    } else { throw new Exception("horror, pila vacia."); }
  }

  public boolean vacia() {
      return (numDatos == 0);
  }
}

public class PilaComodines {
    public static void main(String[] args) throws Exception {
	Pila<Integer> pi = new Pila<Integer>();
	Pila<Double> pd = new Pila<Double>();
	pi.push(25);
	pi.push(44);
	
	//intento añadir un double a pi
	//pi.push(44.5); da error de compilación
	
	escribePilaNumeros(pi);
	System.out.println("-----------------------");
	pd.push(3.5);
	pd.push(2.5);
	escribePilaNumeros(pd);	
    }
    
   // utilizo este método para los 2 tipos de pila
    public static void escribePilaNumeros(Pila<? extends Number> p) throws Exception {
	while (!p.vacia()) 
	    System.out.println(p.pop());
    }
    
 //si lo hiciera sin comodín daría error de compilación en escribePilaNumeros(pi) y en escribePilaNumeros(pd);
 //sin comodín: 
   // public static void escribePilaNumeros(Pila< Number> p) throws Exception {
   // 	while (!p.vacia()) 
   	//    System.out.println(p.pop());
    //  }
 //ésto sí me valdría si la pila es de Number, sí compilaría
    
}

