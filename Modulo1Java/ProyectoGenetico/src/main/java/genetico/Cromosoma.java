package genetico;
import java.util.Arrays;
import java.util.Random;

///@author Christian Berdejo
///@version 1.0
public class Cromosoma {
    private int [] datos;
    private final int GEN_POR_DEFECTO=0;
    private static Random gna = new Random();

    /// Crea un Cromosoma
    /// @param longitud indica el número de genes
    /// @param rdm Indica si el cromosoma se inicializa con genes valores aleatorios o con valores por defecto
    public Cromosoma(int longitud, boolean rdm) {
        if (longitud < 0) throw new IllegalArgumentException();

        //inicializa array
        this.datos = new int[longitud];
        for (int i = 0; i < longitud; i++) {
            this.datos[i] = rdm ? gna.nextInt(2) : GEN_POR_DEFECTO;
        }

    }

    /// Crea un Cromosoma copia de otro
    /// @param cromosoma el cromosoma que se copia
    public void Cromosoma (Cromosoma cromosoma){
        cromosoma = this;
    }

    /// Devuelve el gen de la posicion
    /// @return el gen en la posicón posicion de `this`
    /// @throws IllegalArgumentException si la posicion no es valida
    public int getGen(int posicion){
        if (posicion < 0 || posicion > this.datos.length) throw new IllegalArgumentException();

        return this.datos[posicion];
    }

    /// Establece el gen de la posicion
    /// @param posicion la posicion del gen
    /// @param valor el valor del gen
    /// @throws IllegalArgumentException si la posicion no es valida o el valor no es 0 o 1
    public void setGen(int posicion, int valor){
        if ((posicion < 0 || posicion > this.datos.length) || (valor != 0 && valor != 1)) throw new IllegalArgumentException();
        this.datos[posicion] = valor;

    }
    /// Devuelve el numero de genes en `this`
    /// @return número de genes
    public int getLongitud(){
        return this.datos.length;
    }

    /// Recorre los genes de `this` y los muta de forma aleatoria
    /// @param probabilidad la probabilidad de mutar un gen
    public void mutar(double probabilidad){
        for (int i = 0; i < this.datos.length; i++){
            if (gna.nextDouble() < probabilidad){
                this.datos[i] = gna.nextInt(2);
            }
        }

    }
    @Override
    public String toString() {
        return Arrays.toString(this.datos);
    }
}
