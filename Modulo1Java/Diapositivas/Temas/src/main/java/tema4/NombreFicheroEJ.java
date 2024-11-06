package tema4;

public class NombreFicheroEJ {
	public static void main(String [] args) {
		NombreFichero nf = new NombreFichero("mdTemas/src/tema4/NombreFicheroEJ.java", '/', '.');
		System.out.println(nf.extension());
		System.out.println(nf.nombre());		
	}
}
