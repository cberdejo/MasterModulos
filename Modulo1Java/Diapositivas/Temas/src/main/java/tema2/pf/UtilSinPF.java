package tema2.pf;

import java.util.ArrayList;
import java.util.List;


public class UtilSinPF {
    public static List<Double> abcisas(List<Punto> list) {
        List<Double> sol = new ArrayList<>();
        for (Punto p : list)
            sol.add(p.x());
        return sol;
    }

    public static List<Punto> mayor4(List<Punto> list) {
        List<Punto> sol = new ArrayList<>();
        for (Punto p : list)
            if (p.distancia(new Punto(0,0)) > 4)
                sol.add(p);
        return sol;
    }
}
