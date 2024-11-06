package caso1;

import java.util.ArrayList;
import java.util.List;

// SIN INTERFACES
public class Amigos {
	private List<Persona> personas;
	
	public Amigos(List<Persona> personas) {
		this.personas = personas;
	}
	
	public List<Persona> getPersonas() {
		return personas;
	}
	
	public List<Persona> mayoresQue(int n) {
		List<Persona> nList = new ArrayList<>();
		for (Persona p: personas)
			if (p.edad() >= n)
				nList.add(p);
		return nList;
	}
	
	public List<Persona> menoresQue(int n) {
		List<Persona> nList = new ArrayList<>();
		for (Persona p: personas)
			if (p.edad() <= n)
				nList.add(p);
		return nList;
	}
	
	public List<Persona> empiezanCon(String s) {
		List<Persona> nList = new ArrayList<>();
		for (Persona p: personas)
			if (p.nombre().startsWith(s))
				nList.add(p);
		return nList;
	}
}
