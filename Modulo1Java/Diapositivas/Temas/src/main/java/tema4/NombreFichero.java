package tema4;

public class NombreFichero {
	private String camino;
	private char separadorCamino, separadorExtension;

	public NombreFichero(String str, char sep, char ext) {
		camino = str;
		separadorCamino = sep;
		separadorExtension = ext;
	}
	public String extension() {	
		int pto = camino.lastIndexOf(separadorExtension);
		return camino.substring(pto + 1);
	}
	public String nombre() {
		int pto = camino.lastIndexOf(separadorExtension);
		int sep = camino.lastIndexOf(separadorCamino);
		return camino.substring(sep + 1, pto);
	}
	public String directorio() { 
		// completar (ejercicio)
		return null;
	}
	
}
