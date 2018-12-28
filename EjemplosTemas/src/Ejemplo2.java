class Externa2 {
	private int a1 = 1;

	public Externa2(int i) {
		a1 = i;
	}

	public Interna m1() {
		Interna i = new Interna(a1 * a1);
		System.out.println("accedo al a2 de la internadesde la externa: " + i.a2);
		return i;

	}

	public String toString() {
		return "a1=" + a1;
	}

	public class Interna {
		private int a2 = 6;

		public Interna(int i) {
			a2 = i;
		}

		public String toString() {
			return "a1=" + a1 + " a2=" + a2;
		}
	}
}

public class Ejemplo2 {
	public static void main(String[] s) {
		Externa2 ex1 = new Externa2(2);
		Externa2 ex2 = new Externa2(3);
		Externa2.Interna in1 = ex1.m1();
		Externa2.Interna in2 = ex2.m1();
		Externa2.Interna in3 = ex2.new Interna(4);
		System.out.println(in1);
		System.out.println(in2);
		System.out.println(in3);
	}
}
