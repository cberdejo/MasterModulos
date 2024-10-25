import mastermind.Mastermind;

import mastermind.MastermindMemoria;
import mastermind.Movimiento;

import java.util.Optional;
import java.util.Scanner;

public class TestMastermind {
	private static final String FIN = "FIN";

	public static void main(String[] args) {
		//Mastermind juego = new Mastermind();
		MastermindMemoria juego = new MastermindMemoria();

		Scanner sc = new Scanner(System.in);
		boolean acertado = false;
		String cifras = null;
		boolean meRindo = false;
		int intento = 1;
		while (!acertado && !meRindo) {
			System.out.print("Introduce tu jugada " + intento
					+ " (FIN para terminar): ");
			cifras = sc.next();
			meRindo = cifras.equalsIgnoreCase(FIN);
			if (!meRindo) {
				Optional<Movimiento> omov = juego.intento(cifras);
				if (omov.isPresent()) {
					System.out.println(omov.get());
					acertado = omov.get().colocadas() == 4;
					intento++;
				} else {
					System.out.println("Error en la jugada " + cifras);
				}
			}
		}
		if (meRindo) {
			System.out.println("Te rendiste : " + juego.getSecreto());
		} else {
			System.out.println("Correcto en " + (intento - 1) + " intentos");
		}
//			System.out.println("Movimientos :" + juego.movimientos());
	}
}
