package tema4;

import tema4.clases.Persona;

public class MainPersona {
	public static void main(String [] args) {
		Persona p1 = new Persona("juan", 23);
		Persona p2 = new Persona("pedro", 15);
		Persona p3 = new Persona("JUAN", 23);
		System.out.println("p1.equals(p2) = " + p1.equals(p2));
		System.out.println("p1.equals(p3) = " + p1.equals(p3));
		System.out.println("p1 == p3 = " + (p1 == p3));
		String s1 = "hola";
		String s2 = "ho" + "la";
		String s3 = new String("hola");
		System.out.println("s1 == s2 = " + (s1 == s2));
		System.out.println("s1 == s3 = " + (s1 == s3));
		System.out.println("s1.equals(s2) = " + s1.equals(s2));
		System.out.println("s1.equals(s3) = " + s1.equals(s3));
	}
}
