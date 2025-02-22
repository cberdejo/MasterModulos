package alturas;

import java.io.IOException;
import java.nio.file.Path;


import java.util.*;

/// @Author: Christian Berdejo
/// @Version 1.0
public class Mundo {
    private List<Pais> paises;

    public Mundo(){
        paises = new ArrayList<>();
    }

    public List<Pais> getPaises() {
        return paises;
    }

    /// Lee el fichero y crea la lista de paises
    /// @param file fichero con la lista de paises
    /// @throws  IOException
    public void leePaises(String file) throws IOException {

        try (Scanner sc = new Scanner(Path.of(file))){
            while(sc.hasNextLine()){
                Optional<Pais> p = stringAPais(sc.nextLine());
                p.ifPresent(pais -> paises.add(pais));
            }

        }

    }

    /// Crea un pais a partir de una linea
    /// @param linea linea del fichero
    /// @return Optional con el pais
    private Optional<Pais> stringAPais(String linea) {


        try (Scanner scLinea = new Scanner(linea)){
            scLinea.useDelimiter("[,]+");
            scLinea.useLocale(Locale.US); //Las comas son con "." no con ","
            String nombrePais = scLinea.next();
            String continente = scLinea.next();
            double altura = scLinea.nextDouble();
            return Optional.of( new  Pais(nombrePais, continente, altura));

        } catch (NoSuchElementException e) {
            //No se especifica nada de excepciones, por lo que la lina no es válida, se ignora y ya.

            return Optional.empty();
        }


    }
    /// Filtra los paises segun el criterio
    /// @param seleccion criterio de seleccion
    public List<Pais> selecciona(Seleccion seleccion) {

        return paises.stream().filter(seleccion::test).toList();
    }

}
