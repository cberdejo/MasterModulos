package genetico;
/// @Author: Christian Berdejo
/// @Version: 1.o
public class Individuo {
    private double fitness;
    private Cromosoma cromosoma;

    ///Crea un Individuo con un cromosoma aleatorio
    /// @param longitud_cromosoma la longitud del cromosoma
    /// @param problema el problema que se quiere resolver
    public Individuo(int longitud_cromosoma, Problema problema) {

        this.cromosoma = new Cromosoma(longitud_cromosoma, true);
        this.fitness = problema.evalua(this.cromosoma);

    }

    ///Crea un Individuo
    /// @param cromosoma el cromosoma
    /// @param problema el problema que se quiere resolver
    public Individuo(Cromosoma cromosoma, Problema problema) {

        this.cromosoma = cromosoma;
        this.fitness = problema.evalua(this.cromosoma);
    }

    public Cromosoma getCromosoma() {
        return cromosoma;
    }
    public double getFitness() {
        return fitness;
    }

    @Override
    public String toString() {
        return "I(" + this.cromosoma + ", " + this.fitness + ")";
    }
}
