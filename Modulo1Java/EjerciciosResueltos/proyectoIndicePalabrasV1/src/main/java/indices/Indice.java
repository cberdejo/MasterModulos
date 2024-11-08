package indices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Indice {
    private final List<String> texto;

    public Indice() {
        this.texto = new ArrayList<>();
    }

    public List<String> getTexto() { // Cambiado para devolver la lista
        return texto;
    }

    public void agregarLinea(String texto) {
        this.texto.add(texto);
    }

    public abstract void resolver(String delimitadores, Collection<String> noSignificativas);
    public abstract void presentarIndiceConsola();
}
