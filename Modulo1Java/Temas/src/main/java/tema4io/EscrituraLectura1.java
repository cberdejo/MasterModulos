package tema4io;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;


public class EscrituraLectura1 {
	public static void main(String[] args) throws IOException {
		String fichero = "data/tema4io/palabras.txt";
		try (PrintWriter pw = new PrintWriter(fichero)) {
			pw.println("amor roma mora ramo maro");
			pw.println("rima mira");
			pw.println("rail liar");
		}
		// leer el fichero de palabras
		for(String linea: Files.readAllLines(Path.of(fichero)))
				System.out.println(linea);
	}
}


