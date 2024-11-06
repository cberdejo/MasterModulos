package genetico;

public class CeroMax implements Problema {


    /// Devuelve el fitness de `cromosoma`
    /// En este caso a mas nº de ceros, mayor es el fitness
    /// @param cromosoma el cromosoma a evaluar
    /// @return el fitness
    @Override
    public double evalua(Cromosoma cromosoma) {
        double numCeros = 0;
        for(int pos = 0; pos < cromosoma.getLongitud(); pos++) {
            if (cromosoma.getGen(pos) == 0) numCeros++;
        }
        return numCeros;
    }
}
