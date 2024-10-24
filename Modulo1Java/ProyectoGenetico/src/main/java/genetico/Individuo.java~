package genetico;

public class Individuo {
    private double fitness;
    private Cromosoma cromosoma;

    public Individuo(int longitud_cromosoma, Problema problema) {

        this.cromosoma = new Cromosoma(longitud_cromosoma, true);
        this.fitness = problema.evalua(this.cromosoma);

    }
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
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
