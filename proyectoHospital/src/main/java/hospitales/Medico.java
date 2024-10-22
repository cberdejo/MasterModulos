package hospitales;

public class Medico extends Persona{
    private Categoria categoriaProf;
    private double horasSemanales;
    private boolean trabajaEnPrivado;

    public Medico( String dni, String nombre, String apellidos, int edad, Genero genero, Categoria categoriaProf, boolean trabajaEnPrivado, double horasSemanales) {
        super(dni, nombre, apellidos , edad, genero);
        if (horasSemanales < 0) {
            throw new IllegalArgumentException("Las horas semanales no pueden ser negativas");
        }
        this.categoriaProf = categoriaProf;
        this.horasSemanales = horasSemanales;
        this.trabajaEnPrivado = trabajaEnPrivado;
    }

    public Categoria getCategoriaProf() {
        return categoriaProf;
    }

    public void setCategoriaProfesional(Categoria categoriaProf) {
        this.categoriaProf = categoriaProf;
    }

    public double getHorasSemanales() {
        return horasSemanales;
    }



    public boolean trabajaEnPrivado() {
        return trabajaEnPrivado;
    }

    @Override
    public String toString() {
        return  "Medico ( categoriaProf=" + categoriaProf + ", horasSemanales=" + horasSemanales + ", trabajaEnPrivado=" + trabajaEnPrivado+");";
    }
}
