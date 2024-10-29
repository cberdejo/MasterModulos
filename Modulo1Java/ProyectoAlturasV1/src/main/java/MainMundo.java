import alturas.EnContinente;
import alturas.MenoresQue;
import alturas.Pais;
import alturas.Mundo;

import java.io.IOException;

public class MainMundo {
    public static void main(String args[]) throws IOException {
        Mundo paises = new Mundo();
        paises.leePaises("data/alturas.txt");
        for (Pais pais : paises.selecciona(new MenoresQue(1.70))) {
            System.out.println(pais);
        }
        //System.out.println();
        for (Pais pais : paises.selecciona(new EnContinente("Europe"))) {
            System.out.println(pais);
        }

    }
}
