package rectas;

public record Punto (double x, double y) {
    public Punto() {
        this(0,0);
    }

    /// distancia entre dos puntos
    /// @param pto punto para calcular la distancia
    /// @return distancia
    public double distancia (Punto pto){
        return Math.sqrt(Math.pow(x - pto.x, 2) + Math.pow(y - pto.y, 2));
    }
}
