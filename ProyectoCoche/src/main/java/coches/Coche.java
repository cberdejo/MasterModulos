package coches;
/// @Author Christian Berdejo
///@Version 1.0

public class Coche {
    private static double Piva = 0.16;
    private final double precio;
    private final String nombre;

    /// Crea un Coche
    /// @param nombre el nombre del coche
    ///  @param precio el precio del coche
    public Coche( String nombre,double precio) {
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser menor que 0");
        this.precio = precio;
        this.nombre = nombre;
    }

    /// Altera el porcentaje del iva por uno dado por parámetro
    ///@param porcentajeIva el nuevo porcentaje del IVA
    /// @throws IllegalArgumentException si se envía un porcentaje menor que 0
    public static void setPiva(double porcentajeIva) {
        if (porcentajeIva < 0 ) throw new IllegalArgumentException("El nuevo porcentaje de IVA debe ser positivo");
        Piva = porcentajeIva;
    }
    /// Devuelve el precio total, añadiendole el iva correspondiente
    /// @return el precio total del coche
    public double precioTotal(){
        return precio + (precio * Piva);
    }

    @Override
    public String toString() {
        return "< " + nombre + " > -> < " + precioTotal() + ">";
    }
}
