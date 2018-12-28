public class A {
	public void p(int n) {
		if (n != 0)
			throw new ExcepB();
		else
			throw new ExcepA();
	}

	public void q() {
		try {
			p(2);
			p(0);
		} catch (ExcepA e) {
			System.out.println("Excepcion A capturada en q");
		} catch (ExcepB e) {
			System.out.println("Excepcion B capturada en q");
			p(0);
		} finally {
			System.out.println("Ejecutamos el finally de q");
		}
		System.out.println("Termina la ejecucion de q");
	}

	public void h() {
		try {
			q();
			p(0);
		} catch (ExcepA e) {
			System.out.println("Excepcion A capturada en h");
		} catch (ExcepB e) {
			System.out.println("Excepcion A capturada en h");
		} finally {
			System.out.println("Ejecutamos el finally de h");
		}
		System.out.println("Termina la ejecucion de h");
	}
}