package Ejemplos;
import java.util.Random;

public class Urna {
    private int bolasNegras;
    private int bolasBlancas;
    private Random random;

    public Urna(int bolasNegras, int bolasBlancas) {
        this.bolasNegras = bolasNegras;
        this.bolasBlancas = bolasBlancas;
        this.random = new Random();

    }

    public void agregarBolaRoja() {
        bolasNegras++;
    }

    public void agregarBolaAzul() {
        bolasBlancas++;
    }

    public void quitarBolaRoja() {
        if (bolasNegras > 0) {
            bolasNegras--;
        } else {
            System.out.println("No hay más bolas Negras para quitar.");
        }
    }

    public void quitarBolaAzul() {
        if (bolasBlancas > 0) {
            bolasBlancas--;
        } else {
            System.out.println("No hay más bolas Blancas para quitar.");
        }
    }

    public void quitarBolaAlAzar(){
        if (bolasBlancas == 0 && bolasNegras == 0) {
            System.out.println("No quedan bolas en la urna.");
            return;
        }

        if (bolasBlancas > 0 && bolasNegras > 0) {
            // Elección al azar
            if (random.nextInt(2) == 0) {
                bolasBlancas--;
                System.out.println("Se ha quitado una bola blanca.");
            } else {
                bolasNegras--;
                System.out.println("Se ha quitado una bola negra.");
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
