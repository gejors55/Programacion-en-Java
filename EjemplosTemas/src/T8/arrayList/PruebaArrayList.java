package arrayList;

import java.util.*;


public class PruebaArrayList {
	public static <T> void imprimeEmpleados(ArrayList<T> p) {

		Iterator<T> it = p.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("------------------------");
	}

	public static void main(String[] args) {

		ArrayList<Empleado> personal = new ArrayList<Empleado>();
		personal.add(new Empleado("pepe", 25000));
		personal.add(new Empleado("leopoldo", 28000));
		personal.add(new Empleado("maria", 15000));

		// si intento añadir al arrayList un objeto de tipo persona no compila
		// personal.add(new Persona("4376764W","maria", 34));

		// imprimimos información sobre los empleados
		imprimeEmpleados(personal);

		// aumentamos sueldo en un 5%
		for (Empleado e : personal)
			e.subirSueldo(5);

		// imprimimos información sobre los empleados
		imprimeEmpleados(personal);

		// consultar nº de elementos
		System.out.println("Nº de elementos de personal : " + personal.size());

		// Accedemos al segundo elemento
		System.out.println("Accedemos al segundo elemento de personal : "
				+ personal.get(1));

		// Modificamos un registro
		personal.set(0, new Empleado("luis", 12000));
		System.out.println("------------------------");

		// imprimimos información sobre los empleados
		imprimeEmpleados(personal);

	}

}
