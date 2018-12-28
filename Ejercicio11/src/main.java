
public class main {

	public static void main(String arg[]) {
		A a1 = new A(5);// x = 5 a1
		a1.incrementX();// x = 6 a1
		A.incrementY();// y = 11
		A a2 = new A(7);//  x = 7 a2
		a2.incrementX();// x = 8 a2
		A.incrementY();// y =12
		A b = new B(15); // x = 15 b
		b.incrementX();// x = 16 b
		A.incrementY();// y = 13
		System.out.println(a1);// 6 13
		System.out.println(a2);// 8 13
		System.out.println(b);// 16 13
	}

}
