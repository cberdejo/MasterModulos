package tema4io;
/**
 * Created by pacog on 15/4/15.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class MuestraNumLineaFile {
    public static void main(String[] args) {
        // leer el fichero de palabras y mostrarlas en pantalla línea fst línea
        try {
            for (String linea : Files.readAllLines(Path.of(args[0]))) {
                int numLinea = 1;
                System.out.println(numLinea + "\t:" + linea);
                numLinea++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR: falta el getNombre del fichero");
        } catch (IOException e) {
            System.out.println("ERROR: no se puede leer del fichero");
        }
    }
}
