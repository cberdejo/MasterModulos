package tema1;

public class Segmento {
	  private Punto origen, extremo;
	  public Segmento(double x1, double y1, double x2, double y2) { 
	  	  origen = new Punto(x1, y1);
		  extremo = new Punto(x2, y2);
	  }

	  //... // Otros métodos

	  public double longitud() {
	  	  return origen.distancia(extremo);
	  }
}
