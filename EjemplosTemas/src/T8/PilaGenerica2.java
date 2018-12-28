public class PilaGenerica2<T extends Number> {
    private Object[] datos;
    private int numDatos;
    private static final int TAM_INI = 10, TAM_INC = 10;

    public PilaGenerica2() {
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

    @SuppressWarnings("unchecked")
	public T pop() throws Exception {
	if (numDatos > 0) {
	    numDatos--;
	    return (T)datos[numDatos];
	} else { throw new Exception("horror, pila vacia."); }
    }

    public static void main(String[] args) throws Exception {
	PilaGenerica2<Number> pi = new PilaGenerica2<Number>();
	pi.push(25);
	pi.push(44);
	pi.push(37.45);
	//pi.push("hola"); // esto da error de compilacion.
	double d = pi.pop().doubleValue();
	
	System.out.println(d);
	int i = pi.pop().intValue();
	System.out.println(i);
    }

}

