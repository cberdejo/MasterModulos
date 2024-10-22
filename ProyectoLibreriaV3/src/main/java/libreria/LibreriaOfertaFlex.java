package libreria;

public class LibreriaOfertaFlex extends Libreria {

    private  OfertaFlex oferta;

    public LibreriaOfertaFlex(OfertaFlex ofertaFlex){
        super();
        this.oferta = ofertaFlex;
    }

    public OfertaFlex getOferta() {
        return oferta;
    }

    public void setOferta(OfertaFlex ofertaFlex) {
        this.oferta = ofertaFlex;
    }

    @Override
    public void addLibro(String autor, String titulo, double precioBase) {
        Libro libro = new Libro(autor, titulo, precioBase);
        double descuento = oferta.getDescuento(libro);
        if ( descuento > 0)super.addLibro(libro);
        else super.addLibro(new LibroEnOferta(autor,titulo,precioBase,descuento));

    }
}
