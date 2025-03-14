package mastermind;

import java.util.Optional;
import java.util.Random;

///@Author Christian Berdejo
/// @Version 1.0
public class Mastermind {
    protected static final int TAMANO_POR_DEFECTO  = 4;
    private String combinacionSecreta;
    private static Random alea = new Random();

    public Mastermind() {
        this(TAMANO_POR_DEFECTO);
    }

    public Mastermind(int tam) {
        int numCifrasDisponibles = 10;
        if (tam <= 0 || tam > 10) {
           throw new IllegalArgumentException("El tamano debe estar entre 1 y 10");
        }
       generarCombinacionSecreta(tam);
    }

    ///Genera una cadena de texto con `numCifras` cifras aleatorias.
    /// @param numCifras Cuantas cifras queremos generar
    private void generarCombinacionSecreta(int numCifras) {
        this.combinacionSecreta = "";
        for (int i = 0; i < numCifras; i++) {
            String n = Integer.toString(alea.nextInt(10));
            if (!combinacionSecreta.contains(n))
                this.combinacionSecreta += n;
        }
    }

    /// @return La longitud de la combinacion secreta
    public int getLongitud() {
        return this.combinacionSecreta.length();
    }

    /// Comprueba la validez de una combinacion:
    /// - Debe tener el mismo número de dígitos que la combinación secreta
    /// - Cada dígito debe ser único
    /// - Cada dígito debe ser un número
    /// @param cifras La combinacion
    /// @return `true` si la combinacion es valida, `false` en caso contrario
    private boolean validaCombinacion(String cifras){
        // (\d) se asegura de que cada digito sea un digito numerico
        // (?!.*\1) se asegura de que no se repita el mismo digito
        //{longitud} número de digitos únicos
        return cifras.matches("(?:(\\d)(?!.*\\1)){" + getLongitud() + "}");
    }
    /// Realiza un intento para adivinar la clave secreta
    /// @param intento La combinacion introducida por el usuario
    /// @return El resultado del intento de adivinar la combinacion, si no es válido devuelve empty
    public Optional<Movimiento> intento(String intento) {
        if (!validaCombinacion(intento))
            return Optional.empty();

        int colocadas = 0;
        int descolocadas = 0;
        for (int i = 0; i < intento.length(); i++){

          int pos = combinacionSecreta.indexOf(intento.charAt(i));
          if (pos >= 0) {
              if (pos == i) {
                  colocadas++;
              } else {
                  descolocadas++;
              }
          }

        }
       return  Optional.of(new Movimiento(intento, descolocadas, colocadas));

    }

    public String getSecreto(){
        return this.combinacionSecreta;
    }
}
