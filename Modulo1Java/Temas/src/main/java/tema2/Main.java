package tema2;

public class Main {
	public static void main(String [] args) {
		// opcion 1
		Ejecutable ej = new EjecutableImpl();
		prueba(ej);
		// opcion 2
		prueba(new Ejecutable() {
			public void ejecuta() {
				System.out.println("Ya está ejecutado");
			}
		});
	}
	
	public static void prueba(Ejecutable ej) {
		ej.ejecuta();
	}
}
