package indices;

import java.util.*;
import java.util.stream.IntStream;
///@Author Christian Berdejo

///@Version 1.0
public class Indice1aLinea extends Indice {
    private Map<String, Integer> palabras;

    public Indice1aLinea() {
        super();
        palabras = new TreeMap<>(); //TreeMap usa por defecto el orden natural
    }



    /// Busca palabras significativas y apunta en la linea en la que aparece por primera vez
    /// @param delimitadores delimitadores de palabra
    /// @param noSignificativas lista de palabras que se consideran no significativas

    @Override
    public void resolver(String delimitadores, Collection<String> noSignificativas) {

        for (int numLinea = 1; numLinea <= getTexto().size(); numLinea++) {
            String linea = getTexto().get(numLinea - 1);
            try (Scanner sc = new Scanner(linea)) {
                sc.useDelimiter(delimitadores);

                while (sc.hasNext()) {
                    String palabra = sc.next();
                    if (noSignificativas.stream().noneMatch(noS -> noS.equalsIgnoreCase(palabra))
                        && !palabras.containsKey(palabra.toLowerCase())
                    ) {
                        palabras.put(palabra.toLowerCase(), numLinea);
                    }
                }
            } catch (NoSuchElementException e) {
                System.err.println("Error en la linea " + numLinea);

            }

        }
    }

    ///Imprime el indice en consola
    @Override
    public void presentarIndiceConsola() {
        int maxKeyLength = palabras.keySet().stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);


        palabras.forEach((key, value) -> {
            //"%-10s" hace que la cadena ocupe 10 caracteres. Para que queden alineados, se calcula previamente la palabra mas larga
            String formattedKey = String.format("%-" + maxKeyLength + "s", key);
            System.out.println(formattedKey + "   " + value);
        });

    }
    ///Limpia la estructura de datos e inserta una linea
    @Override
    public void agregarLinea(String texto) {
        palabras.clear();
        super.agregarLinea(texto);
    }
}
