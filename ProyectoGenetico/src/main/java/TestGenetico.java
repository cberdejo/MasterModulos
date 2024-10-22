/*
import genetico.AGUniforme;
import genetico.OneMax;
import genetico.AGUnPunto;
import genetico.AlgoritmoGenetico;
import genetico.Individuo;
import genetico.Problema;

public class TestGenetico {

    static final int TAM_POBLACION = 20;
    static final int PASOS_GA = 400;
    static final int LONG_CROMOSOMA = 50;

    static final double PROB_MUT = 0.02;

    public static void main(String[] args) {
        Problema problema = new OneMax();

        AlgoritmoGenetico ga1 = new AGUnPunto(TAM_POBLACION, LONG_CROMOSOMA,
                PASOS_GA, PROB_MUT, problema);
        Individuo solucion1 = ga1.ejecuta();
        System.out.println("Solución 1:" + solucion1);

        AlgoritmoGenetico ga2 = new AGUniforme(TAM_POBLACION, LONG_CROMOSOMA,
                PASOS_GA, PROB_MUT, problema);
        Individuo solucion2 = ga2.ejecuta();
        System.out.println("Solución 2:" + solucion2);
    }
}
 */


import genetico.Cromosoma;

public class TestGenetico {



    public static void main(String[] args) {
        Cromosoma cromosoma = new Cromosoma(5,true);
    }
}