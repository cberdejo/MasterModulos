import urna.Urna;

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        try {
            int nb = Integer.parseInt(args[0]);
            int nn = Integer.parseInt(args[1]);
            Urna urna = new Urna(nn, nb);
            System.out.println(urna);

            while (urna.totalBolas() > 1) {
                Urna.ColorBola color1 = urna.extraerBola();
                Urna.ColorBola color2 = urna.extraerBola();
                if (color1 == color2) {
                    urna.ponerBlanca();
                } else {
                    urna.ponerNegra();
                }
            }

            System.out.println("La última bola es de color: " + urna.extraerBola());
            /*
                Parece ser que si hay mas bolas negras que blancas en la urna, la ultima saldrá siempre negra.
                En caso de que haya el mismo número o mayor de bolas blancas que negras, la bola extraida final siempre será blanca.
             */
        } catch (IllegalArgumentException e) {
            System.err.println("Escribe un número mayor que 0 para las urnas: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.err.println("No hay más bolas para extraer.");
        }
    }

}