package alturas;
/// @Author: Christian Berdejo
/// @Version 1.0
public class MayoresQue implements  Seleccion{

    private double altura;
    public MayoresQue(double altura){
        this.altura = altura;
    }
    @Override
    public boolean test(Pais pais) {
        return pais.altura() > altura;
    }
}
