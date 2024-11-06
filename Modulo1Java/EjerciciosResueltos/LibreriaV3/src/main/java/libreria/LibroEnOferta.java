package libreria;

public class LibroEnOferta extends Libro{
    private double porDescuento;


    /// Constructor de un libro en oferta.
    /// @param autor del libro
    /// @param titulo del libro
    /// @param precioBase precio base del libro
    /// @param descuento porcentaje de descuento. Debe ser mayor o igual a 0.
    /// @throws IllegalArgumentException si `descuento` es negativo

    public LibroEnOferta(String autor, String titulo, double precioBase, double descuento) {
        super(autor, titulo, precioBase);

        if (descuento < 0) throw new IllegalArgumentException();
        porDescuento = descuento;
    }



     /// @return el porcentaje de descuento
    public double getDescuento() {
        return porDescuento;
    }

    /// Calcula el precio final aplicando el descuento
    @Override
    public double getPrecioFinal() {
        double precioFinalSinDescuento = super.getPrecioFinal();
        return precioFinalSinDescuento-precioFinalSinDescuento*porDescuento/100;

    }

    @Override
    public String toString() {
        return "("+getAutor()+";"
                + getTitulo()+";"
                + getPrecioBase()+";"
                + porDescuento+"%;"
                + (getPrecioBase()*porDescuento/100)+";"
                + IVA +"%;"
                + getPrecioFinal()+")";
    }

}
