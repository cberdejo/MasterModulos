package wordle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class Wordle {
    private static Random alea = new Random();
    private static Set<String> validas;
    private String secreta;

    public Respuesta intento(String palabra) {

    }

    public static void leePalabras(String fichero) throws IOException {

        try (Stream<String> ln = Files.lines(Path.of(fichero))) {

        }

    }

    private String comienza(){
        //palabra aleatoria entre valida


    }

    public String getSecreta() {
        return null;
    }
}