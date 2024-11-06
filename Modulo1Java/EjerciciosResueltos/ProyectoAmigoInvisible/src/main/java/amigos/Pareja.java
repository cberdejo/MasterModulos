package amigos;

public record Pareja(Persona uno, Persona otro) {

    @Override
    public boolean equals(Object o) {
        return (o instanceof Pareja p)
                && (
                    (p.uno.equals(uno) && p.otro.equals(otro)) || (p.uno.equals(otro) && p.otro.equals(uno))
                );
    }



    @Override
    public int hashCode() {
    /*
        uno.hashCode() + otro.hashCode();
        No es del todo correcto porque por ejemplo si tuvieramos hashes 6 + 6 = 12 y 4 + 8 = 12.
        coincidirían pero no son iguales. Por lo visto una práctica común es múltiplicar por 31
        que es un número primo.
     */
        return 31 * uno.hashCode() + otro.hashCode();

    }
}
