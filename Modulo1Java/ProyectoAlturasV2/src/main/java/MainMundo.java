import alturas.EnContinente;
import alturas.MenoresQue;
import alturas.Pais;
import alturas.Mundo;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MainMundo {
    public static void main(String args[]) throws IOException {
        Mundo paises = new Mundo();
        paises.leePaises("data/alturas.txt");

        List<Pais> listaPaises = paises.getPaises();

        // a)
        Comparator<Pais> comparadorAlturaAsc = Comparator.comparingDouble(Pais::altura);
        Set<Pais> paisesOrdenadosAlturaAsc = new TreeSet<>(comparadorAlturaAsc);
        paisesOrdenadosAlturaAsc.addAll(listaPaises);

        // b)
        Comparator<Pais> comparadorNombre = Comparator.comparing(Pais::pais);
        Set<Pais> paisesOrdenadosNombre = new TreeSet<>(comparadorNombre);
        paisesOrdenadosNombre.addAll(listaPaises);

        // c)
        Comparator<Pais> comparadorContinenteNombre = Comparator.comparing(Pais::continente)
                .thenComparing(Pais::pais);
        Set<Pais> paisesOrdenadosContinenteNombre = new TreeSet<>(comparadorContinenteNombre);
        paisesOrdenadosContinenteNombre.addAll(listaPaises);

        // d)
        Comparator<Pais> comparadorContinenteNombreReverso = Comparator.comparing(Pais::continente)
                .thenComparing(Pais::pais, Comparator.reverseOrder());
        Set<Pais> paisesOrdenadosContinenteNombreReverso = new TreeSet<>(comparadorContinenteNombreReverso);
        paisesOrdenadosContinenteNombreReverso.addAll(listaPaises);

        // e)
        Set<Pais> paisesOrdenadosPorOrdenNatural = new TreeSet<>(listaPaises);

        // f)
        Comparator<Pais> comparadorContinenteOrdenNatural = Comparator.comparing(Pais::continente)
                .thenComparing(Comparator.naturalOrder());
        Set<Pais> paisesOrdenadosContinenteOrdenNatural = new TreeSet<>(comparadorContinenteOrdenNatural);
        paisesOrdenadosContinenteOrdenNatural.addAll(listaPaises);


        System.out.println("a) Países ordenados por altura (ascendente): " + paisesOrdenadosAlturaAsc);
        System.out.println("b) Países ordenados alfabéticamente: " + paisesOrdenadosNombre);
        System.out.println("c) Países ordenados por continente y alfabéticamente: " + paisesOrdenadosContinenteNombre);
        System.out.println("d) Países ordenados por continente y alfabéticamente inverso: " + paisesOrdenadosContinenteNombreReverso);
        System.out.println("e) Países ordenados por orden natural (altura, alfabético): " + paisesOrdenadosPorOrdenNatural);
        System.out.println("f) Países ordenados por continente y orden natural: " + paisesOrdenadosContinenteOrdenNatural);
    }
}
