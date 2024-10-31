package tema3;

public class EjemploEx {
	void aux(int tam) {
		try { 
			int a[] = new int[tam]; 
			a[4] = 0; 
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("excepción: " + e.getMessage()); 
			e.printStackTrace(); 
		} 
	}
	public static void main(String[] args) {
		new EjemploEx().aux(2);
		System.out.println("fin");
	}
}
