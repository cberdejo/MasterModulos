import libreria.Libreria;

public class Main {

    private static void printTituloWithPrecio(String titulo, double precio) {
        System.out.println("El libro: " + titulo + " tiene un precio de: " + precio);
    };

    public static void main(String[] args) {
        Libreria libreria = new Libreria();
        libreria.addLibro("george orwell", "1984", 8.20);
        libreria.addLibro("Philip K. Dick", "¿Sueñan los androides con ovejas eléctricas?", 3.50);
        libreria.addLibro("Isaac Asimov", "Fundación e Imperio", 9.40);
        libreria.addLibro("Ray Bradbury", "Fahrenheit 451", 7.40);
        libreria.addLibro("Alex Huxley", "Un Mundo Feliz", 6.50);
        libreria.addLibro("Isaac Asimov", "La Fundación", 7.30);
        libreria.addLibro("William Gibson", "Neuromante", 8.30);
        libreria.addLibro("Isaac Asimov", "SegundaFundación", 8.10);
        libreria.addLibro("Isaac Newton", "arithmetica universalis", 7.50);
        libreria.addLibro("George Orwell", "1984", 6.20);
        libreria.addLibro("Isaac Newton", "Arithmetica Universalis", 10.50);
        System.out.println(libreria);

        libreria.remLibro("george orwell", "1984");
        libreria.remLibro("Alex Huxley", "Un Mundo Feliz");
        libreria.remLibro("Isaac Newton", "arithmetica universalis");
        System.out.println(libreria);


        printTituloWithPrecio("George Orwell",  libreria.getPrecioFinal("George Orwell", "1984"));
        printTituloWithPrecio("Philip K. Dick",  libreria.getPrecioFinal("Philip K. Dick", "¿Sueñan los androides con ovejas más electricas?"));
        printTituloWithPrecio("Ray Bradbury",  libreria.getPrecioFinal("Ray Bradbury", "Fahrenheit 451"));
        printTituloWithPrecio("Alex Huxley",  libreria.getPrecioFinal("Alex Huxley", "Un Mundo Feliz"));
        printTituloWithPrecio("Isaac Asimov",  libreria.getPrecioFinal("Isaac Asimov", "La Fundación"));
        printTituloWithPrecio("William Gibson",  libreria.getPrecioFinal("William Gibson", "Neuromante"));
        printTituloWithPrecio("Isaac Asimov",  libreria.getPrecioFinal("Isaac Asimov", "Segunda Fundación"));
        printTituloWithPrecio("Isaac Newton",  libreria.getPrecioFinal("Isaac Newton", "Arithmetica Universalis"));


    }

}
