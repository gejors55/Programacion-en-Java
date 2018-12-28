package ejercicio3;

//public class main {
//
//	public static void main(String arg[]) {
//		A a = new A(12);
//		B b = new B();
//		b.q(a);
//		b.p(a);
//		System.out.println(a);
//		///muestra 13 porque cuando en b.q(a) crea un objeto con 3 
//		//y lo incrementa ese objeto no vuelve a main, en main sigue el 12
//	}
//
//}
public class main{
	public static void main(String arg[]){
		B b1 = new B();//Bien
		F f1 = new F();//Bien
		F f2 = new F(2);//NO esta bien
		C c1 = new C();//NO a no tiene constructor vacio
		C c2 = new C(3);//NO super tiene que ir primero en el construccion
	}
	
	
}
