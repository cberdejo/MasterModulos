import buses.Criterio;
import buses.EnMatricula;
import buses.PorLinea;
import buses.Servicio;

import java.io.IOException;
import java.io.PrintWriter;

public class MainBuses {
    public static void main(String [] args) {
        Servicio servicio = new Servicio("Malaga");
        try {
        	System.out.println(servicio.getCiudad());
            servicio.leeBuses("data/buses.txt");
            Criterio cr1 = new PorLinea(21);
            servicio.guarda("data/linea21.txt", cr1);
            servicio.guarda(new PrintWriter(System.out,true), cr1);
            Criterio cr2 = new EnMatricula("29");
            servicio.guarda("data/contiene29.txt", cr2);
            servicio.guarda(new PrintWriter(System.out,true), cr2);
        } catch ( IOException e) {
            System.err.println("No existe el fichero de entrada");
        }
    }
}
