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
        this(TAMANO_POR_DEFECTO);
        movmientos = new ArrayList<>();

    }

    /// Realiza un intento para adivinar la clave secreta
    /// @param intento La combinacion introducida por el usuario
    /// @return El resultado del intento de adivinar la combinacion
    /// @throws  MastermindException si la combinacion no es valida o está en la memoria
    @Override
    public Movimiento intento(String intento) {

        Movimiento movimiento = super.intento(intento);

        if (movmientos.contains(movimiento)) {
            throw new MastermindException("Ya has intentado esa combinacion");
        }
        movimientos().add(movimiento);
        return movimiento;
    }


    public List<Movimiento> movimientos() {
        return movmientos;
    }
}
