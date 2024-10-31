package tema4;

import java.util.Scanner;

public class MainIn {
	public static void main(String[] args) {
		// ------ Uso de la clase Scanner ------------------
		System.out.print("Introduce tu primer" + " apellido y tu getEdad: ");
		try (Scanner sc = new Scanner(System.in)) {
			String apellido = sc.next();
			int edad = sc.nextInt();
			System.out.println("Datos leídos:");
			System.out
					.println("Apellido: " + apellido + "\t" + "Edad: " + edad);
		}
	}
}
