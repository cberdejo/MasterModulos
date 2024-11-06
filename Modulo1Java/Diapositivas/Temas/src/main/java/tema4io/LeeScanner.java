package tema4io;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class LeeScanner {
    public static void main(String[] args) throws IOException {
        String fichero = "data/tema4io/personas.txt";
        try (Scanner sc = new Scanner(Path.of(fichero))) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        }
    }
}
