package tema4io;

import tema5.clases.Persona;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SPersona {

    private Set<Persona> personas = new HashSet<>();

    public void leePersonas(String fichero) throws IOException {
        for(String linea : Files.readAllLines(Paths.get(fichero)))
            personas.add(stringAPersona(linea));
    }

    public Persona stringAPersona(String linea) {
        Persona persona = null;
        try (Scanner scLinea = new Scanner(linea)) {
            scLinea.useDelimiter("[,:-]+");
            String nombre = scLinea.next();
            int edad = scLinea.nextInt();
            persona = new Persona(nombre, edad);
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Error de fichero. Número erróneo");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error de fichero. Faltan datos");
        }
        return persona;
    }
    public Set<Persona> getPersonas() {
        return personas;
    }
}
