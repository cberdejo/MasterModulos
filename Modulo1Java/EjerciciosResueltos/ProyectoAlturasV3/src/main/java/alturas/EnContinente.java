package alturas;
/// @Author: Christian Berdejo
/// @Version 1.0
public class EnContinente implements Seleccion {

    private String texto;
    //// @param texto texto que indica el continente
    public EnContinente(String texto) {
        this.texto = texto;
    }

    ///Comprobar que el continente del pais es igual al indicado
    /// @param pais pais a comprobar
    @Override
    public boolean test(Pais pais) {
       return pais.continente().equalsIgnoreCase(texto);
    }
}
