import npi.NPI;

import static npi.NPI.*;

public class Main {
    public static void main(String [] args) {
        // 5 + (6.4 - 2) * raiz(3.1);
        // NPI: 5 6.4 2 – 3.1 raiz * +
        NPI cpi = new NPI();
        cpi.entra(5);
        cpi.entra(6.4);
        cpi.entra(2);
        cpi.opera2(resta);
        cpi.entra(3.1);
        cpi.opera1(raiz);
        cpi.opera2(producto);
        cpi.opera2(suma);
        System.out.println("Resultado " + cpi.getResultado());
    }
}