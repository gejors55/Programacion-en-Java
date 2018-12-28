package clasesInternas;

public class Ejemplo3 {

	public static void main(String[] s) {
		OuterClass x = new OuterClass();
		OuterClass.InnerClass1 a = x.new InnerClass1();
		OuterClass.InnerClass1.InnerClass2 b = a.new InnerClass2();
		a.test();
		b.test();
	}

}
