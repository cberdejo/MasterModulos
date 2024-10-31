package tema4io;

import java.io.IOException;
import java.nio.file.*;
public class LeeConFiles {
    public static void main(String[] args) throws IOException {
        String fichero = "data/tema4io/personas.txt";
        for (String linea: Files.readAllLines(Path.of(fichero)))
            System.out.println(linea);
    }
}
