package Geometria;

public class Punto
{
    private double x,y;
    public Punto(){
        this(0,0);
    }
    public Punto(double x, double y ){
        this.x=x;
        this.y=y;
    }
    //GETTERS
    public double absica(){return x;}
    public double ordenada(){return y;}
    //METHODS
    public void trasladar(double a, double b){
        x += a;
        y += b;
    }
    public double distancia(Punto pto){
        return Math.sqrt(Math.pow(x-pto.x,2)+Math.pow(y-pto.y,2));
    }
    public String toString(){
        return "("+x+","+y+")";
    }
}
