public class Ejemplo0 {

	// Ejemplo mínimo de hebras, sin swing.
	// Uso de la herencia de Thread y del método start().

	private static class MiThread extends Thread {
		private int num;

		public MiThread(int constante) {
			this.num = constante;
		}

		public void run() {
			while (true) {
				System.out.print("hebra: " + num +"!!!!!!!!!!!!!!!!!!!!!!!!!!!"
						+ System.getProperty("line.separator"));
			}

		}
	}

	public static void main(String[] args) {

		MiThread th0 = new MiThread(0);
		 //th0.run(); //así sería para ejecutarlo en la hebra ppal
		th0.start(); // Ejecución CONCURRENTE
		int i;
		for (i = 0; i < 10000000; i++) {
			System.out.print("estoy en el main--------------------------- "
					+ System.getProperty("line.separator"));

		}
	}
}
