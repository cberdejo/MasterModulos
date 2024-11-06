import wordle.Fallo;
import wordle.Movimiento;
import wordle.Respuesta;
import wordle.WordleM;
import wordle.Wordle;

import java.io.IOException;
import java.util.Scanner;


public class MainWordle {
    public static void main(String[] args) {
        try {
            Wordle.leePalabras("data/palabras.txt");
        } catch (IOException e) {
            System.out.println("No se encuentra el fichero de palabras");
            System.exit(1);
        }
        Wordle wordle = new WordleM(); // new Wordle()
        boolean fin = false;
        int numJugada = 1;
        Scanner sc = new Scanner(System.in);
        while (!fin) {
            System.out.print("Jugada " + numJugada + ": ");
            String jugada = sc.nextLine();
            Respuesta omov = wordle.intento(jugada);
            switch (omov) {
                case Fallo(String msg):
                    System.out.println(msg);
                    break;
                case Movimiento(String palabra, String respuesta):
                    System.out.println("   " + palabra + ": " + respuesta);
                    if (wordle.getSecreta().equals(palabra)) {
                        fin = true;
                        System.out.println("Acertaste en " + numJugada + "jugadas");
                    } else if (numJugada == 6) {
                        fin = true;
                        System.out.println("Fracasaste. Era "+ wordle.getSecreta());
                    }
                    numJugada += 1;
            }
        }
    }
}

