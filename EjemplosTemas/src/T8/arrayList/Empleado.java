package arrayList;

public class Empleado {
	private long sueldo;
	private String nombre;

	public Empleado(String n, long s) {
		nombre = n;
		sueldo = s;
	}

	
	public void subirSueldo(double porcentaje) {
		double aumento = sueldo * porcentaje / 100;
		sueldo += aumento;

	}

	public String toString() {
		return "Empleado[nombre= " + nombre + " sueldo= " + sueldo
				+ "]";
	}
	
	public String getNombre(){
		return nombre;
	}
	public double getSueldo(){
		return sueldo;
	}
}

