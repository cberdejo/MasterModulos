package tema1;

public class Vuelo  {
	   
	static private int sigVuelo = 1;    // De clase
	private String localizadorVuelo;    // De instancia

	static private int nuevoIdentificador() {// De clase
		int vuelo = sigVuelo;
		sigVuelo++;
		return vuelo;
	}

	public Vuelo(String lin) {
		localizadorVuelo = lin + "_" + Vuelo.nuevoIdentificador();
	}
	
	public String toString() {
		return localizadorVuelo;
	}
}
