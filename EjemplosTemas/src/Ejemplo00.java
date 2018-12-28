public class Ejemplo00 {
	private static int at1 = 3;
	private int at2 = 4;

	private int m1() {
		return at1 * at2;
	}

	//private int m2() {
	//	return Interna.at3 * Interna.at4;
	//} // da error pq no hay objeto creado de la clase interna

	public static class Interna {
		private static int at3 = 5;
		private int at4 = 6;

		private int m3() {
			return at1 * at1;
		}
		// private int m4(){return m1()*at2;} // esto da error pq at2 no es
		// estático ni m1
	}

}
