package urna;

import java.util.Random;
/// @Author Christian Berdejo
///  @Version 1.0
public class Urna {
    ///Representa el color de una bola
    static public enum ColorBola {Blanca, Negra};
    ///Número de bolas negras
    private int bolasNegras;
    ///Número de bolas blancas
    private int bolasBlancas;

    private Random random;

    ///Crea una urna con un número determinado de bolas negras y blancas.
    /// @param bolasNegras el número de bolas negras
    /// @param bolasBlancas el número de bolas blancas
    public Urna(int bolasNegras, int bolasBlancas) {
        this.bolasNegras = bolasNegras;
        this.bolasBlancas = bolasBlancas;
        this.random = new Random();

    }
    /// Agrega una bola negra a `this`
    public void agregarBolaNegra() {
        bolasNegras++;
    }

    /// Agrega una bola blanca a `this`
    public void agregarBolaBlanca() {
        bolasBlancas++;
    }

    /// Quita una bola negra de `this`
    /// @throw IllegalStateException si `this` no tiene bolas negras
    public void quitarBolaNegra (){
        if (bolasNegras==0) throw new IllegalStateException("No quedan bolas negras en la urna.");

        bolasNegras--;
    }

    /// Quita una bola blanca de `this`
    /// @throw IllegalStateException si `this` no tiene bolas blancas
    public void quitarBolaBlanca() {
        if (bolasBlancas==0) throw new IllegalStateException("No quedan bolas blancas en la urna.");

        bolasBlancas--;
    }

    public ColorBola quitarBolaAlAzar(){
        if (bolasBlancas == 0 && bolasNegras == 0) throw new IllegalStateException("No quedan bolas en la urna.");


        if (bolasBlancas > 0 && bolasNegras > 0) {
            // Elección al azar
            if (random.nextInt(2) == 0) {
                bolasBlancas--;
                return ColorBola.Blanca;
            } else {
                bolasNegras--;
            }
        } else if (bolasNegras > 0) {
            bolasNegras--;
            System.out.println("Solo quedan bolas negras. Se ha quitado una bola negra.");
        } else {
            bolasBlancas--;
            System.out.println("Solo quedan bolas blancas. Se ha quitado una bola blanca.");
        }

    }

    @Override
    public String toString() {
        return "Bolas negras:" + bolasNegras + " \n bolas blancas: " + bolasBlancas +"\n";
    }

}
