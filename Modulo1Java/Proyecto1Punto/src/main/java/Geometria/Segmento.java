package Geometria;

public class Segmento {

    private Punto origen;
    private Punto extremo;

    public Segmento(Punto o, Punto e) {
        this.origen = o;
        this.extremo = e;
    }

   public void trasladar(double a, double b){
        origen.trasladar(a, b);
        extremo.trasladar(a, b);
    }
    public double longitud(){
        return origen.distancia(extremo);
    }
}
