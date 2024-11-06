package tema2.h1;

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
}

class Cuatro extends Tres {
	public int test() { return 4; }
}

public class Prueba1 {
	public static void main(String[] args) {
		Tres obj3 = new Tres();
		Cuatro obj4 = new Cuatro();
		System.out.println("obj3.test()       = " + obj3.test());
		System.out.println("obj3.resultado2() = " + obj3.resultado2());
		System.out.println("obj3.resultado3() = " + obj3.resultado3());
		System.out.println("obj4.resultado1() = " + obj4.resultado1());
		System.out.println("obj4.resultado2() = " + obj4.resultado2());
		System.out.println("obj4.resultado3() = " + obj4.resultado3());
	}
}
