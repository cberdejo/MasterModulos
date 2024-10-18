package genetico;
import java.util.Arrays;
import java.util.Random;

///@author Christian Berdejo
///@version 1.0
public class Cromosoma {
    protected int [] datos;
    private final int GEN_POR_DEFECTO=0;
    private static Random gna;

    public Cromosoma(int longitud, boolean rdm) {
        if (longitud < 0) throw new IllegalArgumentException();

        this.datos = new int[longitud];

        Arrays.stream(this.datos).forEach(x -> {
            if (rdm) x = gna.nextInt();
            else x = GEN_POR_DEFECTO;
        });
        System.out.println(this.datos);
    }
    public void Cromosoma (Cromosoma cromosoma){

    }


    @Override
    public String toString() {
        return Arrays.toString(this.datos);
    }
}
