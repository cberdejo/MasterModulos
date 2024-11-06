package tema5.clases;

import java.util.Comparator;

/**
 * Created by pacog on 27/10/15.
 */
public class SatString implements Comparator<String> {
    public int compare (String s1, String s2) {
        int res = Integer.compare(s1.length(),s2.length());
        if (res == 0) {
            res = s1.compareTo(s2);
        }
        return res;
    }
}
