package tema3.excepciones;

public class DPC1 {
	public void división(int num1, int num2) {
		try {
			System.out.println(num1 + " / " + num2 + " = " + (num1 / num2));
		} finally {
			System.out.println("Finally hecho.");
		}
		System.out.println("Volviendo de división.");
	}
	public static void main(String[] args) {
		new DPC1().división(10, 0);
		System.out.println("Volviendo de main.");
	}
}
