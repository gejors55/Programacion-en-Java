// Ejemplo de uso de join() (segunda parte).
// Ejemplo de cálculo de números primos en una sola hebra.

public class EjemploPrimos2 {
	public static final int NUM = 5000000;

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		HebraPrimos hebra1 = new HebraPrimos("uno ", 1, 3 * NUM);
		hebra1.start();
		try {
			hebra1.join();
		} catch (InterruptedException e) {
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Tiempo total: " + (endTime - startTime) + " ms.");
	}

}
