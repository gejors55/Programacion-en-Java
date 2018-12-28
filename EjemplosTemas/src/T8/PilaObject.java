public class PilaObject {
  private Object[] datos;
  private int numDatos;
  private static final int TAM_INI = 10, TAM_INC = 10;

  public PilaObject() {
    datos = new Object[TAM_INI];
    numDatos = 0;
  }

  public void push(Object dato) {
    if (numDatos == datos.length) {
      Object[] aux = new Object[datos.length+TAM_INC];
      System.arraycopy(datos,0,aux,0,datos.length);
      datos = aux;
    }
    datos[numDatos++] = dato;
  }

  public Object pop() throws Exception {
    if (numDatos > 0) {
      numDatos--;
      return datos[numDatos];
    } else { throw new Exception("horror"); }
  }

    public static void main(String[] args) throws Exception {
	PilaObject p = new PilaObject();
	String o;
	p.push("Hola");
	p.push(new String("24"));
//	p.push(new Integer(37));// ClassCastException
	o = (String)p.pop(); 
	System.out.println(o);
	o = (String)p.pop();
	System.out.println(o);
    }

}

