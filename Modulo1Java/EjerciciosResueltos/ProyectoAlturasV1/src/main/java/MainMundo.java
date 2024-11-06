import alturas.EnContinente;
import alturas.MenoresQue;
import alturas.Pais;
import alturas.Mundo;

import java.io.IOException;

public class MainMundo {
    public static void main(String args[]) throws IOException {
        Mundo paises = new Mundo();
        paises.leePaises("data/alturas.txt");

        double altura = 1.70;
        System.out.println("Paises menores de " + altura + ": ");
        paises.selecciona(new MenoresQue(altura)).forEach(pais -> System.out.println("Menor que " + altura + ": " + pais));

        String continente = "Europe";
        System.out.println("Paises en " + continente + ": ");
        paises.selecciona(new EnContinente(continente)).forEach(pais -> System.out.println(continente + ": " + pais));

    }
}
