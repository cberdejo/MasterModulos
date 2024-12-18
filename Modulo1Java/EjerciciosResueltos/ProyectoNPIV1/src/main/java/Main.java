import npi.NPI;

public class Main {
    public static void main(String[] args) {
    NPI npi = new NPI();
    // 3*(6-4)+5  => 3 6 4 - * 5 +
    npi.entra(3);
    npi.entra(6);
    npi.entra(4);
    npi.resta();
    npi.multiplica();
    npi.entra(5);
    npi.suma();
    System.out.println("3*(6-4)+5 = "+npi.getResultado());

    //3 * (6 - 2) + 5 => 3 6 2 - * 5 +

    npi.entra(3);
    npi.entra(6);
    npi.entra(2);
    npi.resta();
    npi.multiplica();
    npi.entra(5);
    npi.suma();
    System.out.println("3*(6-2)+5 = "+npi.getResultado());

    //3 * (6 - 2) + (2 + 7) / 5 => 3 6 2 - *  2  7 +  5 / +
    npi.entra(3);
    npi.entra(6);
    npi.entra(2);
    npi.resta();
    npi.multiplica();
    npi.entra(2);
    npi.entra(7);
    npi.suma();
    npi.entra(5);
    npi.divide();
    npi.suma();
    System.out.println("3*(6-2)+(2+7)/5 = "+npi.getResultado());
    }
}
