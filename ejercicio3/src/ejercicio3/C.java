package ejercicio3;

public class C extends A {
	public C() {
		System.out.println("C.");
	}

	public C(int a) {
		int x = a * a;
		super(x);
		System.out.println("C: " + a);
	}
}
