package genetico;

public class OneMax implements Problema {
    @Override
    public double evalua(Cromosoma cromosoma) {
        double numUnos = 0;
        for(int gen :cromosoma.datos) {
            if (gen == 1) numUnos++;
        }
        return numUnos;
    }
}
