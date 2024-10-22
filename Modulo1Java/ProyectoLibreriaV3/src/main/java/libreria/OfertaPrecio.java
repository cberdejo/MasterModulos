package libreria;

public class OfertaPrecio implements OfertaFlex {

    private double porDescuento;
    private double umbralPrecio;

    public OfertaPrecio(double porDescuento, double umbralPrecio) {
        if (porDescuento < 0 || umbralPrecio < 0) throw new IllegalArgumentException();
        this.porDescuento = porDescuento;
        this.umbralPrecio = umbralPrecio;
    }

    @Override
    public double getDescuento(Libro libro) {
       return libro.getPrecioFinal() < umbralPrecio? porDescuento : 0;
    }

    @Override
    public String toString() {
        return porDescuento+"% ("+umbralPrecio+")";
    }
}
