package libreria;

import java.util.Arrays;

public class LibreriaOferta extends Libreria {
    private double porDescuento;
    private String[] autores;

    /// Crea una libreria con libros que pueden estar en oferta
    /// @param pd porcentaje de descuento
    /// @param autores autores del libro en oferta
    public LibreriaOferta(double pd, String[] autores){
        //super ();

        if (pd < 0) throw new IllegalArgumentException();
        this.porDescuento = pd;
        this.autores = autores;
    }

    /// Cambia el porcentaje de descuento y los autores en oferta
    ///  @param pd porcentaje de descuento
    ///  @param aue autores en oferta
    public void setOferta(double pd, String[] aue){
        if (pd < 0) throw new IllegalArgumentException();
        this.porDescuento = pd;
        this.autores = autores;
    }

    /// @return devuelve los autores con libros en oferta
    public String[] getOferta(){
        return autores;
    }

    /// @return devuelve el porcentaje de descuento
    public double getDescuento() {
        return porDescuento;
    }

    /// Agrega un libro a la libreria teniendo en cuenta si el autor está en oferta
    /// - Si ya está se sustituye
    /// - Si ya está no se añade
    /// @param autor del libro
    /// @param titulo del libro
    /// @param precioBase precio base del libro
    /// @throws IllegalArgumentException si el precio es < 0
    @Override
    public void addLibro(String autor, String titulo, double precioBase) {

        addLibro(esAutorEnOferta(autor)?
                new LibroEnOferta(autor,titulo,precioBase,porDescuento):
                new Libro(autor,titulo,precioBase));

    }

    /// Determina si un autor está en oferta
    /// @param autor del libro
    /// @return true si el autor está en oferta, false en caso contrario
    private boolean esAutorEnOferta(String autor) {
        int pos = 0;
        while (pos < autores.length && !autores[pos].equalsIgnoreCase(autor))
            pos++;
        return (pos < autores.length);
    }

    @Override
    public String toString() {
        return porDescuento+"%"
                + Arrays.toString(autores)+
                super.toString();
    }
}
