package ejercicio3;

public class F extends B {
	public F() {
		System.out.println("F.");
	}

	public F(int a) {
		super(a);
		System.out.println("F: " + a);
	}
}