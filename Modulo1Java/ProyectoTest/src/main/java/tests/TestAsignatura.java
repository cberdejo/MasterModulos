package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestAsignatura {
    private List<Test> examenes;
    private double valorAciertos;
    private double valorErrores;
    private String nombreAsignatura;
    private final static double APROBADO = 5;

    public TestAsignatura(String nombreAsignatura, double valorAciertos, double valorErrores, List<String> exms){
        this.nombreAsignatura = nombreAsignatura;
        this.valorAciertos= valorAciertos;
        this.valorErrores =valorErrores;
        this.examenes = new ArrayList<>();
        extraeDatos(exms);

    }

    public TestAsignatura(String nombreAsignatura, List<String> informacion){
       this(nombreAsignatura,1,0,informacion);


    }

    private  void extraeDatos(List<String> exams){
        for (String exam: exams){
            try (Scanner sc = new Scanner(exam)){
                sc.useDelimiter("[:+]+");
                String nombre = sc.next();
                int aciertos = sc.nextInt();
                int fallos = sc.nextInt();

                examenes.add(new Test(nombre,aciertos,fallos));
            }
        }
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






/*
    private  void extraeDatos(List<String> info){
        String infoString = info.toString();
        try (Scanner sc = new Scanner(infoString.substring(1, infoString.length() - 1))) { // remove the surrounding brackets
            sc.useDelimiter(", ");
            while (sc.hasNext()) {
                String[] nombreYNotas = sc.next().split(":");
                String nombre = nombreYNotas[0].trim();
                String[] notas = nombreYNotas[1].split("\\+");

                int aciertos = Integer.parseInt(notas[0].trim());
                int errores = Integer.parseInt(notas[1].trim());

                examenes.add(new Test(nombre, aciertos, errores));
            }
        }
    }
*/