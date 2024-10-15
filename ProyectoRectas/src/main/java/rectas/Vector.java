package rectas;

public record  Vector (Punto extremo) {

    private static final double EPSILON = 0.000001;
    public Vector (double x, double y) {
        this(new Punto(x,y));
    }

    public Vector (Punto origen, Punto extremo) {

        this(extremo.x() - origen.x(), extremo.y() - origen.y());
    }
}
