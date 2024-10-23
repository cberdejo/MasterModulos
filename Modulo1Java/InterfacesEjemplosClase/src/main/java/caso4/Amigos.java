package caso4;
// CON INTERFACES, LISTAS Y FUNCIONAL

import java.util.List;

public class Amigos {
	private List<Persona> personas;
	
	public Amigos(List<Persona> personas) {
		this.personas = personas;
	}
	
	public List<Persona> getPersonas() {
		return personas;
	}

}
