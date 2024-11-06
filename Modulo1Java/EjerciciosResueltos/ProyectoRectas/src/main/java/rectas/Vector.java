package rectas;

public record Vector(Punto extremo) {

    private static final double EPSILON = 0.000001;

    /**
     * Constructor que inicializa un vector desde el origen (0,0) hasta el punto
     * (x, y).
     *
     * @param x Coordenada x del extremo del vector.
     * @param y Coordenada y del extremo del vector.
     */
    public Vector(double x, double y) {
        this(new Punto(x, y));
    }

    /**
     * Constructor que crea un vector desde un punto origen hasta un punto extremo.
     *
     * @param origen  Punto de origen del vector.
     * @param extremo Punto de extremo del vector.
     */
    public Vector(Punto origen, Punto extremo) {
        this(new Punto(extremo.x() - origen.x(), extremo.y() - origen.y()));
    }

    /**
     * Suma el vector actual con otro vector dado.
     *
     * @param v El vector a sumar.
     * @return Un nuevo vector resultado de la suma.
     */
    public Vector sum(Vector v) {
        return new Vector(this.extremo.x() + v.extremo.x(), this.extremo.y() + v.extremo.y());
    }

    /**
     * Retorna un vector ortogonal (rotado 90 grados en sentido antihorario).
     *
     * @return Un nuevo vector ortogonal al actual.
     */
    public Vector ortogonal() {
        return new Vector(-extremo.y(), extremo.x());
    }

    /**
     * Verifica si el vector actual es paralelo a otro vector dado.
     *
     * @param otro El vector a comparar.
     * @return `true` si los vectores son paralelos, `false` en caso contrario.
     */
    public boolean esParalelo(Vector otro) {
        return Math.abs(this.extremo.x() * otro.extremo.y() - this.extremo.y() * otro.extremo.x()) < EPSILON;
    }

    /**
     * Calcula y retorna el módulo del vector.
     *
     * @return El módulo (longitud) del vector.
     */
    public double modulo() {
        return Math.sqrt(extremo.x() * extremo.x() + extremo.y() * extremo.y());
    }

    /**
     * Retorna un vector unitario en la misma dirección y sentido del vector actual.
     * Lanza una RuntimeException si el módulo es cero.
     *
     * @return Un vector unitario en la dirección del vector actual.
     */
    public Vector dirección() {
        double modulo = modulo();
        if (modulo == 0) {
            throw new RuntimeException("El módulo del vector es cero, no se puede definir dirección.");
        }
        return new Vector(extremo.x() / modulo, extremo.y() / modulo);
    }

    /**
     * Calcula el extremo del vector cuando el origen se coloca en un punto dado.
     *
     * @param org El punto de origen desde el cual calcular el nuevo extremo.
     * @return Un nuevo punto que representa el extremo del vector desde el origen
     *         dado.
     */
    public Punto extremoDesde(Punto org) {
        return new Punto(org.x() + extremo.x(), org.y() + extremo.y());
    }

    /**
     * Representación en cadena del vector.
     *
     * @return Una cadena que describe el vector en formato "(x, y)".
     */
    @Override
    public String toString() {
        return "Vector(" + extremo.x() + ", " + extremo.y() + ")";
    }
}
