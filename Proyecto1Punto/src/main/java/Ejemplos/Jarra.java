package Ejemplos;

public class Jarra {
    private int capacidadMaxima;
    private int cantidadActual;

    public Jarra(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.cantidadActual = 0;
    }

    public void llenar() {
        cantidadActual = capacidadMaxima;
        System.out.println("La jarra está llena.");
    }

    public void vaciar() {
        cantidadActual = 0;
        System.out.println("La jarra está vacía.");
    }
    public void vertirAguaDeUnaJarraAOtra(int cantidad, Jarra jarra){
        if (cantidad > cantidadActual){
            cantidadActual=0;
            jarra.agregarAgua(cantidadActual);
        }else {
            cantidadActual-=cantidad;
            jarra.agregarAgua(cantidadActual);
        }
    }

    public void agregarAgua(int cantidad) {
        if (cantidadActual + cantidad <= capacidadMaxima) {
            cantidadActual += cantidad;
            System.out.println("Agregaste " + cantidad + " unidades de agua.");
        } else {
            System.out.println("No se puede agregar esa cantidad. Se desbordaría.");
        }
    }

    public String toString(){
        return String.format("Capacidad Máxima %d \n Capacidad Mínima %d .", capacidadMaxima, cantidadActual);
    }
}
