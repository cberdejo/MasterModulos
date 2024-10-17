package caso2;
// CON INTERFACES

import java.util.ArrayList;
import java.util.List;

public class Amigos {
	private List<Persona> personas;
	
	public Amigos(List<Persona> personas) {
		this.personas = personas;
	}
	
	public List<Persona> getPersonas() {
		return personas;
	}
	
	public List<Persona> filtra(Predicado pred) {
		List<Persona> nList = new ArrayList<>();
		for (Persona p: personas)
			if (pred.test(p))
				nList.add(p);
		return nList;
	}	
}
