package tema5;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pacog on 14/4/15.
 */
public class SetUso2 {
    public static void main(String [] args) {
        Set<String> cs = new HashSet<>();
        for (String s : args) {
            if (!cs.add(s)) {
                System.out.println("repetido "+ s);
            }
        }

        System.out.println(cs);
    }
}
