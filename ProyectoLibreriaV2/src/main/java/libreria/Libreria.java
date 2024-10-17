package libreria;

import java.util.List;
import java.util.ArrayList;

/// @author Fco. Gutierrez
/// @version 1.0
public class Libreria {
    private List<Libro> libros;
    private static final int TAM_DEFECTO = 16;

    /// Crea una libreria
    public Libreria() {
        libros = new ArrayList<>();
    }


    /// Agrega un libro a la libreria
    /// - Si ya está se sustituye
    /// -Si ya está no se añade
    /// @param autor del libro
    /// @param titulo del libro
    /// @param precioBase precio base del libro
    public void addLibro(String autor, String titulo, double precioBase) {
        Libro libro = new Libro(autor, titulo, precioBase);
        addLibro(libro);
    }

    /// Agrega un `libro` a la lista de libros
    /// - Si ya está se sustituye
    /// - Si no está se añade
    /// @param libro a agregar
    private void addLibro(Libro libro) {
        int pos = posicionLibro(libro.getAutor(), libro.getTitulo());
        if (pos < 0) {

            libros.add(libro);
        } else
            libros.set(pos, libro);
    }

    /// Localiza la posición de un libro con `autor` y `titulo` dado
    /// en la lista de libros.
    /// @return la posición del libro en el array. -1 si no está
    private int posicionLibro(String autor, String titulo) {
        int pos = 0;
        while (pos < libros.size() &&
                (!libros.get(pos).getTitulo().equalsIgnoreCase(titulo)
                || !libros.get(pos).getAutor().equalsIgnoreCase(autor)))
            pos++;
        return (pos == libros.size())? -1 : pos;
    }


    /// Calcula el precio base de un libro conocido `autor` y `título`
    /// @param autor  del libro
    /// @param titulo del libro
    /// @return el precio base del libro
    public double getPrecioBase(String autor, String titulo) {
        int pos = posicionLibro(autor, titulo);
        return (pos < 0)? 0 : libros.get(pos).getPrecioBase();
    }

    /// Calcula el precio final de un libro conocido `autor` y `título`
    /// @param autor  del libro
    /// @param titulo del libro
    /// @return el precio final del libro
    public double getPrecioFinal(String autor, String titulo) {
        int pos = posicionLibro(autor, titulo);
        return (pos < 0)? 0 : libros.get(pos).getPrecioFinal();
    }

    /// Elimina un libro de la librería conocido `autor` y `titulo`.
    /// Se debe mantener el orden de los libros.
    /// Si el libro con ese ´autor` y `titulo` no existe no se hace nada.
    /// @param autor  del libro
    /// @param titulo del libro
    public void remLibro(String autor, String titulo) {
        int pos = posicionLibro(autor, titulo);
        if (pos >= 0)
            libros.remove(pos);

    }

    @Override
    public String toString() {
        return libros.toString();
    }
}
