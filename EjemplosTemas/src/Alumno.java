//Para ver uso de clases internas.
//Solo puede existir un obj. de la clase interna cuando ex. un obj de la 
//clase contenedora.
//La interna puede acceder a todos los miembros de la clase contenedora.
//La interna no puede contener ningun miembro estï¿½tico.
//Si dentro de la interna se desea hacer una referencia al obj. actual de la
// clase contenedora, se hace mediante NombreClaseContenedora.this.

public class Alumno {
	private String nombre;
	private String apellidos;
	private int añoNacimiento;
	private char horario; // 'M' :maï¿½ana, 'T': tarde
	private Direccion direccion;


	public Alumno(String nombre, String apellidos, int año, char horario,
			String calle, int num) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		añoNacimiento = año;
		this.horario = horario;
		direccion = new Direccion(calle, num);
	}

	public void ponDireccion(Direccion dir) {
		direccion = dir;
	}

	
	public String toString() {
		return nombre + " " + apellidos + " " + añoNacimiento + " " + horario
				+ " " + direccion;
	}

	// clase interna

	class Direccion {
		String calle;
		int numero;

		Direccion(String rue, int num) {
			calle = rue;
			numero = num;
		}

		public String toString() {
			return calle + " " + numero;
		}
	}

	public static void main(String args[]) {
		Alumno alumno = new Alumno("Pepe", "Glez", 1973, 'T', "silvina", 11);
		System.out.println("Datos alumno: " + alumno);

		// le cambiamos la direccion

		Alumno.Direccion direccion = alumno.new Direccion("via carpetana", 79);
		alumno.ponDireccion(direccion);
		System.out.println("Datos alumno: " + alumno);
	}
} // Alumno