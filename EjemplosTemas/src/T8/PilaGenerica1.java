public class PilaGenerica1<T> {
  private Object[] datos;
  private int numDatos;
  private static final int TAM_INI = 10, TAM_INC = 10;

  public PilaGenerica1() {
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
    } else { throw new Exception("horror"); }
  }




    public static void main(String[] args) throws Exception {
	PilaGenerica1<Number> pi = new PilaGenerica1<Number>();
	pi.push(new Integer(25));
	pi.push(new Integer(44));
	pi.push(new Double(37.45));
	double d = pi.pop().doubleValue();
	System.out.println(d);
	int i = pi.pop().intValue();
	System.out.println(i);
	
	System.out.println("otra pila:-----------------------");
	
	PilaGenerica1<String> ps = new PilaGenerica1<String>();
	ps.push(new String("uno"));
	ps.push(new String("dos"));
	String os = ps.pop();
	System.out.println(os);
	os = ps.pop();
	System.out.println(os);
	
	System.out.println("otra pila:-----------------------");
	
	
	
	
	
	
	

    }

}

