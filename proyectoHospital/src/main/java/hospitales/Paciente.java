package hospitales;

public class Paciente extends Persona {
    private Medico medico;
    private boolean urgente;
    private String segSocial;
    private double altura;
    private double peso;

    public Paciente(String dni, String nombre, String apellidos, int edad, Genero genero, Medico medico, boolean urgente, String segSocial, double altura, double peso) {
        super(nombre, apellidos, dni, edad, genero);
        this.medico = medico;
        this.urgente = urgente;
        this.segSocial = segSocial;
        this.altura = altura;
        this.peso = peso;
    }

    public String getNumSegSocial() {
        return segSocial;
    }

    public boolean esUrgencia() {
        return urgente;
    }
    public void setEsUrgencia(boolean urgente) {
        this.urgente = urgente;
    }
    public Medico esAtendidoPor() {
        return medico;
    }

    public void asignaMedico(Medico medico) {
        this.medico = medico;
    }

    public double getAltura() {
        return altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getIndiceCorporal(){
        return this.peso / this.altura;
    }



    @Override
    public String toString() {
        return super.toString() + ", medico=" + medico + ", urgente=" + urgente + ", segSocial=" + segSocial + ", altura=" + altura + ", peso=" + peso;
    }
}

