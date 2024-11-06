package tests;

public record Test(String nombre, int aciertos, int errores) {

    public Test {
        if (aciertos<0 | errores<0) throw new IllegalArgumentException();
    }

    @Override
    public boolean equals(Object obj) {
      return (obj instanceof Test t) && t.nombre.equalsIgnoreCase(nombre);
    }

    @Override
    public int hashCode(){
        return nombre.toLowerCase().hashCode();
    }

    public double calificacion(double valAc, double valErr){
        if (valAc <0 | valErr<0) throw new IllegalArgumentException();
        return valAc*aciertos-valErr*errores;
    }
}
