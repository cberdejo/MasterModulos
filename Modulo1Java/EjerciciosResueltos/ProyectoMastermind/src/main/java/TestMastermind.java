import mastermind.MastermindException;
import mastermind.MastermindMemoria;
import mastermind.Movimiento;

import java.util.Scanner;

public class TestMastermind {

	public static void main(String[] args)  {
//		Mastermind juego = new Mastermind();
		MastermindMemoria juego = new MastermindMemoria();

//		System.out.println(juego.getSecreto());
		Scanner sc = new Scanner(System.in);
		boolean acertado = false;
		String cifras = null;
		int intento = 1;
		while (!acertado) {
			try {
				System.out.print("Intento " + intento
						+ ". Introduce cifra: ");
				cifras = sc.next();
				Movimiento mov = juego.intento(cifras);
				System.out.println("Intento " + intento + " " + mov);
				acertado = mov.colocadas() == juego.getLongitud();
				intento++;
			} catch (MastermindException e) {
				System.out.println(cifras + " no validas: "
						+ e.getMessage());
			}
		}
		System.out.println("Correcto en " + (intento - 1) + " intentos");
//			System.out.println("Movimientos :" + juego.movimientos());
	}
}
