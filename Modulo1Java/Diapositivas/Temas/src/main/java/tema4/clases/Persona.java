package tema4.clases;

import java.util.Objects;

public class Persona {
	private String nombre;
	private int edad;

	public Persona(String n, int e) {
		nombre = n;
		edad   = e;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Persona p &&
			   (edad == p.edad) &&
			   (p.nombre.equalsIgnoreCase(nombre));
	}

	/*
	public int hashCode() {
		return nombre.toLowerCase().hashCode() + Integer.hashCode(edad);
	}
	 */
	public int hashCode() {
		return Objects.hash(nombre.toLowerCase(),edad);
	}

	public String toString() {
		return "Persona(" + nombre+ "," + edad + ")";
	}
}
