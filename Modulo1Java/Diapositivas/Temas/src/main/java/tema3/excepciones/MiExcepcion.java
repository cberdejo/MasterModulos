package tema3.excepciones;

public class MiExcepcion extends RuntimeException {
	public MiExcepcion() {
		super();
	}
	public MiExcepcion(String msg) {
		super(msg);
	}
	public MiExcepcion(int i) {
		super(Integer.toString(i));
	}
}