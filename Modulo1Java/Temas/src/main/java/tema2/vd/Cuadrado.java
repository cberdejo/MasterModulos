package tema2.vd;

public class Cuadrado extends Poligono {
    private double lado;

    public Cuadrado(double l) {
        lado = l;
    }

    @Override
    public double perimetro() {
        return 4*lado;
    }
}
