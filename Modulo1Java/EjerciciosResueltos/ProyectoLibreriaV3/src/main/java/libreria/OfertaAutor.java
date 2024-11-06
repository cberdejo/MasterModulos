package libreria;

import java.util.Arrays;

public class OfertaAutor  implements OfertaFlex{

    private String[] autores;
    private double porDescuento;

    public OfertaAutor(double porDescuento, String[] autores) {
        if (porDescuento < 0 ) throw new IllegalArgumentException();
        this.autores = autores;
        this.porDescuento = porDescuento;
    }

    @Override
    public double getDescuento(Libro libro) {
        double pos = getPosicionAutores(libro);
        return (pos >= 0)? porDescuento : 0;
    }

    private double getPosicionAutores(Libro libro) {
       int pos = 0;
       while (pos < autores.length && !autores[pos].equalsIgnoreCase(libro.getAutor()))
           pos++;
       return (pos == autores.length)? -1 : pos;
    }

    @Override
    public String toString() {
        //return porDescuento+"%"+ Arrays.toString(autores);
        String salida = porDescuento +"%[";
        for (int i = 0; i < autores.length; i++) {
            if (i > 0) salida += ";";
            salida += autores[i];
        }
        return salida + "]";
    }
}
