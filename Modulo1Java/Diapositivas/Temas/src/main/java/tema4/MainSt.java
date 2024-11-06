package tema4;

import java.util.Scanner;

public class MainSt {
	public static void main(String [] args) {
		try (Scanner sc = new Scanner("hola   fst ; todos. como-estas")) {
			// Separadores: espacio . , ; -    una o más veces (+)
			sc.useDelimiter("\\s*[ .,;-]+s*");
			while (sc.hasNext()) {
				String palabra = sc.next();
				System.out.println(palabra);
			}
		}
	}
}


