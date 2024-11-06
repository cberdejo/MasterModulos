package tema4;

import tema4.clases.Persona;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainAS2 {
		public static void main(String[] args) {
		String datos = 
		"Juan García,23.Pedro González:,15.Luisa López:19.Andrés Molina,-22";
		try (Scanner sc = new Scanner(datos)) {
			sc.useDelimiter("[.]"); // un punto una o más veces
			while (sc.hasNext()) {
				String datoPersona= sc.next();
				try (Scanner scPersona = new Scanner(datoPersona)) {
					scPersona.useDelimiter("[,:-]+");
					// , : o – una o más veces
					String nombre = scPersona.next();
					int edad = scPersona.nextInt();
					Persona persona = new Persona(nombre, edad);
					System.out.println(persona);
				} catch (InputMismatchException e) {
					System.err.println("Hay un dato que no es entero en " + datoPersona);
				} catch (NoSuchElementException e) {
					System.err.println("Faltan datos en " + datoPersona);
				}
			}
		}
	}
}
