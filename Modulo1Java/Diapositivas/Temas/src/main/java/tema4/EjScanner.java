package tema4;

import java.util.NoSuchElementException;
import java.util.Scanner;
class EjScanner {
    public static void main(String[] args) {
        try (Scanner teclado = new Scanner(System.in)) {
        for (int i = 0; i < 5; ++i) {
            System.out.print("Introduzca su nombre: ");
            teclado.skip("\\s*"); // elimina todos los espacios y nl
            String nombre = teclado.nextLine();
            System.out.print("Introduzca su edad: ");
            int edad = teclado.nextInt();
            teclado.skip(".*\n"); // elimina todo hasta nueva línea
            System.out.println(nombre + ": " + edad);
        }
        } catch (NoSuchElementException e) {
        System.out.println("Error al extraer el dato");
    }
        // En este punto, el teclado (System.in) será cerrado
        // y no se podrá utilizar posteriormente
    }
}
