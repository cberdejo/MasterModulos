package tema3;


import tema3.excepciones.MiExcepcion;

public class EjemploMiEx {
	public void division(int num1, int num2) throws MiExcepcion {
		 if (num2 == 0) {
			throw new MiExcepcion(num1);
		 }
		 System.out.println(num1 + " / " + num2 + " = " + (num1 / num2));
	}
	public static void main(String[] args) {
		 try {
			new EjemploMiEx().division(10, 0);
			System.out.println("División hecha.");
		 } catch (MiExcepcion e) {
			System.out.println("Número " + e.getMessage());
		 } finally {
			System.out.println("Finally hecho.");
		 }
	}
}
