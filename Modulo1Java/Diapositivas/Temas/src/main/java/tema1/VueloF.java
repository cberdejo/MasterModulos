package tema1;

public class VueloF  {
	   
	static private int sigVuelo = 1;    // De clase
	private String localizadorVuelo;    // De instancia

	static private int nuevoIdentificador() {// De clase
		int vuelo = sigVuelo;
		sigVuelo++;
		return vuelo;
	}
	private VueloF(String lin) {
		localizadorVuelo = lin + "_" + VueloF.nuevoIdentificador();
	}
	
	static public VueloF creaVuelo(String lin) { // Método factoría
		return new VueloF(lin);
	}
	public String toString() {
		return localizadorVuelo;
	}
	
}
