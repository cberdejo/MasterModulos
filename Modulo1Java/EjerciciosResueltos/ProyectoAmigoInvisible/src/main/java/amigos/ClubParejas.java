package amigos;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ClubParejas extends Club{

    private Set<Pareja> parejas;


    //Constructor es el mismo que el padre
    public ClubParejas() {
        super();
        parejas = new HashSet<>();
    }


    /// Crea uno o dos socios a partir de una cadena. En caso de encontrar un `-` se sobre entiende que es una pareja separada por `-`.
    /// @param  pareja cadena de texto que represena uno o dos socios
    @Override
    protected void creaSocioDesdeString(String pareja) {

        try (Scanner scFamilia = new Scanner(pareja)){
            scFamilia.useDelimiter("-+");
            Persona socio1 = new Persona(scFamilia.next());
            socios.add(socio1);

            if (scFamilia.hasNext()) {
                Persona socio2 = new Persona(scFamilia.next());
                parejas.add(new Pareja(socio1, socio2));
                socios.add(socio2);
            }
        } catch (NoSuchElementException e) {
            //No se especifica nada de excepciones, por lo que la linea no es válida, se ignora y ya.
        }

    }

    @Override
    protected void hacerAmigos() {
        if (socios.size() < 2)throw new AmigoException("No hay suficientes socios para hacer amigos");

        List<Integer> posAmigos = new ArrayList<>(Stream.iterate(0, x -> x < socios.size(), x -> x + 1).toList());
        List<Persona> listaSocios = socios.stream().toList();

        do{
            Collections.shuffle(posAmigos);
        } while(hayCoincidencias(posAmigos,listaSocios));



        for (int i = 0; i < socios.size(); i++){
            listaSocios.get(i).setAmigo(listaSocios.get(posAmigos.get(i)));
        }
    }

    /// Verifica si existe alguna coincidencia no permitida en la lista de posiciones de amigos.
    ///
    /// Este método comprueba si alguna persona se asigna a sí misma como amigo invisible o si se asigna
    /// a alguien de su misma pareja, en cuyo caso devuelve `true`.
    ///
    /// @param posAmigos Lista de posiciones de amigos, donde cada índice representa la persona a la que regalará el socio correspondiente.
    /// @param socios Lista de personas del club, en el mismo orden que `posAmigos`.
    /// @return true si alguna persona se asigna a sí misma o a su pareja; false en caso contrario.
    private boolean hayCoincidencias(List<Integer> posAmigos, List<Persona> socios) {
        return IntStream.range(0, socios.size())
                .anyMatch(i -> posAmigos.get(i) == i
                        || parejas.contains(new Pareja(socios.get(i), socios.get(posAmigos.get(i)))));


    }
}
