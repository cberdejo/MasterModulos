package libreria;
///@Author Christian Berdejo
/// @Version 1.0
public class Libro {
    private String titulo;
    private String autor;
    private double precioBase;

    private static final double IVA = 0.1;

    ///Crea un libro
    /// @param autor autor del libro
    /// @param titulo titulo del libro
    /// @param precio precio del libro
    public Libro(String autor, String titulo, double precio) {

        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }

        this.titulo = titulo;
        this.autor = autor;
        this.precioBase = precio;
    }


    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public double getPrecioBase() {
        return precioBase;
    }
    ///Calcula el precio final del libro, multiplica por el iva
    /// @return precio final
    public double getPrecioFinal(){
        return precioBase + (precioBase * IVA);
    }
    @Override
    public String toString(){
        return String.format("%s; %s; %.3f; %.3f", autor, titulo, precioBase, getPrecioFinal());
    }
}
