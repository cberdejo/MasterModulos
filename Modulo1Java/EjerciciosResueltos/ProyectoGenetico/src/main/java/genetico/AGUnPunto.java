package genetico;

public class AGUnPunto extends AlgoritmoGenetico{

    ///Crea un algoritmo genetico con recombinación de punto con los parámetros de entrada
    ///@param tamPoblacion el tamaño de la población
    /// @param longCromosoma la longitud de los cromosomas
    /// @param pasos el número de pasos que se iterarán
    /// @param probMutacion la probabilidad de mutación
    /// @param problema el problema que se quiere resolver
    public AGUnPunto(int tamPoblacion, int longCromosoma, int pasos, double probMutacion, Problema problema) {
        super(tamPoblacion, longCromosoma, pasos, probMutacion, problema);
    }


    ///*Recombina ambos cromosomas*
    ///
    ///Se genera un número aleatorio `z` comprendido entre cero y la longitud
    /// del cromosoma. Los primeros `z` genes del individuo resultante se toman del primer padre y el
    /// resto del segundo
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
        int z = gna.nextInt(cromosoma1.getLongitud());
        Cromosoma cromosomaRecombinado = new Cromosoma(cromosoma1.getLongitud(), false);
        for (int i = 0; i < cromosomaRecombinado.getLongitud(); i++) {
          cromosomaRecombinado.setGen(i, i<z ? cromosoma1.getGen(i) : cromosoma2.getGen(i));
        }
        return cromosomaRecombinado;
    }
}
