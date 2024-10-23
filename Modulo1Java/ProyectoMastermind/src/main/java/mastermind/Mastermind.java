package mastermind;

import java.util.Random;

///@Author Christian Berdejo
/// @Version 1.0
public class Mastermind {
    private static final int TAMANO_POR_DEFECTO  = 4;
    private String combinacionSecreta;
    private static Random alea = new Random();

    public Mastermind() {
        this(TAMANO_POR_DEFECTO);
    }

    public Mastermind(int tam) {
        int numCifrasDisponibles = 10;
        if (tam <= 0 || tam > 10) {
           throw new MastermindException("El tamano debe estar entre 1 y 10");
        }
       generarCombinacionSecreta(tam);
    }

    ///Genera una cadena de texto con `numCifras` cifras aleatorias.
    /// @param numCifras Cuantas cifras queremos generar
    private void generarCombinacionSecreta(int numCifras) {
        this.combinacionSecreta = "";
        for (int i = 0; i < numCifras; i++) {
            this.combinacionSecreta += alea.nextInt(numCifras);
        }
    }

    /// @return La longitud de la combinacion secreta
    public int getLongitud() {
        return this.combinacionSecreta.length();
    }

    /// Comprueba la validez de una combinacion
    /// @param cifras La combinacion
    /// @return `true` si la combinacion es valida, `false` en caso contrario
    private boolean validaCombinacion(String cifras){
        return cifras.matches(combinacionSecreta);
    }

    public Movimiento intento(String intento) {
    }
}
