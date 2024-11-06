package indices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Indice {
    private  List<String> texto;

    public List<String> getTexto() {
        texto = new ArrayList<>();
    }
    public Indice(List<String> texto) {
        this.texto = texto;
    }

    public void agregarLinea(String texto) {
        this.texto.add(texto);}
    }

    public  void resolver(String delimitadores, Collection<String>
            noSignificativas) {
    }


}
