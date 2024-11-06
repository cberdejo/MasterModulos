package genetico;

import java.util.Arrays;

///@Author: Christian Berdejo
///@Version:1.0
public class Poblacion {
    private Individuo[] individuos;

    ///Crea una nueva Poblacion
    /// @param numPoblacion el numero de individuos
    /// @param longitudCromosoma la longitud de cada cromosoma
    /// @param problema el problema
    /// @throws IllegalArgumentException si `numPoblacion` o `longitudCromosoma` es negativo
    public Poblacion(int numPoblacion, int longitudCromosoma,Problema problema) {
       if (numPoblacion < 0 || longitudCromosoma < 0)
           throw new IllegalArgumentException();

        this.individuos = new Individuo[numPoblacion];
        for (int i = 0; i < numPoblacion; i++) {
            this.individuos[i] = new Individuo(longitudCromosoma,problema);
        }
    }

    ///Devuelve el numero de individuos de `this`
    /// @return el numero de individuos
    public int getNumIndividuos(){
        return this.individuos.length;
    }
    ///Devuelve el individuo en la posicion pos
    /// @param pos la posicion del individuo
    /// @throws IllegalArgumentException si la posicion no es valida
    /// @return el individuo en la posicion pos
    public Individuo getIndividuo(int pos){
        if (pos < 0 || pos > this.individuos.length) throw new IllegalArgumentException();
        return this.individuos[pos];
    }

    ///Devuelve el mejor individuo de `this`, es decir, el que mayor `fitness` tenga
    /// @return el mejor individuo
    public Individuo mejorIndividuo(){
        Individuo mejor = this.individuos[0];
       //Se podría simplificar con un stream
        for (int i = 1; i < this.individuos.length; i++){
            if (this.individuos[i].getFitness() > mejor.getFitness())
                mejor = this.individuos[i];
        }

        return mejor;
    }

    ///Reemplaza un el peor individuo en `this` por el individuo pasado por parámetro
    /// si el individuo pasado por parámetro tiene mayor fitness que el peor
    /// @param individuo el individuo que se reemplazará
    public void reemplaza(Individuo individuo){
        int posPeor = 0;
        for (int i = 1; i < this.individuos.length; i++){
            if (this.individuos[i].getFitness() < this.individuos[posPeor].getFitness())
                posPeor = i;
        }

        this.individuos[posPeor] = this.individuos[posPeor].getFitness() < individuo.getFitness() ? individuo : this.individuos[posPeor];
    }
}
