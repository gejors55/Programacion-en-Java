// Ejemplo mínimo de hebras, sin swing.
// Un thread se arranca en un objeto de una clase, pero
// pueden ejecutarse métodos de ese mismo objeto EN DISTINTAS HEBRAS.

public class Ejemplo1c {
	private static class MiThread extends Thread {
		private int num;

		public MiThread(int constante) {
			this.num = constante;
		}

		public void run() {
			mirun(num);
		}

		public void mirun(int n) {

			int i;
			for (i = 0; i < 1000 ; i++) {
				System.out.print(n + System.getProperty("line.separator"));
			}
		}
	}

	public static void main(String[] args) {
		MiThread th0 = new MiThread(0);
		th0.start(); // Ejecución CONCURRENTE
		th0.mirun(2);// Ejecución de un método DEL MISMO OBJETO th0 pero en la
						// hebra de main.
	}
}
