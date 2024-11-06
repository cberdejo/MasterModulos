package tema4;

class EjPf {
	  static public void main(String[] args) {
		  String s = String.format("El objeto %20s con %d", new A(65), 78);
		 System.out.println(s);	
		 System.out.printf(
		   "Cadena %40s\nEntero %15d\nFlotante %8.2f\nLógico %snd\n",
		   "Esto es una cadena", 34, 457.2345678, 3 == 3);
	  }
}

class A {
   int a;

   public A(int s) {
	a = s;
   }

   public String toString() {
	return "A[" + a + "]";
   }
}
