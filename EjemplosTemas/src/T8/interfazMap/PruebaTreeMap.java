package interfazMap;

import java.util.*;

public class PruebaTreeMap {
	public static void main(String[] args) {
		TreeMap<String, Persona> grupo = new TreeMap<String, Persona>();

		Persona p1 = new Persona("232323245M", "pepe", 37);
		Persona p2 = new Persona("525252545W", "luis", 31);

		grupo.put(p1.getDni(), p1);
		grupo.put(p2.getDni(), p2);
		
		//para sacar uno en concreto
		System.out.println(grupo.get("232323245M"));
	}
}
