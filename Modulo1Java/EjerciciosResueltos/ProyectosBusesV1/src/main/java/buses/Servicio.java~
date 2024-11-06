package buses;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.*;

public class Servicio {
    private String ciudad;
    private List<Bus> buses;

    public Servicio(String ciudad) {
        buses = new ArrayList<>();
        this.ciudad = ciudad;
    }

    public List<Bus>filtra (Criterio criterio){
        return buses.stream().filter(criterio::esSeleccionable).toList();
    }

    public String getCiudad() {
        return ciudad;
    }
    public void leeBuses(String file) throws IOException {

        try (Scanner sc = new Scanner(Path.of(file))){
            while(sc.hasNextLine()){
                Optional<Bus> p = stringABus(sc.nextLine());
                p.ifPresent(pais -> buses.add(pais));
            }

        }

    }

    private Optional<Bus> stringABus(String linea) {
        try (Scanner scLinea = new Scanner(linea)) {
            scLinea.useDelimiter("[,]+");
            int codBus = scLinea.nextInt();
            String matricula = scLinea.next();
            int codLinea = scLinea.nextInt();

            Bus bus = new Bus(codBus, matricula);
            bus.setCodLinea(codLinea);
            return Optional.of(bus);
        } catch (InputMismatchException e) {
            System.err.println("Error, en dato numérico en " + linea);
            return Optional.empty();
        } catch (NoSuchElementException e) {
            System.err.println("Error, faltan datos en " + linea);
            return Optional.empty();
        }
    }

    public void guarda(PrintWriter pw, Criterio criterio) throws IOException {
        buses.stream().filter(criterio::esSeleccionable).forEach(pw::println);
    }
    public void guarda(String file, Criterio criterio) throws IOException {
        try (PrintWriter pw = new PrintWriter(file)) {
            guarda(pw, criterio);
        }
    }
}
