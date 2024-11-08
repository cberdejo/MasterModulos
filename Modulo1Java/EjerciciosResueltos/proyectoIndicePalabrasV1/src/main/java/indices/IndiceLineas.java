package indices;

import java.util.*;
///@Author Christian Berdejo
///@Version 1.0
public class IndiceLineas extends Indice {
    private Map<String, Set<Integer>> palabras;

    public IndiceLineas() {
            super();
            palabras = new TreeMap<>(); //TreeMap usa por defecto el orden natural
    }


    /// Apunta en las lineas en las que aparece una palabra significativa.
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
                    if (noSignificativas.stream().noneMatch(noS -> noS.equalsIgnoreCase(palabra)) ) {

                        Set<Integer> set = palabras.computeIfAbsent(palabra.toLowerCase(), k-> new TreeSet<>());
                        set.add(numLinea);
                        palabras.put(palabra.toLowerCase(), set);

                    }
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

    @Override
    public void presentarIndiceConsola() {
        int maxKeyLength = palabras.keySet().stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);


        palabras.forEach((key, value) -> {
            String valores = value.toString()
                    .replace("[", "")
                    .replace("]", ".")
                    .replace(",", ".")
                    .replace(" ", "");                 ;
            //"%-10s" hace que la cadena ocupe 10 caracteres. Para que queden alineados, se calcula previamente la palabra mas larga
            String formattedKey = String.format("%-" + maxKeyLength + "s", key);

            System.out.println(formattedKey + "    " + valores);

        });



    }
}
