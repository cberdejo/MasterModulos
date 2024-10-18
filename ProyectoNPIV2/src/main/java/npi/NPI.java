package npi;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

/// @Author: Christian Berdejo
/// @Version: 1.0

public class NPI {
    private double x,y,z,t;

    final public static DoubleBinaryOperator suma = (a,b) -> a+b;
    final public static DoubleBinaryOperator resta = (a,b) -> a-b;
    final public static DoubleBinaryOperator producto = (a,b) -> a*b;
    final public static DoubleBinaryOperator division = (a,b) -> a/b;
    final public static DoubleUnaryOperator raiz = a -> Math.sqrt(a);

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
    /// ###Operaciones con una sola variable
    /// @param  operador que se aplica
    public void opera1 (DoubleUnaryOperator operador){

        x = operador.applyAsDouble(x);

    }
    /// ###Operaciones con dos variables
    /// @param operador que se aplica
    public void opera2 (DoubleBinaryOperator operador){

        x = operador.applyAsDouble(x,y);
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
