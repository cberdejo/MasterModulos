package tema2.pf;

import java.util.List;

import static tema2.pf.UtilSinPF.abcisas;
import static tema2.pf.UtilSinPF.mayor4;

public class MainSinPF {
    public static void main(String[] args) {
        // Lista de puntos
        List<Punto> list = List.of(new Punto(3, 4), new Punto(2, 5), new Punto(1, 2), new Punto(-3, 5));
        System.out.println(list);
        // Listas de abscisas
        // Listas de abscisas
        List<Double> list1 = abcisas(list);
        System.out.println(list1);
        // Listas de puntos con distancia al origen mayor que 4
        List<Punto> list2 = mayor4(list);
        System.out.println(list2);
        // Lista de abscisas para puntos cuya distancia
        // al origen es mayor que 4
        List<Double> list3 = abcisas(mayor4(list));
        System.out.println(list3);
    }
}
