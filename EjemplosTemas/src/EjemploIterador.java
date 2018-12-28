import java.util.*;

class Stack<T> implements Iterable<T> {
	private static final int initSize = 10;
	Object[] elem;
	private int last;

	Stack() {
		elem = new Object[initSize];
		last = -1;
	}

	T pop() {
		if (last >= 0)
			return (T) elem[last--];
		else
			return null;
	}

	void push(T x) {
		elem[++last] = x;
	}

	public Iterator<T> iterator() {
		return new Iter();
	}

	// Clase interna Iter, definida dentro de Stack.
	private class Iter implements Iterator<T> {
		int curr = 0; // posición de recorrido del iterador.

		public boolean hasNext() {
			return curr <= last;
		}

		public T next() {
			curr++;
			return (T) elem[curr - 1];
		}

	}
}

public class EjemploIterador {
	public static void main(String[] arg) {
		Stack<String> s = new Stack<String>();
		s.push("uno");
		s.push("dos");
		s.push("tres");
		s.push("cuatro");

		Iterator<String> it = s.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
	}
}
