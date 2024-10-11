package jarras;


public class Jarra {
    private int capacidad;
    private int contenido   ;

    /**
     * Crea una Jarra con con una capacidad inicial
     * @param capacidad
     * @throws IllegalArgumentException en caso de que la capacidad inicial sea negativa
     */
    public Jarra(int capacidad) {
        if (capacidad < 0) throw new IllegalArgumentException("capacidad negativa");

        this.capacidad = capacidad;
        this.contenido = 0;
    }


    /**
     * @return la capacidad de la jarra
     */
    public int capacidad(){
        return capacidad;
    }

    /**
     *
     * @return el contenido de la jarra
     */
    public int contenido(){
        return contenido;
    }

    /**
     * Vacía el contenido de la jarra
     */
    public void vacia() {
        contenido = 0;
    }

    /**
     * Llena la jarra completamente
     */
    public void llena() {
        contenido =  capacidad;
    }

    /**
     * Llena la jarra hasta su capacidad con el contenido de
     * la otra jarra.
     *
     * @param jarra la otra jarra
     * @throws IllegalArgumentException en caso de que se intente llenar una jarra consigo misma
     */
    public void llenaDesde(Jarra jarra) {
        // La jarra argumento no puede ser la misma que la receptora
        if (jarra==this) throw new IllegalArgumentException("La Jarra argumento no puede ser la misma que la receptora");

        // Espacio disponible en la jarra receptora
        int espacioDisponible = capacidad - contenido;

        // Si hay ms contenido en la jarra argumento que espacio disponible en la receptora
        if (jarra.contenido > espacioDisponible) {
            // Llena la jarra receptora al m ximo
            contenido = capacidad;
            // Descontamos el espacio disponible de la jarra argumento
            jarra.contenido -= espacioDisponible;

        } else {
            // A dimos el contenido de la jarra argumento a la receptora
            contenido += jarra.contenido;
            // Vaciamos la jarra argumento
            jarra.vacia();
        }

    }

    /**
     * Representa la Jarra como una cadena
     * @return una cadena en formato "J(capacidad,contenido)"
     */
    @Override
    public String toString() {
        return String.format("J(%d,%d)", capacidad, contenido);
    }
}
