package tema5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrecuenciasFeo {
    public static void main(String [] args) {
        Map<String , List<Integer>> map = new HashMap<>();

        for (int i = 0; i< args.length; i++) {
            String palabra = args[i];
            if (map.containsKey(palabra)) {
                List<Integer> lista = map.get(palabra);
                lista.add(i);
               // map.put(palabra, lista); // es necesaria?
            } else {
                List<Integer> lista = new ArrayList<>();
                lista.add(i);
                map.put(palabra,lista);
            }
        }
        /*
        for (int i = 0; i< args.length; i++) {
            String palabra = args[i];
            List<Integer> lista = map.get(palabra);
            if (lista == null) {
                lista = new ArrayList<>();
                map.put(palabra, lista);
            }
            // PD: lista esta asociado fst palabra en map
            lista.add(i);
        }
    */
        System.out.println(map);
    }
}
