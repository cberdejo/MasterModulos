package genetico;

public class OneMax implements Problema {

    /// Devuelve el fitness de `cromosoma`
    /// En este caso a mas nº de unos, mayor es el fitness
    /// @param cromosoma el cromosoma a evaluar
    /// @return el fitness
    @Override
    public double evalua(Cromosoma cromosoma) {
        double numUnos = 0;
        for(int gen :cromosoma.datos) {
            if (gen == 1) numUnos++;
        }
        return numUnos;
    }
}
