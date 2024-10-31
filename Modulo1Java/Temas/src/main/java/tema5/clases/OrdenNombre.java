package tema5.clases;

import java.util.Comparator;

/**
 * Created by pacog on 7/3/15.
 */
public class OrdenNombre implements Comparator<Persona> {
    // Se comparan por nombres
    public int compare(Persona p1, Persona p2) {
        return p1.getNombre().compareTo(p2.getNombre());
    }
}