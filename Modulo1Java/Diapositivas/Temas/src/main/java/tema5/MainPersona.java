package tema5;

import tema5.clases.OrdenPersona;
import tema5.clases.Persona;

import java.util.Comparator;

/**
 * Created by pacog on 7/3/15.
 */
public class MainPersona {
    public static void main(String [] args) {
        Persona p1 = new Persona("Juan", 35);
        Persona p2 = new Persona("Pedro", 22);
        System.out.println(p1.compareTo(p2));
        Comparator<Persona> op = new OrdenPersona();
        System.out.println(op.compare(p1,p2));
    }
}
