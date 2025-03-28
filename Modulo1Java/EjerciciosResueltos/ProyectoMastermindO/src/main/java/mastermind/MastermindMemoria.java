package mastermind;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MastermindMemoria extends Mastermind{

    private List<Movimiento> movmientos;
    public MastermindMemoria(int tam){
        super(tam);
        movmientos = new ArrayList<>();
    }

    public MastermindMemoria(){
        this(TAMANO_POR_DEFECTO);
        movmientos = new ArrayList<>();

    }

    /// Realiza un intento para adivinar la clave secreta
    /// @param intento La combinacion introducida por el usuario
    /// @return El resultado del intento de adivinar la combinacion, si no es valido devuelve empty
    @Override
    public Optional<Movimiento> intento(String intento) {

        Optional<Movimiento> movimiento = super.intento(intento);

        if ( movimiento.isEmpty() ||  movmientos.contains(movimiento.get())) {
            return Optional.empty();
        }
        movimientos().add(movimiento.get());
        return movimiento;
    }


    public List<Movimiento> movimientos() {
        return movmientos;
    }
}
