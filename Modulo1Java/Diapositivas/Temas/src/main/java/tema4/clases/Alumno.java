package tema4.clases;

public class Alumno {
	private String nombre;
	private double nota;

	public Alumno(String n, double e) {
		nombre = n;
		nota   = e;
	}

	public boolean equals(Object o) {
		return  o instanceof Alumno p &&
			   (nota == p.nota) &&
			   (p.nombre.equalsIgnoreCase(nombre));
	}
	
	public int hashCode() {
		return nombre.toUpperCase().hashCode() + Double.hashCode(nota);
	}

	
	public String toString() {
		return "Alumno(" + nombre+ "," + nota + ")";
	}
}
