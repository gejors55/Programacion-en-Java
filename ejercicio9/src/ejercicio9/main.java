package ejercicio9;

public class main {

	public static void main(String arg[] ) {
		Padre p1 = new Hija1();
		Nieta1 n1 = new Nieta1();
		Padre p2 = new Nieta2();
		Hija2 h1 = new Nieta2();
		
		p1.p(); // Hija1 p
		p1.q(); //Padre q
		n1.q();//nieta1 q
		n1.p();//hija1 p
		p2.h();//no hay h
		p2.q();//padre q
		p2.p();//nieta2 p
		h1.r();//no tiene hija 2
		h1.p();//nieta2 p
		h1.q();//padre q
	}

}
