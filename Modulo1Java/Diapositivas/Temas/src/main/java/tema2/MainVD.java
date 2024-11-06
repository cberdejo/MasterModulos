package tema2;

import tema2.vd.Cuadrado;
import tema2.vd.Poligono;

public class MainVD {
    public static void main(String[] args) {
        Poligono p = new Cuadrado(5);
        System.out.println(p.perimetro());
    }
}
