package alturas;
/// @Author: Christian Berdejo
/// @Version 1.0
public class MayoresQue implements  Seleccion{

    private double altura;

    /// @param altura altura a comprobar
    /// @throws IllegalArgumentException si la altura es negativa
    public MayoresQue(double altura){
        if (altura < 0) {
            throw new IllegalArgumentException("Altura no puede ser negativa");
        }
        this.altura = altura;
    }

    /// comprobar que la altura del pais es mayor que la altura dada
    /// @param pais pais a comprobar
    @Override
    public boolean test(Pais pais) {
        return pais.altura() > altura;
    }
}
