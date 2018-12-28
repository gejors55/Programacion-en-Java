// Ejemplo de uso de join().
// Cálculo de números primos en tres hebras distintas.
// La hebra de main espera a la finalización de las tres.

public class EjemploPrimos1 {
	public static final int NUM = 5000000;

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		HebraPrimos hebra1 = new HebraPrimos("uno ", 1, NUM);
		HebraPrimos hebra2 = new HebraPrimos("dos ", NUM + 1, 2 * NUM);
		HebraPrimos hebra3 = new HebraPrimos("tres", 2 * NUM + 1, 3 * NUM);
		hebra1.start();
		hebra2.start();
		hebra3.start();
		try {
			hebra1.join();
			hebra2.join();
			hebra3.join();
		} catch (InterruptedException e) {
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Tiempo total: " + (endTime - startTime) + " ms.------------");
	}

}
