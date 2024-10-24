package tests;

import java.util.ArrayList;
import java.util.List;

public class TestAsignatura {
    private List<Test> examenes;
    private double valorAciertos;
    private double valorErrores;
    private String nombreAsignatura;
    private final static double APROBADO = 5;

    public TestAsignatura(String nombreAsignatura, double valorAciertos, double valorErrores, List<String> aciertos){
        this.nombreAsignatura = nombreAsignatura;
        this.valorAciertos= valorAciertos;
        this.valorErrores =valorErrores;
        examenes = new ArrayList<>();


    }

    public TestAsignatura(String nombreAsignatura, List<String> informacion){
        this.nombreAsignatura = nombreAsignatura;
        examenes = new ArrayList<>();




    }

    private  void extraeDatos(List<String> info){

    }

    public double notaMedia() {
        return this.examenes.stream()
                .mapToDouble(examen -> examen.calificacion(this.valorAciertos, this.valorErrores))
                .average()
                .orElse(0.0);  //0.0 si no hay exámenes en la lista
    }


    public String getNombre() {
        return nombreAsignatura;
    }
    public int aprobados(){
        return (int) this.examenes.stream().
                filter(examen -> examen.calificacion(valorAciertos,valorErrores)>=APROBADO).count();
    }
}
