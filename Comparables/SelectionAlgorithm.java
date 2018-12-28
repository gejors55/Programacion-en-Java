package comparables;

public class SelectionAlgorithm<T extends Comparable<T>> {
	public void sortArray(T[] v) {
		T minx;
		int minj;
		for (int i = 0; i < v.length - 1; i++) {
			minj = i;
			minx = v[i];
			for (int j = i + 1; j < v.length; j++) {
				if (v[j].less(minx)) {
					minj = j;
					minx = v[j];
				}
			}
			v[minj] = v[i];
			v[i] = minx;
		}
	}

	public void escribeArray(T[] arr) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + "  ");
		System.out.println();
	}

	public static void main(String[] args) {

		Rational[] arr = { new Rational(3, 4), new Rational(1, 2),
				new Rational(30, 2555), new Rational(35, 50) };
		Fecha[] fec = { new Fecha(25, 12, 2014), new Fecha(7, 11, 2014),
				new Fecha(3, 12, 2015) };

		System.out.println("-- ordenando racionales --");
		SelectionAlgorithm <Rational> s1 = new SelectionAlgorithm<Rational>();	
		s1.sortArray(arr);
		s1.escribeArray(arr);
		System.out.println("-- ordenando fechas --");
		SelectionAlgorithm<Fecha> s2 = new SelectionAlgorithm<Fecha>();
		s2.sortArray(fec);
		s2.escribeArray(fec);

	}
}
