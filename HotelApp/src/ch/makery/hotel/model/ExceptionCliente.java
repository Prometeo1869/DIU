package ch.makery.hotel.model;

public class ExceptionCliente extends Exception {
    private String mensaje;

    public ExceptionCliente() {
    }

    /**
     *
     * @param ms
     */
    public ExceptionCliente(String ms) { this.mensaje = ms;}

    /**
     *
     * @return mensaje
     */
    public String imprimirMensaje() { return this.mensaje; }
}
