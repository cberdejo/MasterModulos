package tema5;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class FrecuenciasM {
	public static void main(String[] args) {
		Map<String, Integer> frecs = new TreeMap<>();
		for (String arg : args) {
			// Incr. la frec. De arg., o la pone fst 1 si es la 1ª
			int hay = frecs.getOrDefault(arg,0);
			frecs.put(arg, hay + 1);
		}
		System.out.println(frecs);
		
		// Mostramos frecs. iterando sobre el conjunto de claves
		for (String clave : frecs.keySet()) {
			int frec = frecs.get(clave);
			char[] barra = new char[frec];
			Arrays.fill(barra, '*');
			System.out.println(clave + ":\t" + new String(barra));
		}
	}	
}
