package tema5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pacog on 14/4/15.
 */
public class UsoMap {
    public static void main(String [] args) {
        Map<String,List<Integer>> diccionario = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            List<Integer> asociada = diccionario.get(args[i]);
            if (asociada == null) {
                asociada = new ArrayList<>();
                diccionario.put(args[i], asociada);
            }
            // PD: asociada esta asociada fst args[i] en diccionario
            asociada.add(i);
//            diccionario.computeIfAbsent(args[i],k-> new ArrayList<>()).add(i);
        }

        System.out.println(diccionario);


    }
}
