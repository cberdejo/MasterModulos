package jarras;

import position.Posicion;
/// @Author Christian Berdejo
/// @Version 1.0

public class Mesa {
    private Jarra jarraIz;
    private Jarra jarraDr;

    /**
     * Crea una Mesa con capacidades para cada Jarra
     * @param capacidadIz capacidad inicial Jarra izquierda.
     * @param capacidadDr capacidad inicial Jarra derecha.
     * @throws IllegalArgumentException en caso de que la capacidad de alguna de las Jarras sea negativo.
     */
    public Mesa(int capacidadIz, int capacidadDr) {
        jarraIz = new Jarra(capacidadIz);
        jarraDr = new Jarra(capacidadDr);
    }

    /**
     * Crea una Mesa con dos jarras.
     * @param j1 la primera Jarra
     * @param j2 la segunda Jarra
     */
    public Mesa(Jarra j1, Jarra j2) {
        jarraIz = j1;
        jarraDr = j2;
    }

    /**
     * @param posicion La Posicion de la jarra a consultar
     * @return El contenido de la Jarra en la Posicion especificada
     */
   public int contenido(Posicion posicion){
        return posicion == Posicion.DERECHA ? jarraDr.contenido() : jarraIz.contenido();
   }

   /**
    * @param posicion La Posicion de la jarra a consultar
    * @return La capacidad de la Jarra en la Posicion especificada
    */
   public int capacidad(Posicion posicion){
       return posicion == Posicion.DERECHA ? jarraDr.capacidad() : jarraIz.capacidad();
   }

   /**
    * Llena la Jarra situada en la Posicion especificada
    * @param posicion La Posicion de la jarra a llenar
    */
   public void llena(Posicion posicion){
       if(posicion == Posicion.DERECHA){
           jarraDr.llena();
       }else{
           jarraIz.llena();
       }
   }
   /**
    * Vacía la Jarra situada en la Posicion especificada
    * @param posicion La Posicion de la jarra a vaciar
    */
   public void vacia(Posicion posicion){
       if(posicion == Posicion.DERECHA){
           jarraDr.vacia();
       }else{
           jarraIz.vacia();
       }
   }
   /**
    * Llena la Jarra situada en la Posicion especificada desde la otra
    * @param posicion La Posicion de la jarra a llenar
    */
   public void llenaDesde(Posicion posicion){
       if(posicion == Posicion.DERECHA){
           jarraDr.llenaDesde(jarraIz);
       }else{
           jarraIz.llenaDesde(jarraDr);
       }
   }

   /**
    * @return Un String que representa el estado actual de la Mesa
    */
   @Override
   public String toString() {
       return "M(" + jarraIz+ "," + jarraDr+ ")";
   }
}
