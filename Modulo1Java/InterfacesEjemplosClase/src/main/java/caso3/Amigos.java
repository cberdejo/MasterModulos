package caso3;
// CON INTEFACES Y FUNCIONAL

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class Amigos {
	private List<Persona> personas;

	public Amigos(List<Persona> personas) {
		this.personas = personas;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public List<Persona> filtra(Predicate<Persona> pred) {
		List<Persona> nList = new ArrayList<>();
		for (Persona p: personas)
			if (pred.test(p))
				nList.add(p);
		return nList;
	}
}
