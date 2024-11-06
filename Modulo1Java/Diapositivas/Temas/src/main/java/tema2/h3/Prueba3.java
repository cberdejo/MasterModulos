package tema2.h3;

class Uno {
	public int test() { return 1; }
	public int resultado1() { 
		return this.test(); 
	}
}

class Dos extends Uno {
	public int test() { return 2; }
}

class Tres extends Dos {
	public int resultado2() { return this.resultado1(); }
	public int resultado3() { return super.test(); }
	public int test() { return 3; }
}

class Cuatro extends Tres {
	public int test() { return 4; }
}

public class Prueba3 {
	public static void main(String [] args) {
		Uno obj3 = new Tres();
		Tres obj4 = new Cuatro();
		System.out.println("obj3.test()       = " + obj3.test());
//		System.out.println("obj3.resultado2() = " + obj3.resultado2());
//		System.out.println("obj3.resultado3() = " + obj3.resultado3());
		System.out.println("obj4.resultado1() = " + obj4.resultado1());
		System.out.println("obj4.resultado2() = " + obj4.resultado2());
		System.out.println("obj4.resultado3() = " + obj4.resultado3());
	}
}
