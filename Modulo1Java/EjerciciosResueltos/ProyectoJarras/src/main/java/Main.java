
import jarras.Jarra;
import jarras.Mesa;
import position.Posicion;

public class Main {
    public static void main(String[] args) {
        //Ejercicio 1
        System.out.println("EJERCICIO 1");
        Jarra j1 = new Jarra(7);
        Jarra j2 = new Jarra(5);
        System.out.println(j1 +" , "+ j2);

        j1.llena(); // 7 0
        j2.llenaDesde(j1); //2 5
        j2.vacia(); // 2 0
        j2.llenaDesde(j1); // 0 2
        j1.llena(); // 7 2
        j2.llenaDesde(j1); //4 5
        j2.vacia(); // 4 0
        j2.llenaDesde(j1); //0 4
        j1.llena(); // 7 4
        j2.llenaDesde(j1); // 6 5
        j2.vacia(); // 6 0
        j2.llenaDesde(j1); // 1 5
        System.out.println(j1 + ", " + j2);

        //Ejercicio 2
        System.out.println("EJERCICIO 2");
        Mesa mesa = new Mesa(7,5);

        System.out.println(mesa);

        mesa.llena(Posicion.IZQUIERDA); // 7 0
        mesa.llenaDesde(Posicion.DERECHA); // 2 5
        mesa.vacia(Posicion.DERECHA); // 2 0
        mesa.llenaDesde(Posicion.DERECHA); // 0 2
        mesa.llena( Posicion.IZQUIERDA); // 7 2
        mesa.llenaDesde(Posicion.DERECHA); // 4 5
        mesa.vacia(Posicion.DERECHA);//4 0
        mesa.llenaDesde(Posicion.DERECHA); // 0 4
        mesa.llena(Posicion.IZQUIERDA);// 7 4
        mesa.llenaDesde(Posicion.DERECHA); // 6 5
        mesa.vacia( Posicion.DERECHA); // 6 0
        mesa.llenaDesde(Posicion.DERECHA); //1 5

        System.out.println(mesa);

    }
}