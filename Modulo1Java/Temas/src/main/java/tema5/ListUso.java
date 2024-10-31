package tema5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pacog on 14/4/15.
 */
public class ListUso {
    public static void main(String [] args) {
        List<String> ls = new ArrayList<>();
        ls.add(0,"Hola");
        ls.add(0,"Que");
        ls.add(2, "Tal");
        System.out.println(ls);
        ls.set(1, "Saludos");
        ls.add(2, "Mas");
        System.out.println(ls);
        for(String s : ls) {
            System.out.println(s);
        }
        Iterator<String> it = ls.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        Collections.shuffle(ls);
        System.out.println(ls);
        Collections.sort(ls);
        System.out.println(ls);
        for (int i = 0; i < ls.size(); i++) {
            System.out.println(ls.get(i));
        }
    }
}
