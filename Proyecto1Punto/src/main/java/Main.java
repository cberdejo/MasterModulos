import Geometria.Punto;
import Geometria.Segmento;

import java.util.Random;


public class Main {


    public static void main(String[] args) {

        Random rdm = new Random();
        Punto pto1 = new Punto(rdm.nextDouble(10), rdm.nextDouble(10));
        Punto pto2 = new Punto(rdm.nextDouble(10), rdm.nextDouble(10));
        System.out.println("Los puntos iniciales son" + pto1 +" y "+ pto2);
        System.out.println("La distancia entre ellos es: " + pto1.distancia(pto2));

        //Trasladar random y loguear
        Punto puntoAntiguo1 = new Punto(pto1.absica(), pto1.ordenada());
        pto1.trasladar(rdm.nextDouble(10), rdm.nextDouble(10));
        System.out.println("El punto"+ puntoAntiguo1  +"se ha trasladado a: " + pto1);

        Punto puntoAntiguo2 = new Punto(pto2.absica(), pto2.ordenada());
        pto2.trasladar(rdm.nextDouble(10), rdm.nextDouble(10));
        System.out.println("El punto"+ puntoAntiguo2  +"se ha trasladado a: " + pto2);

        System.out.println("La distancia entre ellos es: " + pto1.distancia(pto2));
        System.out.println("------------------------------------------------------");

    }
}