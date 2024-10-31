package tema2.pf;

import java.util.List;
import static tema2.pf.UtilConPF.*;

public class MainConPF {
    public static void main(String[] args) {
        // Lista de puntos
        List<Punto> list = List.of(new Punto(3,4), new Punto(2,5), new Punto(1,2), new Punto(-3,5));
        System.out.println(list);
        // Listas de abscisas
        List<Double> list1 = map(list, p -> p.x());
        System.out.println(list1);
        // Listas de puntos con distancia al origen mayor que 4
        List<Punto> list2 = filter(list, p -> p.distancia(new Punto())>4);
        System.out.println(list2);
        // Lista de abscisas para puntos cuya distancia
        // al origen es mayor que 4
        List<Double> list3 = map(
                filter(list,
                        p-> p.distancia(new Punto())>4),
                p -> p.x());
        System.out.println(list3);
    }

}
