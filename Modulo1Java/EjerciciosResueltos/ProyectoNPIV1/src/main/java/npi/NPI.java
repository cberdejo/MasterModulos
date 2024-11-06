package npi;
/// @Author: Christian Berdejo
/// @Version: 1.0

public class NPI {
    private double x,y,z,t;


    public NPI() {
        x=0;
        y=0;
        z=0;
        t=0;
    }
    /// #### Entra un valor a la cola
    /// @param valor que entra
    ///
    public void entra(double valor){
        t = z;
        z=y;
        y=x;
        x=valor;

    }
     /// #### Suma los valores de la cola
    public void suma(){
        x = y + x;
        y = z;
        z = t;
    }
    /// #### Resta los valores de la cola
    public void resta(){
        x = y-x;
        y = z;
        z = t;
    }

    /// #### Multiplica los valores de la cola
    public void multiplica(){
        x = y * x;
        y = z;
        z = t;
    }

    /// #### Divide los valores de la cola
    public void divide(){
        x = y / x;
        y = z;
        z = t;
    }
    /// #### Obtiene el resultado actual al principio de la cola
    /// @return el valor de la cola
    public double getResultado(){return x;}

    @Override
    public String toString() {
        return String.format("NPI(x = %.3f, y = %.3f, z = %.3f, t = %.3f)",x,y,z,t);
    }
}
