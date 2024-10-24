package mastermind;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MastermindMemoria extends Mastermind{

    private List<Movimiento> movmientos;
    public MastermindMemoria(int tam){
        super(tam);
        movmientos = new ArrayList<>();
    }

    public MastermindMemoria(){
        super();
        movmientos = new ArrayList<>();

    }

    /// Realiza un intento para adivinar la clave secreta
    /// @param intento La combinacion introducida por el usuario
    /// @return El resultado del intento de adivinar la combinacion
    /// @throws  MastermindException si la combinacion no es valida o está en la memoria
    @Override
    public Movimiento intento(String intento) {
        if (movmientos.stream().anyMatch(movimiento -> intento.equals(movimiento.intento()))) {
            throw new MastermindException("Ya has intentado esa combinacion");
        }
        Movimiento movimiento = super.intento(intento);
        movimientos().add(movimiento);
        return movimiento;
    }


    public List<Movimiento> movimientos() {
        return movmientos;
    }
}
