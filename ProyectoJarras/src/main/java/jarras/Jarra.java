package jarras;


public class Jarra {
    private final int capacidad;
    private int contenido   ;

    /**
     * Crea una Jarra con con una capacidad inicial
     * @param capacidad
     * @throws IllegalArgumentException en caso de que la capacidad inicial sea negativa
     */
    public Jarra(int capacidad) {
        if (capacidad < 0) throw new IllegalArgumentException("capacidad negativa: " + capacidad);

        this.capacidad = capacidad;
        this.contenido = 0;
    }


    /**
     * @return la capacidad de <code>this</code>
     */
    public int capacidad(){
        return capacidad;
    }

    /**
     *
     * @return el contenido de  <code>this</code>
     */
    public int contenido(){
        return contenido;
    }

    /**
     * Vacía el contenido de <code>this</code>
     */
    public void vacia() {
        contenido = 0;
    }

    /**
     * Llena la capacidad de <code>this</code> completamente
     */
    public void llena() {
        contenido =  capacidad;
    }

    /**
     * Llena  <code>this</code> hasta su capacidad con el contenido de
     * la jarra <code>jarra</code>.
     * <ul>
     *      <li>Si el contenido de <code>jarra</code> es mayor o igual que el espacio disponible de <code>this</code>
     *      el contenido será igual a la capcidad .
     *      </li>
     *      <li>En cualquier otro caso el contenido de <code>this</code> es igual su contenido + el contenido de <code>jarra</code>
     * </ul>
     * @param jarra jarra de la que se extrae el contenido
     * @throws IllegalArgumentException en caso de que se intente llenar una jarra consigo misma
     */
    public void llenaDesde(Jarra jarra) {
        if (jarra==this) throw new IllegalArgumentException("La Jarra argumento no puede ser la misma que la receptora");

        int espacioDisponible = capacidad - contenido;

        if (jarra.contenido >= espacioDisponible) {
            contenido = capacidad;
            jarra.contenido -= espacioDisponible;

        } else {
            contenido += jarra.contenido;
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
