package es.unican.is2.practica2.common;

public class OperacionNoValida extends RuntimeException {
	
    private static final long serialVersionUID = 1L;

    public OperacionNoValida(String msg) {
    	
        super(msg);
    }
}