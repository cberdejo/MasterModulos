package amigos;
/// @Author: Christian Berdejo Sánchez
/// @Version 1.0
public class Persona  implements Comparable<Persona>{
    private String nombre;
    private Persona amigo;

    ///Crea una persona con el nombre indicado
    /// @param nombre el nombre de la persona
    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Persona getAmigo() {
        return amigo;
    }

    public void setAmigo(Persona amigo) {
        this.amigo = amigo;
    }

    @Override
    public boolean equals(Object o) {
       return  (o instanceof Persona p ) && p.getNombre().equalsIgnoreCase(getNombre());
    }

    @Override
    public int hashCode() {
        return getNombre().toLowerCase().hashCode();
    }

    @Override
    public int compareTo(Persona p) {
        return getNombre().compareToIgnoreCase(p.getNombre());
    }

    @Override
    public String toString() {
        String regalado = (amigo == null) ? "sin amigo" : amigo.getNombre();
        return String.format("%s :le regala a: %s", getNombre(), regalado );
    }


}
