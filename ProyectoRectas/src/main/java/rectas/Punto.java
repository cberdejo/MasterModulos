package rectas;

public record Punto (double x, double y) {
    public Punto() {
        this(0,0);
    }
    public double distancia (Punto pto){
        return 0;
    }
}
