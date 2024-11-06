package urna;

import java.util.NoSuchElementException;
import java.util.Random;

/// @Author Christian Berdejo
/// @Version 1.0
public class Urna {
    /// Representa el color de una bola
    static public enum ColorBola {Blanca, Negra}

    ;
    /// Número de bolas negras
    private int bolasNegras;
    /// Número de bolas blancas
    private int bolasBlancas;

    private static Random random;

    /// Crea una urna con un número determinado de bolas negras y blancas.
    ///
    /// @param bolasNegras  el número de bolas negras
    /// @param bolasBlancas el número de bolas blancas
    /// @throws IllegalArgumentException en caso de que `bolasNegras` sea menor que `0` o `bolasBlancas` sea menor que `0`
    public Urna(int bolasNegras, int bolasBlancas) {
        if (bolasNegras < 0 || bolasBlancas < 0) {
            throw new IllegalArgumentException("La urna no puede tener negativas bolas");
        }
        this.bolasNegras = bolasNegras;
        this.bolasBlancas = bolasBlancas;
        this.random = new Random();

    }

    /// Devuelve el número total de bolas  de `this`
    ///
    /// @return el número total de bolas
    public int totalBolas() {
        return bolasBlancas + bolasNegras;
    }

    /// Agrega una bola negra a `this`
    public void ponerNegra() {
        bolasNegras++;
    }

    /// Agrega una bola blanca a `this`
    public void ponerBlanca() {
        bolasBlancas++;
    }


    /// Quita una bola de `this` al azar.
    /// - En caso de solo tener bolas negras devuelve una bola negra
    /// - En caso de tener solo bolas blancas devuelve una bola blanca
    /// - En cualquier otro caso devuelve una bola al azar
    ///
    /// @return el color de la bola obtenida con un el `enum ColorBola {Blanca,Negra}`
    /// @throws NoSuchElementException en caso de que no queden bolas
    public ColorBola extraerBola() {
        if (bolasBlancas == 0 && bolasNegras == 0) throw new NoSuchElementException("No quedan bolas en la urna.");

        if (bolasBlancas > 0 && bolasNegras > 0) {
            boolean esBolaBlanca = random.nextInt(totalBolas()) < bolasBlancas;
            if (esBolaBlanca) {
                bolasBlancas--;
                return ColorBola.Blanca;
            } else {
                bolasNegras--;
                return ColorBola.Negra;
            }
        }

        if (bolasNegras > 0) {
            bolasNegras--;
            return ColorBola.Negra;
        } else {
            bolasBlancas--;
            return ColorBola.Blanca;
        }
    }

    @Override
    public String toString() {
        return "U(B: " + bolasBlancas + ", N: " + bolasNegras + ")";
    }

}
