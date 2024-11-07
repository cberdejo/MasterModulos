package amigos;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class ClubParejas extends Club{

    private Set<Pareja> parejas;


    //Constructor es el mismo que el padre

    private void leeSocios(String linea, String delim) {

        try (Scanner scLinea = new Scanner(linea)){
            scLinea.useDelimiter( "["+delim+"]+");
            creaSocioDesdeString(scLinea.next());
        } catch (NoSuchElementException e) {
            //No se especifica nada de excepciones, por lo que la linea no es válida, se ignora y ya.
        }

    }


    @Override
    protected void creaSocioDesdeString(String nombre){
        socios.add(new Persona(nombre));

    }

    @Override
    protected void hacerAmigos() {

    }
}
