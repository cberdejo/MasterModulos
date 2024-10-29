package buses;

public class EnMatricula implements Criterio {
    private String matricula;

    public EnMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public boolean esSeleccionable(Bus bus) {
        return bus.getMatricula().contains(matricula);
    }

    @Override
    public String toString() {
        return "Atobuses cuya matricula contiene " + matricula;
    }
}
