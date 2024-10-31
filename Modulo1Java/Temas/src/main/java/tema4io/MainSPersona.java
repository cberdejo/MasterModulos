package tema4io;

import java.io.IOException;

public class MainSPersona {

	public static void main(String [] args) throws IOException {
		SPersona sp = new SPersona();
		sp.leePersonas("data/tema4io/personas.txt");
		System.out.println(sp.getPersonas());
	}
}
