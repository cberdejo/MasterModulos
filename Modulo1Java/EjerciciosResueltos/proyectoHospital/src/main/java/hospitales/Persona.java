package hospitales;

public abstract class Persona {
    private int edad;
    private Genero genero;
    private String apellidos;
    private String dni;
    private String  nombre;

    public Persona(String dni, String nombre, String apellidos, int edad, Genero genero ) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        this.edad = edad;
        this.genero = genero;
        this.apellidos = apellidos;
        this.dni = dni; // se podría validar el DNI
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }
}
