package tema5;

import java.util.*;

public class Numeros {
    public static void main(String [] args) {
        Random alea = new Random();
        Map<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = 0; i < 30; i++) {
            int ea = alea.nextInt(1000);
            int m = ea % 10;
            List<Integer> lista = map.get(m);
            if (lista == null) {
                lista = new ArrayList<>();
                map.put(m, lista);
            }
            lista.add(ea);
        }
        for (int i : map.keySet()) {
            System.out.println(i + "\t" + map.get(i));
        }
    }
}
