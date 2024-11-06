package tema5;

import tema5.clases.OrdenEdad;
import tema5.clases.OrdenNombre;
import tema5.clases.OrdenPersona;
import tema5.clases.Persona;

import java.text.Collator;
import java.util.*;
import java.util.function.Function;

public class EjPersona {
	public static void main(String [] args) {
		Set<Persona> sp = new HashSet<>();
		sp.add(new Persona("Juan",15));
		sp.add(new Persona("Pedro",23));
        sp.add(new Persona("Ana", 21));
		sp.add(new Persona("Maria", 23));
		sp.add(new Persona("MARIA", 23));

		System.out.println(sp);


		Set<Persona> sp1 = new TreeSet<>(new OrdenPersona());
		sp1.addAll(sp);

		System.out.println(sp1);

		Set<Persona> sp2 = new TreeSet<>(new OrdenPersona().reversed());
		sp2.addAll(sp1);
		System.out.println(sp2);

        //Set<Persona> sp2b = new TreeSet<>(new OrdenEdad().reversed().thenComparing(new OrdenNombre()));
        //Set<Persona> sp2b = new TreeSet<>(Comparator.comparingInt(Persona::getEdad).reversed().thenComparing(Comparator.comparing(Persona::getNombre)));
        // Por edad invertida y en caso de igualdad por nombre ignorando caso
		Function<Persona,String> f1 = Persona::getNombre;
		Function<String, String> f2 = String::toLowerCase;
		Set<Persona> sp2b = new TreeSet<>(Comparator.comparingInt(Persona::getEdad).reversed()
				.thenComparing(f1.andThen(f2)));
        sp2b.addAll(sp1);
        System.out.println(sp2b);

		Set<Persona> sp3 = new TreeSet<>(Collections.reverseOrder());
		sp3.addAll(sp1);
		System.out.println(sp3);

	}
}
