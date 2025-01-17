package hospitales;

import java.util.List;
import java.util.Optional;

public class Departamento {
    private String nombre;
    private List<Medico> medicos;

    public Departamento(String nombre, List<Medico> medicos) {
        this.nombre = nombre;
        this.medicos = medicos;
    }

    public Optional<Medico> getMedico(String dni) {
        return medicos.stream().filter(m -> m.getDni().equals(dni)).findFirst();
    }

    public boolean trabajaEnDepartamento(Medico medico) {
        return medicos.stream().anyMatch(m -> m.getDni().equals(medico.getDni()));
    }

    public int getNumMedicos(Categoria categoria) {
        return (int) medicos.stream().filter(m -> m.getCategoriaProf() == categoria).count();
    }
}
