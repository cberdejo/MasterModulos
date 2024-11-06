package tema5.clases;

import java.util.Comparator;

/**
 * Created by pacog on 7/3/15.
 */
public class OrdenEdad implements Comparator<Persona> {
    // Se comparan por getEdad
    public int compare(Persona p1, Persona p2) {
        return Integer.compare(p1.getEdad(),p2.getEdad());
    }
}