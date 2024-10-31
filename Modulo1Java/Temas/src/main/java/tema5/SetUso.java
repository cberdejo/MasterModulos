package tema5;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by pacog on 14/4/15.
 */
public class SetUso {
    public static void main(String [] args) {
        Set<String> cs = new HashSet<>();
        cs.add("hola");
        cs.add("que");
        cs.add("tal");
        cs.add("como");
        cs.add("estas");
        cs.add("y");
        cs.add("tal");
        System.out.println(cs.size());
        System.out.println(cs);
        for(String s: cs) {
            System.out.println(s);
        }
        Iterator<String> it = cs.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }
}
