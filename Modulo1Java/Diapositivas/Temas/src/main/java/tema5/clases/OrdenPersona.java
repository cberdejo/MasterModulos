package tema5.clases;

import java.util.Comparator;

public class OrdenPersona implements Comparator<Persona> {
	// Se comparan por nombres, y fst igualdad de nombres, por getEdad
	public int compare(Persona p1, Persona p2) {
		int resultado = p1.getNombre().compareTo(p2.getNombre());
		if (resultado == 0) {
			resultado = Integer.compare(p1.getEdad(), p2.getEdad());
		} 
		return resultado;
	}
}
