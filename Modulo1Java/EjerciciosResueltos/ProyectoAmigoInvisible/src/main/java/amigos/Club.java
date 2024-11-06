package amigos;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;

/// @Author: Christian Berdejo Sánchez
/// @Version 1.0
public class Club {
    protected Set<Persona> socios;
    private static Random alea = new Random();

    public Club() {
        socios = new TreeSet<>();
    }

    /// Lee el fichero y añade los socios al `Club`
    /// @param  fEntrada fichero de entrada
    /// @param  delim delimitador
    /// @throws  IOException
    public void  lee(String fEntrada, String delim) throws IOException {
        try (Scanner sc = new Scanner(Path.of(fEntrada))){
            while(sc.hasNextLine()){
                leeSocios(sc.nextLine(), delim);

            }

        }
    }

    /// Lee una linea  y añade socios según el delimitador pasado
    /// @param linea linea del fichero
    /// @param delim delimitador
    /// @return Optional con el pais
    private void leeSocios(String linea, String delim) {

        try (Scanner scLinea = new Scanner(linea)){
            scLinea.useDelimiter( "["+delim+"]+");
            creaSocioDesdeString(scLinea.next());
        } catch (NoSuchElementException e) {
            //No se especifica nada de excepciones, por lo que la linea no es válida, se ignora y ya.
        }

    }

    /// Crea un socio a partir de una cadena
    /// @param  nombre nombre del socio
    protected void creaSocioDesdeString(String nombre){
        socios.add(new Persona(nombre));

    }

    ///De forma aleatoria, se asigna a cada persona un amigo invisible.
    protected void hacerAmigos(){
        List<Integer> posAmigos = new ArrayList<>();
        for (int i = 0; i < socios.size(); i++){
            posAmigos.add(i);
        }

        do{
        Collections.shuffle(posAmigos);

        } while(coincidePosicion(posAmigos));

        List<Persona> listaSocios = socios.stream().toList();
        //Continuar

    }

    ///Devuelve verdadero si la lista de posiciones de amigos coincide con la lista de posiciones de socios
    private boolean coincidePosicion(List<Integer> posAmigos) {
        for (int i = 0; i < socios.size(); i++){
            if (posAmigos.get(i) == i){
                return true;
            }
        }
        return false;
    }
}
