package alturas;
/// @Author: Christian Berdejo
/// @Version 1.0
public record Pais(String pais, String continente, double altura) implements Comparable<Pais> {


    /// Compara dos paises segun su altura
    @Override
    public int compareTo(Pais o) {
        int comp =  Double.compare(altura, o.altura);
        if (comp == 0) {
            return pais.compareTo(o.pais);
        }else {
            return comp;
        }
    }



}
