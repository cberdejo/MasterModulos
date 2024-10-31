package tema2.pf;

public record Punto(double x, double y) {
	public Punto() {
		this(0,0);
	}
	public double distancia(Punto pto) {
	    return Math.sqrt(Math.pow(x - pto.x, 2) +
			  Math.pow(y - pto.y, 2)); 
	}
}	
