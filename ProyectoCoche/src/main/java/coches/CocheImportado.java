package coches;
///@Author Christian Berdejo
///@Version 1.0
public class CocheImportado  extends Coche {
    private final double homologacion;

    /// Crea un coche importado
    /// @param nombre el nombre del coche
    /// @param precio el precio del coche
    /// @param homologacion la homologación del coche
    public CocheImportado(String nombre, double precio,  double homologacion) {
        super(nombre, precio);
        if (homologacion < 0) throw new IllegalArgumentException("La homologación no puede ser menor que 0");
        this.homologacion = homologacion;
    }
    /// Devuelve el precio total, añadiendole la homologación correspondiente
    /// @return el precio total del coche
    public double precioTotal() {
        return super.precioTotal() + homologacion;

    }
}
