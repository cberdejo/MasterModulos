package libreria;

import java.util.Arrays;

///@Author Christian Berdejo
/// @Version 1.0
public class Libreria {
    private static final int TAM_DEFECTO=16;
    private Libro[] libros;
    private int numLibros;

    ///Crea una librería con el tamaño por defecto
    public Libreria(){
        this(TAM_DEFECTO);
    }

    ///Crea una librería con un tamanio inicial
    ///@param tam tamaño inicial de la libreria
    public Libreria(int tam){
        if (tam < 0) {
            throw new IllegalArgumentException("El tamanio no puede ser negativo");
        }
        libros = new Libro[tam];
        numLibros = 0;
    }

    ///Elimina un libro de la librería
    ///@param autor autor del libro
    ///@param titulo titulo del libro
    public void remLibro(String autor, String titulo){
        int pos = posicionLibro(autor,titulo);
        if (pos < 0)
            throw new IllegalArgumentException("El libro no existe");

        for (int i = pos; i < numLibros-1; i++) {
            libros[i] = libros[i+1];
        }
        numLibros--;
    }

    /// Inserta un libro o edita su precio si ya existe a `this` dado autor, titulo y precio
    /// @Param autor autor del libro
    /// @Param titulo titulo del libro
    /// @Param precio precio base del libro
    ///
    public void addLibro( String autor, String titulo, double precio){
        //Si precio es <0, se lanza la excepción al crear el libro
        addLibro( new Libro(autor, titulo, precio));
    }

    ///Añade un libro a la librería
    ///@param autor autor del libro
    ///@param titulo t titulo del libro
    ///@param precio precio base del libro
    /// @return el precio base de un libro perteneciente `this` dado un autor y un título
    /// @throws IllegalArgumentException si el libro no existe.
    public double getPrecioBase(String autor, String titulo){
        int pos = posicionLibro(autor,titulo);
        if (pos < 0)
            throw new IllegalArgumentException("El libro no existe");
        return  libros[pos].getPrecioBase();
    }

    ///Añade un libro a la librería
    ///@param autor autor del libro
    ///@param titulo titulo del libro
    ///@param precio precio base del libro
    /// @return el precio final de un libro perteneciente `this` dado un autor y un título
    /// @throws IllegalArgumentException si el libro no existe.
    public double getPrecioFinal(String autor, String titulo){
        int pos = posicionLibro(autor,titulo);
        if (pos < 0)
            throw new IllegalArgumentException("El libro no existe");
        return  libros[pos].getPrecioFinal();
    }

    private void addLibro(Libro libro){
        int pos = posicionLibro(libro.getAutor(), libro.getTitulo());
        if (pos < 0) {
            ensureArrayCapacity();
            libros[numLibros] = libro;
            numLibros++;
        }else {
            libros[pos] = libro;
        }

    }

    ///Duplica la capacidad del array si es necesario
    private void ensureArrayCapacity() {
        if (numLibros == libros.length)
            libros = Arrays.copyOf(libros, libros.length * 2);

    }
    ///Busca la posición de un libro en el `array`
    /// - En caso de no encontrar el libro, se devuelve `-1`
    /// - En caso de encontrar el libro, se devuelve la posición
    /// @return la posición del libro
    private  int posicionLibro(String autor, String titulo){
        int currentPos = 0;
        while (currentPos < numLibros && !librosIguales(libros[currentPos], new Libro(autor, titulo,0))) {
            currentPos++;
        }

        return currentPos >= numLibros ? -1 : currentPos;
    }
    ///Se devuelve si dos libres son iguales, e considera que dos libres son iguales si el titulo y el autor son iguales sin tener en cuenta los mayúsculas y minúsculas
    ///@return true si son iguales, false en caso contrario
    private boolean librosIguales(Libro libro1, Libro libro2){
        return (libro1.getAutor().equalsIgnoreCase(libro2.getAutor()) && libro1.getTitulo().equalsIgnoreCase(libro2.getTitulo()));
    }

    @Override
    public String toString() {
        return String.format("L(" + libros+")");
    }


}
