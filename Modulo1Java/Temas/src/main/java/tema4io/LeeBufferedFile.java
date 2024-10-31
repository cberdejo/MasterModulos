package tema4io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class LeeBufferedFile {
    public static void main(String[] args) throws IOException {
        String fichero = "data/tema4io/personas.txt";
        try (BufferedReader br =Files.newBufferedReader(Path.of(fichero))) {
            String linea = br.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = br.readLine();
            }
        }
    }
}

