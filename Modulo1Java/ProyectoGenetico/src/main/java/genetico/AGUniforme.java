package genetico;

public class AGUniforme extends AlgoritmoGenetico {

    ///Crea un algoritmo genetico con recombinación uniforme con los parámetros de entrada
    ///@param tamPoblacion el tamaño de la población
    /// @param longCromosoma la longitud de los cromosomas
    /// @param pasos el número de pasos que se iterarán
    /// @param probMutacion la probabilidad de mutación
    /// @param problema el problema que se quiere resolver
    public AGUniforme(int tamPoblacion, int longCromosoma, int pasos, double probMutacion, Problema problema) {
        super(tamPoblacion, longCromosoma, pasos, probMutacion, problema);
    }

    ///*Recombina ambos cromosomas*
    ///
    /// el valor de cada gen del individuo resultante se elige de forma aleatoria de uno de los padres
    /// @param cromosoma1 el primer cromosoma
    /// @param cromosoma2 el segundo cromosoma
    /// @throws  IllegalArgumentException si los cromosomas no tienen la misma longitud.
    /// *No debería saltar nunca si se llama desde ejecuta*
    /// @return el cromosoma resultante de la recombinación

    @Override
    protected Cromosoma recombinar(Cromosoma cromosoma1, Cromosoma cromosoma2) {
        if (cromosoma1.getLongitud() != cromosoma2.getLongitud()) {
            throw new IllegalArgumentException("Los cromosomas no tienen la misma longitud");
        }

        Cromosoma cromosoma = new Cromosoma(cromosoma1.getLongitud(), false);
        for (int i = 0; i < cromosoma1.getLongitud(); i++) {
            cromosoma.setGen(i, gna.nextInt(2) == 0 ? cromosoma1.getGen(i) : cromosoma2.getGen(i));
        }
        return cromosoma;
    }
}
