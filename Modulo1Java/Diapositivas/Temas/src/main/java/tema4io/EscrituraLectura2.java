package tema4io;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class EscrituraLectura2 {
	public static void main(String[] args) throws IOException {
		String fichero = "data/tema4io/palabras.txt";
		// imprimir un fichero de palabras
		try (PrintWriter pw = new PrintWriter(fichero)) {
			pw.println("amor roma mora ramo");
			pw.println("rima mira");
			pw.println("rail liar");
		}
		// leer el fichero de palabras mostrando palabra fst palabra
		for (String linea : Files.readAllLines(Path.of(fichero)))
			try (Scanner scLinea = new Scanner(linea)) {
				while (scLinea.hasNext())
					System.out.println(scLinea.next());
			}
	}
}

