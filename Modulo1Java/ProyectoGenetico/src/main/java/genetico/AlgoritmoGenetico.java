package genetico;

import java.util.Random;

public abstract class AlgoritmoGenetico {
    private Problema problema;
    private double probMutacion;
    private Poblacion poblacion;
    private int pasos;

    protected static Random gna = new Random();

    ///Crea un algoritmo genetico con los parámetros de entrada
    ///@param tamPoblacion el tamaño de la población
    /// @param longCromosoma la longitud de los cromosomas
    /// @param pasos el número de pasos que se iterarán
    /// @param probMutacion la probabilidad de mutación
    /// @param problema el problema que se quiere resolver
    public AlgoritmoGenetico(int tamPoblacion, int longCromosoma, int pasos, double probMutacion, Problema problema) {
        this.problema = problema;
        this.poblacion = new Poblacion(tamPoblacion, longCromosoma,problema);
        this.pasos = pasos;
        this.probMutacion = probMutacion;
    }
    /// El algoritmo consiste en iterar tantas veces como indique el número de `pasos`, la secuencia siguiente:
    /// 1. Seleccionar dos individuos de la población al azar
    /// 1. Recombina sus cromosomas
    /// 1. Muta el cromosoma resultante
    /// 1. Reemplaza el peor individuo por el cromosoma resultante si este es mejor que el peor
    ///
    /// Una vez terminado devuelve el mejor individuo de la población, es decir, el que tiene mayor `fitness`
    public Individuo ejecuta() {
        //Itero por el nº de pasos
        for (int i = 0; i < pasos; i++) {
            //Obtengo dos individuos al azar
            Cromosoma cromosoma1 = poblacion.getIndividuo(gna.nextInt(poblacion.getNumIndividuos())).getCromosoma();
            Cromosoma cromosoma2 = poblacion.getIndividuo(gna.nextInt(poblacion.getNumIndividuos())).getCromosoma();

            //Recombino
            Cromosoma cromosomaRecombinado = recombinar(cromosoma1, cromosoma2);
            //Mutación
            cromosomaRecombinado.mutar(probMutacion);
            //Reemplazo
            poblacion.reemplaza(new Individuo(cromosomaRecombinado,problema));

        }
        return poblacion.getMejorIndividuo();

    }

    protected abstract Cromosoma recombinar(Cromosoma cromosoma1, Cromosoma cromosoma2);

}
