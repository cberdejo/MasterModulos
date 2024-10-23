package libreria;
/// @author Fco. Gutierrez
/// @version 1.0
///
public class Libro {
    private String titulo;
    private String autor;
    private double precioBase;
    protected static double IVA = 10;

    /// Crea un libro
    /// @param au author
    /// @param ti titulo
    /// @param pb Precio base
    public Libro(String au, String ti, double pb) {
        if (pb < 0) throw new IllegalArgumentException();
        autor = au;
        titulo = ti;
        precioBase = pb;
    }

    /// @return titulo
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public double getPrecioBase() {
        return precioBase;
    }
    /// Calcula el precio con IVA
    public double getPrecioFinal() {
        return precioBase + precioBase*IVA/100;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Libro l &&
                l.autor.equalsIgnoreCase(autor) && l.titulo.equalsIgnoreCase(titulo);
    }

    @Override
    public int hashCode() {
        return autor.toLowerCase().hashCode() + titulo.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return "(" + autor + ";" + titulo + ";" +
                precioBase + ";" + IVA + ";" +
                getPrecioFinal()+")";
    }
}
