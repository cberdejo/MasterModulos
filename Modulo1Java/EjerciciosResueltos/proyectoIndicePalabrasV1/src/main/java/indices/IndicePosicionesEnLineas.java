package indices;

import java.util.*;

public class IndicePosicionesEnLineas extends Indice {
    private Map<String, Map<Integer,Set<Integer>>> palabras;

    public IndicePosicionesEnLineas() {
        super();
        palabras = new TreeMap<>(); //TreeMap usa por defecto el orden natural
    }

    /// Registra las posiciones de cada palabra significativa en el texto(linea y  posicion en la linea), ignorando palabras no significativas.
    /// @param delimitadores delimitadores de palabra
    /// @param noSignificativas lista de palabras que se consideran no significativas

    @Override
    public void resolver(String delimitadores, Collection<String> noSignificativas) {

        for (int numLinea = 1; numLinea <= getTexto().size(); numLinea++) {
            String linea = getTexto().get(numLinea - 1);
            try (Scanner sc = new Scanner(linea)) {
                sc.useDelimiter(delimitadores);

                int numPalabra = 1;
                while (sc.hasNext()) {
                    String palabra = sc.next();
                    if (noSignificativas.stream().noneMatch(noS -> noS.equalsIgnoreCase(palabra)) ) {

                        Map<Integer, Set<Integer>> map = palabras.computeIfAbsent(palabra.toLowerCase(), k -> new TreeMap<>());
                        Set<Integer> set = map.computeIfAbsent(numLinea, k -> new TreeSet<>());

                        set.add(numPalabra);

                        palabras.put(palabra.toLowerCase(), map);

                    }
                    numPalabra++;
                }
            } catch (NoSuchElementException e) {
                System.err.println("Error en la linea " + numLinea);

            }

        }
    }

    ///Limpia la estructura de datos e inserta una linea
    @Override
    public void agregarLinea(String texto) {
        palabras.clear();
        super.agregarLinea(texto);
    }

    /// Imprime el indice en consola
    @Override
    public void presentarIndiceConsola() {



        palabras.forEach((key, mapValue) -> {
            System.out.println(key);

            mapValue.forEach((linea, posicion) -> {
                String posicionValores = posicion.toString()
                        .replace("[", "")
                        .replace("]", ".")
                        .replace(",", ".")
                        .replace(" ", "");
                String spaces = "          ";
                System.out.println(spaces+ linea + "   " + posicionValores);
            });

        });


    }
}
