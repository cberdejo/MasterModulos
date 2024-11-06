package tema4io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Copia {
	public static void main(String args[]) {
		try (InputStream desdeF = Files.newInputStream(Path.of(args[0]));
			 OutputStream hastaF = Files.newOutputStream(Path.of(args[1]))) {
			// Copia de los bytes
			int i = desdeF.read();
			while (i != -1) { // -1 si se alcanza fin de fichero
				hastaF.write(i);
				i = desdeF.read();
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Uso: Copia <origen> <destino>");
		} catch (FileNotFoundException e) {
			System.err.println("No existe " + e);
		} catch (IOException e) {
			System.err.println("Error de E/S " + e);
		}
	}
}
