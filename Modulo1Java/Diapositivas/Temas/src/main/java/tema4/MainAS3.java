package tema4;

import tema4.clases.Alumno;

import java.util.Locale;
import java.util.Scanner;

public class MainAS3 {
	public static void main(String[] args) {
		String datos = 
		"Juan García,23.5:Pedro González,15.2:Luisa López,19.1:Andrés Molina,22.7";
		try (Scanner sc = new Scanner(datos)) {
			sc.useDelimiter("[:]+"); // : una o más veces
			while (sc.hasNext()) {
				String datoPersona= sc.next();
				try (Scanner scAlumno = new Scanner(datoPersona)) {
					scAlumno.useDelimiter("[,]+");
					// , una o más veces
					scAlumno.useLocale(Locale.ENGLISH);
					String nombre = scAlumno.next();
					double nota = scAlumno.nextDouble();
					Alumno alumno = new Alumno(nombre, nota);
					System.out.println(alumno);

				}
			}
		}
	}
}
