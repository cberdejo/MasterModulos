import amigos.AmigoException;
import amigos.Club;
import amigos.ClubParejas;
import amigos.ClubManager;

import java.io.IOException;

public class Main {
	public static void main(String [] args)  {
//		ClubManager clubM = new ClubManager(new Club());
		ClubManager clubM = new ClubManager(new ClubParejas());
		try {
			clubM
				.setEntrada("data/socios.txt", "[ ,\\-;]+")
//				.setEntrada("data/socios.txt", "[ ,;]+")
				.setConsola(true)
// 				.setSalida("data/salida.txt")
				.build();
		} catch (IOException e) {
			System.out.println("Error en la entrada/salida de datos");
		} catch (AmigoException e) {
			System.out.println("Error: " + e.getMessage());			
		}
	}
}
