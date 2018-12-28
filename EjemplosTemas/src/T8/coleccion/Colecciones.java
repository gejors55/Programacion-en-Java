package coleccion;
import java.util.*;

public class Colecciones {

    public static void main(String[] args) {
    	//TreeSet es una implementación de SortedSet, colección sin duplicados 
	TreeSet<Integer> set = new TreeSet<Integer>();
	set.add(58);
	set.add(25);
	set.add(44);
	set.add(25);
	//set.add(1.5); //esto da error de compilación pq tiene que ser entero

	Colecciones.<Integer>escribeColeccion(set);
	//Se puede omitir el parámetro de tipo <Integer> en la llamada al método.
	//Lo deduce el compilador, vale con:
	//Colecciones.escribeColeccion(set);
    }

    public static <E> void escribeColeccion(Collection<E> col) {
	Iterator<E> it = col.iterator();
	while (it.hasNext()) {
	    E elem = it.next();
	    System.out.println(elem);
	} 
    }
}

